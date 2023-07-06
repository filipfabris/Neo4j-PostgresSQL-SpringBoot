package hr.fer.tel.server.rest.controller;

import java.util.Collection;
import java.util.stream.Collectors;

import javax.annotation.security.RolesAllowed;

import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import hr.fer.tel.server.rest.model.Key;
import hr.fer.tel.server.rest.model.ShortKeyDTO;
import hr.fer.tel.server.rest.service.KeyService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/rest2")
public class KeysController {

  @Autowired
  private KeyService keyService;

  @GetMapping("/keys")
  public ResponseEntity<Collection<ShortKeyDTO>> getKeys() {
    Set<ShortKeyDTO> result = keyService.getAll().stream()
      .map(k -> k.toDTO())
      .collect(Collectors.toSet());

    return ResponseEntity.ok(result);
  }
  
	@RolesAllowed("iot-read")
	@GetMapping("/keys2")
	public ResponseEntity<Collection<ShortKeyDTO>> getKeys2() {
	  Set<ShortKeyDTO> result = keyService.getAll().stream()
	    .map(k -> k.toDTO())
	    .collect(Collectors.toSet());
	
	  return ResponseEntity.ok(result);
	}
  
	@DeleteMapping("/key/{token}")
	public ResponseEntity<String> deleteKey(@PathVariable("token") String keyName){
		
		if(keyService.checkIfExists(keyName) == false) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		boolean isDeleted = keyService.ProbaDeleteKeyById(keyName);
		
		//Check if deleted
		if(isDeleted == false) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(keyName);

	}
	
	@DeleteMapping("/key2/{token}")
	@RolesAllowed("iot-read")
	public ResponseEntity<String> deleteKey2(@PathVariable("token") String token){
		
		if(keyService.checkIfExists(token) == false) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		boolean isDeleted = keyService.ProbaDeleteKeyById(token);
		
		//Check if deleted
		if(isDeleted == false) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(token);

	}
	
	@PutMapping("/key/{oldKeyName}")
	public ResponseEntity<String> editKey(@RequestBody String newKeyToken, @PathVariable("oldKeyName") String oldKeyName){
		
		//Check if scene exists
		if(keyService.checkIfExists(oldKeyName) == false) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		boolean isEdited = keyService.editKey(oldKeyName, newKeyToken);
		
		if(isEdited == false) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

		return ResponseEntity.status(HttpStatus.OK).body(newKeyToken);
	}
	
	@RolesAllowed("iot-read")
	@PutMapping("/key2/{token}")
	public ResponseEntity<String> editKey2(@RequestBody String newToken, @PathVariable("token") String oldToken){
		
		//Check if scene exists
		if(keyService.checkIfExists(oldToken) == false) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		boolean isEdited = keyService.editKey(oldToken, newToken);
		
		if(isEdited == false) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

		return ResponseEntity.status(HttpStatus.OK).body(newToken);
	}
	
	@PostMapping("/keys")
	public ResponseEntity<String> addKey(@RequestBody Key key){
		
		//Check if scene exists
		if(keyService.checkIfExists(key.getValue()) == true) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("key already exists");
		}
				
		keyService.ProbaAdd(key);

		return ResponseEntity.status(HttpStatus.OK).body(key.getValue());
	}
	
	@RolesAllowed("iot-read")
	@PostMapping("/keys2")
	public ResponseEntity<String> addKey2(@RequestBody Key key){
		
		//Check if scene exists
		if(keyService.checkIfExists(key.getValue()) == true) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("key already exists");
		}
				
		keyService.ProbaAdd(key);

		return ResponseEntity.status(HttpStatus.OK).body(key.getValue());
	}
	
	

}
