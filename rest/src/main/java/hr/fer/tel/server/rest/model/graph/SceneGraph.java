package hr.fer.tel.server.rest.model.graph;

import hr.fer.tel.server.rest.model.Scene;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Node(labels = {"Scene"})
public class SceneGraph {

    @Id
    @GeneratedValue
    private Long id;

    @Property(name = "name")
    private String sceneName;

    public SceneGraph() {
    }

    public SceneGraph(String sceneName) {
        this.sceneName = sceneName;
    }

    public SceneGraph(Scene scene) {
        this.sceneName = scene.getTitle();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }
}
