package hr.fer.tel.server.rest.controller.graph;

import hr.fer.tel.server.rest.model.graph.SceneGraph;
import hr.fer.tel.server.rest.model.graph.TagGraph;
import hr.fer.tel.server.rest.service.graph.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class Controller {

    @Autowired
    private GraphService graphService;

    @GetMapping("/scenes")
    public ResponseEntity<List<SceneGraph>> getScenes() {
        return ResponseEntity.ok( graphService.getScenes());
    }

    @GetMapping("/scenes/names")
    public ResponseEntity<List<String>> getScenesNames() {
        return ResponseEntity.ok( graphService.getScenesNames());
    }

    @GetMapping("/scenes/tags/{tagName}")
    public ResponseEntity<List<String>> getScenesByTagName(@PathVariable String tagName) {
        return ResponseEntity.ok( graphService.getScenesByTagName(tagName));
    }

//    @PostMapping("/scenes/add/{sceneName}")
//    public ResponseEntity<SceneGraph> addScene(@PathVariable String sceneName) {
//        return ResponseEntity.ok( graphService.addScene(sceneName));
//    }

//    @PostMapping("/scenes/add/{sceneName}/{tagName}")
//    public ResponseEntity<SceneGraph> addSceneToTag(@PathVariable String sceneName, @PathVariable String tagName) {
//        return ResponseEntity.ok( graphService.addSceneToTag(sceneName, tagName));
//    }

//    @DeleteMapping("/scenes/delete/{sceneName}")
//    public ResponseEntity<SceneGraph> deleteScene(@PathVariable String sceneName) {
//        return ResponseEntity.ok( graphService.deleteScene(sceneName));
//    }






    @GetMapping("/tags")
    public ResponseEntity<List<TagGraph>> getTags() {
        return ResponseEntity.ok( graphService.getAllTags());
    }

    @GetMapping("/tags/names")
    public ResponseEntity<List<String>> getTagsNames() {
        return ResponseEntity.ok( graphService.getAllTagsNames());
    }

    @PostMapping("/tags/add/{tagName}")
    public ResponseEntity<TagGraph> addTag(@PathVariable String tagName) {
        return ResponseEntity.ok( graphService.addTag(tagName));
    }

    @PostMapping("/tags/connect/{tagName1}/{tagName2}")
    public ResponseEntity<TagGraph> connectTags(@PathVariable String tagName1, @PathVariable String tagName2) {
        return ResponseEntity.ok( graphService.connectTags(tagName1, tagName2));
    }

    @DeleteMapping("/tags/delete/{tagName}")
    public ResponseEntity<TagGraph> deleteTag(@PathVariable String tagName) {
        return ResponseEntity.ok( graphService.deleteTag(tagName));
    }











    @GetMapping("/test")
    public ResponseEntity<String> test() {
        List<TagGraph> tags = graphService.getAllTags();

//        TagGraph tag = graphService.test();


        return ResponseEntity.ok("test");
    }

}
