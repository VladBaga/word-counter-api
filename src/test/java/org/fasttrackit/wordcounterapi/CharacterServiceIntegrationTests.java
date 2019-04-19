package org.fasttrackit.wordcounterapi;


import org.fasttrackit.wordcounterapi.domain.Character;
import org.fasttrackit.wordcounterapi.exception.ResourceNotFoundException;
import org.fasttrackit.wordcounterapi.service.CharacterService;
import org.fasttrackit.wordcounterapi.transfer.character.CreateCharacterRequest;
import org.fasttrackit.wordcounterapi.transfer.character.GetCharacterRequest;
import org.fasttrackit.wordcounterapi.transfer.character.UpdateCharacterRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CharacterServiceIntegrationTests {

    @Autowired
    private CharacterService characterService;

    @Test
    public void testCreateCharacter_whenValidRequest_thenReturnCharacterWithId(){
        Character character = createCharacter();

        assertThat(character, notNullValue());
        assertThat(character.getId(), greaterThan(0L));
    }

    private Character createCharacter() {
        CreateCharacterRequest request = new CreateCharacterRequest();
        request.setCharacter("Laptop");

        return characterService.createCharacter(request);
    }

    @Test (expected = ResourceNotFoundException.class)
    public void testGetCharacter_whenCharacterNotFound_thenThrowResourceNotFoundException() throws ResourceNotFoundException {
        characterService.getCharacter(0);
    }

    @Test
    public void testUpdateCharacter_whenValidRequestWithAllFields_thenReturnUpdatedCharacter() throws ResourceNotFoundException { //ORICE TEST ESTE INDEPENDENT
        Character createdCharacter = createCharacter();

        UpdateCharacterRequest request = new UpdateCharacterRequest();
        request.setCharacter(createdCharacter.getCharacter() + " Edited");

        Character updatedCharacter = characterService.updateCharacter(createdCharacter.getId(), request);

        assertThat(updatedCharacter.getCharacter(), is(request.getCharacter()));
        assertThat(updatedCharacter.getCharacter(), not(is(createdCharacter.getCharacter())));

        assertThat(updatedCharacter.getId(), is(createdCharacter.getId()));
    }
    // todo: Implement negative tests for update and tests for update with some fields only.

    @Test (expected = ResourceNotFoundException.class)
    public void testDeleteCharacter_whenExistingId_thenCharacterIsDeleted() throws ResourceNotFoundException {
        Character createdCharacter = createCharacter();

        characterService.deleteCharacter(createdCharacter.getId());

        characterService.getCharacter(createdCharacter.getId());
    }
}
