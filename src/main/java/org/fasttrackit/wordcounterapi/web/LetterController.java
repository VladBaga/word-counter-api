package org.fasttrackit.wordcounterapi.web;

import org.fasttrackit.wordcounterapi.domain.Letter;
import org.fasttrackit.wordcounterapi.exception.ResourceNotFoundException;
import org.fasttrackit.wordcounterapi.service.LetterService;
import org.fasttrackit.wordcounterapi.transfer.letter.CreateLetterRequest;
import org.fasttrackit.wordcounterapi.transfer.letter.UpdateLetterRequest;
import org.fasttrackit.wordcounterapi.transfer.word.CreateWordRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/letters")
public class LetterController {

    private final LetterService letterService;

    @Autowired
    public LetterController(LetterService letterService) {
        this.letterService = letterService;
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Letter> getLetter(@PathVariable("id") long id) throws ResourceNotFoundException {
        Letter response = letterService.getLetter(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Letter> createLetter(@RequestBody @Valid CreateLetterRequest request){
        Letter response = letterService.createLetter(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity updateLetter(@PathVariable("id") long id, @RequestBody @Valid UpdateLetterRequest request) throws ResourceNotFoundException {
        letterService.updateLetter(id, request);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity deleteLetter(@PathVariable("id") long id){
        letterService.deleteLetter(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
