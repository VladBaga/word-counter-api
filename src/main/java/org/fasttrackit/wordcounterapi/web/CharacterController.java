package org.fasttrackit.wordcounterapi.web;

import org.fasttrackit.wordcounterapi.domain.Character;
import org.fasttrackit.wordcounterapi.exception.ResourceNotFoundException;
import org.fasttrackit.wordcounterapi.service.CharacterService;
import org.fasttrackit.wordcounterapi.transfer.character.CreateCharacterRequest;
import org.fasttrackit.wordcounterapi.transfer.character.UpdateCharacterRequest;
import org.fasttrackit.wordcounterapi.transfer.word.CreateWordRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/characters")
public class CharacterController {

    private final CharacterService characterService;

    @Autowired
    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Character> getCharacter(@PathVariable("id") long id) throws ResourceNotFoundException {
        Character response = characterService.getCharacter(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Character> createCharacter(@RequestBody @Valid CreateCharacterRequest request){
        Character response = characterService.createCharacter(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCharacter(@PathVariable("id") long id, @RequestBody @Valid UpdateCharacterRequest request) throws ResourceNotFoundException {
        characterService.updateCharacter(id, request);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCharacter(@PathVariable("id") long id){
        characterService.deleteCharacter(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
