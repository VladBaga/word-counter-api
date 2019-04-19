package org.fasttrackit.wordcounterapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.wordcounterapi.domain.Word;
import org.fasttrackit.wordcounterapi.exception.ResourceNotFoundException;
import org.fasttrackit.wordcounterapi.persistence.WordRepository;
import org.fasttrackit.wordcounterapi.transfer.word.CreateWordRequest;
import org.fasttrackit.wordcounterapi.transfer.word.UpdateWordRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WordService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WordService.class);

    private final WordRepository wordRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public WordService(WordRepository wordRepository, ObjectMapper objectMapper) {
        this.wordRepository = wordRepository;
        this.objectMapper = objectMapper;
    }

    public Word createWord(CreateWordRequest request){
        LOGGER.info("Creating word {}", request);
        Word word = objectMapper.convertValue(request, Word.class);
        return wordRepository.save(word);
    }

    public Word getWord(long id) throws ResourceNotFoundException {
        LOGGER.info("Retrieving word {}", id);
        return wordRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Word" + id + "not found !"));
    }

    public Word updateWord(long id, UpdateWordRequest request) throws ResourceNotFoundException {
        LOGGER.info("Updating word {}, {}", id,request);
        Word word = getWord(id);

        BeanUtils.copyProperties(request, word);

        return wordRepository.save(word);
    }

    public  void deleteWord (long id){
        LOGGER.info("Deleting word {}", id);

        wordRepository.deleteById(id);

        LOGGER.info("Deleted word {}", id);
    }
}
