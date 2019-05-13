package org.fasttrackit.wordcounterapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.wordcounterapi.domain.Letter;
import org.fasttrackit.wordcounterapi.exception.ResourceNotFoundException;
import org.fasttrackit.wordcounterapi.persistence.LetterRepository;
import org.fasttrackit.wordcounterapi.transfer.letter.CreateLetterRequest;
import org.fasttrackit.wordcounterapi.transfer.letter.UpdateLetterRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LetterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LetterService.class);

    private final LetterRepository letterRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public LetterService(LetterRepository letterRepository, ObjectMapper objectMapper) {
        this.letterRepository = letterRepository;
        this.objectMapper = objectMapper;
    }

    public Letter createLetter(CreateLetterRequest request){
        LOGGER.info("Creating letter {}", request);
        Letter letter = objectMapper.convertValue(request, Letter.class);
        return letterRepository.save(letter);
    }

    public Letter getLetter(long id) throws ResourceNotFoundException {
        LOGGER.info("Retrieving letter {}", id);
        return letterRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Letter" + id + "not found !"));
    }

    public Letter updateLetter(long id, UpdateLetterRequest request) throws ResourceNotFoundException {
        LOGGER.info("Updating letter {}, {}", id,request);
        Letter letter = getLetter(id);

        BeanUtils.copyProperties(request, letter);

        return letterRepository.save(letter);
    }

    public  void deleteLetter (long id){
        LOGGER.info("Deleting letter {}", id);

        letterRepository.deleteById(id);

        LOGGER.info("Deleted letter {}", id);
    }
}
