package hr.fer.tel.server.rest.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.security.RolesAllowed;

import hr.fer.tel.server.rest.service.graph.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/rest2")
public class TagController {

    @Autowired
    private GraphService graphService;

    @GetMapping("/tags")
    @RolesAllowed("iot-read")
    public ResponseEntity<List<String>> getTags() {
        return ResponseEntity.ok( graphService.getAllTagsNames() );
    }

}
