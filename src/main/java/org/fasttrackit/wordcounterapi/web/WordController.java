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
@CrossOrigin
public class WordController {

    private final WordService wordService;

    @Autowired
    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Word> getWord(@PathVariable("id") long id) throws ResourceNotFoundException {
        Word response = wordService.getWord(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Word> createWord(@RequestBody @Valid CreateWordRequest request) {
        Word response = wordService.createWord(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateWord(@PathVariable("id") long id, @RequestBody @Valid UpdateWordRequest request) throws ResourceNotFoundException {
        wordService.updateWord(id, request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteWord(@PathVariable("id") long id) {
        wordService.deleteWord(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/word")
    public ResponseEntity<String> getWordCount(@RequestParam("wordCount") int wordCount) {

        return new ResponseEntity<>("Words :" + wordCount, HttpStatus.OK);
    }
}
