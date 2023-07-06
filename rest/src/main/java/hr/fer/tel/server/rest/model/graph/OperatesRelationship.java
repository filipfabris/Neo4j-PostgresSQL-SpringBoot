package hr.fer.tel.server.rest.model.graph;

import org.springframework.data.neo4j.core.schema.*;

@RelationshipProperties
public class OperatesRelationship {


    @Id
    @GeneratedValue
    private Long id;

    @Property(name = "tbt")
    private String tbt;

    @TargetNode
    private TagGraph tag;
}
