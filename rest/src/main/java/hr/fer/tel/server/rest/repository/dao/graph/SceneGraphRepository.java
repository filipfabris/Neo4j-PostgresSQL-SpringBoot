package hr.fer.tel.server.rest.repository.dao.graph;

import hr.fer.tel.server.rest.model.graph.SceneGraph;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SceneGraphRepository extends Neo4jRepository<SceneGraph, Long> {

    @Query("MATCH (s:Scene) RETURN s.name")
    List<String> findAllScenesNames();

    @Query( "OPTIONAL MATCH (parent:Tag {name:$tagName})-[:IS_OPERATING*]->(child:Tag)-[:IS_MONITORING]->(scene:Scene)\n" +
            "OPTIONAL MATCH (parent2:Tag {name:$tagName})-[:IS_MONITORING]->(novo)\n" +
            "WITH apoc.coll.union(COLLECT( distinct scene.name), COLLECT( distinct novo.name)) AS output\n" +
            "UNWIND output AS col\n" +
            "RETURN col"
    )
    List<String> findAllScenesByTagsName(String tagName);

    SceneGraph findBySceneName(String name);

}
