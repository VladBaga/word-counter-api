package org.fasttrackit.wordcounterapi;


import org.fasttrackit.wordcounterapi.domain.Letter;
import org.fasttrackit.wordcounterapi.exception.ResourceNotFoundException;
import org.fasttrackit.wordcounterapi.service.LetterService;
import org.fasttrackit.wordcounterapi.transfer.letter.CreateLetterRequest;
import org.fasttrackit.wordcounterapi.transfer.letter.GetLetterRequest;
import org.fasttrackit.wordcounterapi.transfer.letter.UpdateLetterRequest;
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
public class LetterServiceIntegrationTests {

    @Autowired
    private LetterService letterService;

    @Test
    public void testCreateLetter_whenValidRequest_thenReturnLetterWithId(){
        Letter letter = createLetter();

        assertThat(letter, notNullValue());
        assertThat(letter.getId(), greaterThan(0L));
    }

    private Letter createLetter() {
        CreateLetterRequest request = new CreateLetterRequest();
        request.setLetter("abcd");

        return letterService.createLetter(request);
    }

    @Test (expected = ResourceNotFoundException.class)
    public void testGetLetter_whenLetterNotFound_thenThrowResourceNotFoundException() throws ResourceNotFoundException {
        letterService.getLetter(0);
    }

    @Test
    public void testUpdateLetter_whenValidRequestWithAllFields_thenReturnUpdatedLetter() throws ResourceNotFoundException { //ORICE TEST ESTE INDEPENDENT
        Letter createdLetter = createLetter();

        UpdateLetterRequest request = new UpdateLetterRequest();
        request.setLetter(createdLetter.getLetter() + " Edited");

        Letter updatedLetter = letterService.updateLetter(createdLetter.getId(), request);

        assertThat(updatedLetter.getLetter(), is(request.getLetter()));
        assertThat(updatedLetter.getLetter(), not(is(createdLetter.getLetter())));

        assertThat(updatedLetter.getId(), is(createdLetter.getId()));
    }
    // todo: Implement negative tests for update and tests for update with some fields only.

    @Test (expected = ResourceNotFoundException.class)
    public void testDeleteLetter_whenExistingId_thenLetterIsDeleted() throws ResourceNotFoundException {
        Letter createdLetter = createLetter();

        letterService.deleteLetter(createdLetter.getId());

        letterService.getLetter(createdLetter.getId());
    }
}
