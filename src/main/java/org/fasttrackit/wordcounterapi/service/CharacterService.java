package org.fasttrackit.wordcounterapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.wordcounterapi.domain.Character;
import org.fasttrackit.wordcounterapi.exception.ResourceNotFoundException;
import org.fasttrackit.wordcounterapi.persistence.CharacterRepository;
import org.fasttrackit.wordcounterapi.transfer.character.CreateCharacterRequest;
import org.fasttrackit.wordcounterapi.transfer.character.UpdateCharacterRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CharacterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CharacterService.class);

    private final CharacterRepository characterRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public CharacterService(CharacterRepository characterRepository, ObjectMapper objectMapper) {
        this.characterRepository = characterRepository;
        this.objectMapper = objectMapper;
    }

    public Character createCharacter(CreateCharacterRequest request){
        LOGGER.info("Creating character {}", request);
        Character character = objectMapper.convertValue(request, Character.class);
        return characterRepository.save(character);
    }

    public Character getCharacter(long id) throws ResourceNotFoundException {
        LOGGER.info("Retrieving character {}", id);
        return characterRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Character" + id + "not found !"));
    }

    public Character updateCharacter(long id, UpdateCharacterRequest request) throws ResourceNotFoundException {
        LOGGER.info("Updating character {}, {}", id,request);
        Character character = getCharacter(id);

        BeanUtils.copyProperties(request, character);

        return characterRepository.save(character);
    }

    public  void deleteCharacter (long id){
        LOGGER.info("Deleting character {}", id);

        characterRepository.deleteById(id);

        LOGGER.info("Deleted character {}", id);
    }
}
