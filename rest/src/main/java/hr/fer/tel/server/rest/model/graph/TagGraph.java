package hr.fer.tel.server.rest.model.graph;

import org.springframework.data.neo4j.core.schema.*;

import java.util.*;

@Node(labels = {"Tag"})
public class TagGraph {

    @Id
    @GeneratedValue
    private Long id;

    @Property(name = "name")
    private String tagName;

    @Relationship(type = "IS_OPERATING", direction = Relationship.Direction.OUTGOING)
    private Set<TagGraph> operates = new HashSet<>();

    @Relationship(type = "IS_MONITORING", direction = Relationship.Direction.OUTGOING)
    private Set<MonitoringRelationship> monitoring = new HashSet<>();

    public TagGraph() {
    }

    public TagGraph(String tagName) {
        this.tagName = tagName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Set<TagGraph> getOperates() {
        return operates;
    }

    public boolean addOperates(TagGraph tag) {
        return operates.add(tag);
    }

    public Set<MonitoringRelationship> getMonitoring() {
        return monitoring;
    }

    public boolean addMonitoring(MonitoringRelationship isMonitoringRelationship) {
        return monitoring.add(isMonitoringRelationship);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TagGraph tagGraph)) return false;
        return Objects.equals( tagName, tagGraph.tagName );
    }

    @Override
    public int hashCode() {
        return Objects.hash( tagName );
    }
}
