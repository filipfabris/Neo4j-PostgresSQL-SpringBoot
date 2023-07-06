package hr.fer.tel.server.rest.repository.dao.graph;

import hr.fer.tel.server.rest.model.graph.TagGraph;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public interface TagGraphRepository extends Neo4jRepository<TagGraph, Long> {

    @Query("MATCH (t:Tag) RETURN t.name")
    List<String> findAllTagsNames();

    TagGraph findByTagName(String name);

    @Query("MATCH (tracker:Tag {name: $name}) " +
            "WITH tracker " +
            "OPTIONAL MATCH (tracker)-[r_issued_by:IS_OPERATING]->(organization:Tag) " +
            "WITH tracker, COLLECT(r_issued_by) AS organization_rels, COLLECT(DISTINCT organization) AS organizations " +
            "OPTIONAL MATCH (tracker)-[r_assigned_to:IS_MONITORING]->(asset:Scene) " +
            "RETURN tracker, organization_rels, organizations, COLLECT(r_assigned_to) AS asset_rels, COLLECT(DISTINCT asset) AS assets")
    TagGraph findByTagName2(String name);


    List<TagGraph> findByTagNameIn(List<String> tags);

    @Query("MATCH (tracker:Tag) " +
            "WHERE tracker.name IN $tagNames " +
            "WITH tracker " +
            "OPTIONAL MATCH (tracker)-[r_issued_by:IS_OPERATING]->(organization:Tag) " +
            "WITH tracker, COLLECT(r_issued_by) AS organization_rels, COLLECT(DISTINCT organization) AS organizations " +
            "OPTIONAL MATCH (tracker)-[r_assigned_to:IS_MONITORING]->(asset:Scene) " +
            "RETURN tracker, organization_rels, organizations, COLLECT(r_assigned_to) AS asset_rels, COLLECT(DISTINCT asset) AS assets")
    List<TagGraph> findByTagNames2(List<String> tagNames);


}
