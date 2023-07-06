package hr.fer.tel.server.rest.service.graph;

import hr.fer.tel.server.rest.model.Scene;
import hr.fer.tel.server.rest.model.graph.MonitoringRelationship;
import hr.fer.tel.server.rest.model.graph.SceneGraph;
import hr.fer.tel.server.rest.model.graph.TagGraph;
import hr.fer.tel.server.rest.repository.dao.SceneRepository;
import hr.fer.tel.server.rest.repository.dao.graph.SceneGraphRepository;
import hr.fer.tel.server.rest.repository.dao.graph.TagGraphRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@org.springframework.stereotype.Service
public class GraphService {

    @Autowired
    private TagGraphRepository tagRepository;

    @Autowired
    private SceneGraphRepository sceneGraphRepository;

    @Autowired
    private SceneRepository sceneRelationalRepository;


//    public TagGraph test(){
//        List<String> list = List.of("fer", "ferit");
////        List<TagGraph> tagGraphs = findByTagName(list);
//
//        TagGraph tagGraph = tagRepository.findByTagName( "fer" );
//        TagGraph tagGraph2 = tagRepository.findByTagName2( "fer" );
//
//        return tagGraph;
//    }

    //Scenes methods
    public List<SceneGraph> getScenes() {
        return sceneGraphRepository.findAll();
    }

    public List<String> getScenesNames() {
        return sceneGraphRepository.findAllScenesNames();
    }

    public List<String> getScenesByTagName(String tagName) {
        return sceneGraphRepository.findAllScenesByTagsName( tagName );
    }

//    public SceneGraph addScene(String sceneName) {
//        SceneGraph sceneGraph = sceneRepository.findBySceneName( sceneName );
//        if (sceneGraph == null) {
//            sceneGraph = new SceneGraph( sceneName );
//            sceneRepository.save( sceneGraph );
//        }
//        return sceneGraph;
//    }

    //Dodavanje samp preko SceneControllera
//    public SceneGraph addSceneToTag(String sceneName, String tagName) {
//        SceneGraph sceneGraph = sceneRepository.findBySceneName( sceneName );
//        if (sceneGraph == null) {
//            sceneGraph = new SceneGraph( sceneName );
//        }
//
//        TagGraph tagGraph = tagRepository.findByTagName( tagName );
//        if (tagGraph == null) {
//            tagGraph = new TagGraph( tagName );
//        }
//
//        MonitoringRelationship monitoring = new MonitoringRelationship();
//        monitoring.setScene( sceneGraph );
//        tagGraph.addMonitoring( monitoring );
//
//        tagRepository.save( tagGraph );
//
//        return sceneGraph;
//    }

    //Brisanje dopusteno samo preko SceenControllera
//    public SceneGraph deleteScene(String sceneName) {
//        SceneGraph sceneGraph = sceneRepository.findBySceneName( sceneName );
//        if (sceneGraph == null) {
//            return null;
//        }
//
//        sceneRepository.delete( sceneGraph );
//
//        return sceneGraph;
//    }


    //Tags methods
    public List<TagGraph> getAllTags() {
        return tagRepository.findAll();
    }

    public List<String> getAllTagsNames() {
        return tagRepository.findAllTagsNames();
    }

    public TagGraph addTag(String tagName) {
        TagGraph tagGraph = tagRepository.findByTagName( tagName );
        if (tagGraph == null) {
            tagGraph = new TagGraph( tagName );
            tagRepository.save( tagGraph );
        }
        return tagGraph;
    }

    public TagGraph connectTags(String tagFrom, String tagTo) {
        TagGraph tagGraphFrom = tagRepository.findByTagName( tagFrom );
        TagGraph tagGraphTo = tagRepository.findByTagName( tagTo );

        if (tagGraphFrom == null || tagGraphTo == null) {
            return null;
        }

        tagGraphFrom.addOperates( tagGraphTo );
        tagGraphTo.addOperates( tagGraphFrom );

        tagRepository.save( tagGraphFrom );
        tagRepository.save( tagGraphTo );

        return tagGraphFrom;
    }


    //Kada se pobrise treba spojiti sve tagove koji su bili spojeni na taj tag - djecu tog taga
    @Transactional
    public TagGraph deleteTag(String tagName) {
        TagGraph tagGraph = tagRepository.findByTagName( tagName );
        if (tagGraph == null) {
            return null;
        }

        tagRepository.delete( tagGraph );
        sceneRelationalRepository.deleteTagFromScenes( tagName );

        return tagGraph;
    }


    //General methods
    public boolean addToNeo4J(Scene scene) {
        SceneGraph sceneGraph;

        //Check if scene already exists
        sceneGraph = sceneGraphRepository.findBySceneName( scene.getTitle() );

        //If scene doesn't exist, create it
        if (sceneGraph == null) {
            sceneGraph = new SceneGraph( scene.getTitle() );
        }
        sceneGraphRepository.save( sceneGraph );

        this.saveToNeo4j( scene, sceneGraph );

        return true;
    }

    public boolean modifyToNeo4J(Scene newScene, Scene oldScene) {

        if(deleteFromNeo4J( oldScene ) == false)
            return false;

        if(addToNeo4J( newScene ) == false)
            return false;

        return true;
    }


    //Koristi ju SceneController
    public boolean deleteFromNeo4J(Scene scene) {

        SceneGraph sceneGraph = sceneGraphRepository.findBySceneName( scene.getTitle() );
        if (sceneGraph == null) {
            return false;
        }

        sceneGraphRepository.delete( sceneGraph );
        return true;
    }


    private void saveToNeo4j(Scene scene, SceneGraph sceneGraph) {
        //Tags from new Scene
        List<String> tags = scene.getTags();

        //Tags that already exist in database
        List<TagGraph> selectedTags = tagRepository.findByTagNameIn( tags );

        //Match tags that already exists, if does not exist, create new tag
        for (String tag : tags) {
            if (selectedTags.stream().noneMatch( tagGraph -> tagGraph.getTagName().equals( tag ) )) {
                TagGraph tagGraph = new TagGraph( tag );
                selectedTags.add( tagGraph );
            }
        }

        //Adding relationship between scene and tags
        for (TagGraph tagGraph : selectedTags) {
            MonitoringRelationship monitoring = new MonitoringRelationship();
            monitoring.setScene( sceneGraph );
            tagGraph.addMonitoring( monitoring );
        }

        //Save scene and tags
        for (TagGraph tagGraph : selectedTags) {
            tagRepository.save( tagGraph );
        }
    }


}








