package hr.fer.tel.server.rest.model.graph;

import org.springframework.data.neo4j.core.schema.*;

@RelationshipProperties
public class MonitoringRelationship {

    @Id
    @GeneratedValue
    private Long id;

    @Property(name = "tbt")
    private String tbt;

    @TargetNode
    private SceneGraph scene;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTbt() {
        return tbt;
    }

    public void setTbt(String tbt) {
        this.tbt = tbt;
    }

    public SceneGraph getScene() {
        return scene;
    }

    public void setScene(SceneGraph scene) {
        this.scene = scene;
    }
}
