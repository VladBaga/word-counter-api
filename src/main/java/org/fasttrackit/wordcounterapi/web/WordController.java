package org.fasttrackit.wordcounterapi.web;

import org.fasttrackit.wordcounterapi.domain.Word;
import org.fasttrackit.wordcounterapi.exception.ResourceNotFoundException;
import org.fasttrackit.wordcounterapi.service.WordService;
import org.fasttrackit.wordcounterapi.transfer.word.CreateWordRequest;
import org.fasttrackit.wordcounterapi.transfer.word.UpdateWordRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/words")
public class WordController {

    private final WordService wordService;

    @Autowired
    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity <Word> getWord(@PathVariable("id") long id) throws ResourceNotFoundException {
        Word response = wordService.getWord(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity <Word> createWord(@RequestBody @Valid CreateWordRequest request){
        Word response = wordService.createWord(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity updateWord(@PathVariable("id") long id, @RequestBody @Valid UpdateWordRequest request) throws ResourceNotFoundException {
        wordService.updateWord(id, request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity deleteWord(@PathVariable("id") long id){
        wordService.deleteWord(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
