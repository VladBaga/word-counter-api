package org.fasttrackit.wordcounterapi;

import org.fasttrackit.wordcounterapi.domain.Word;
import org.fasttrackit.wordcounterapi.exception.ResourceNotFoundException;
import org.fasttrackit.wordcounterapi.service.WordService;
import org.fasttrackit.wordcounterapi.transfer.word.CreateWordRequest;
import org.fasttrackit.wordcounterapi.transfer.word.UpdateWordRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WordServiceIntegrationTests {

    @Autowired
    private WordService wordService;

    @Test
    public void testCreateWord_whenValidRequest_thenReturnWordWithId() {
        Word word = createWord();

        assertThat(word, notNullValue());
        assertThat(word.getId(), greaterThan(0L));
    }

    private Word createWord() {
        CreateWordRequest request = new CreateWordRequest();
        request.setWord("Banana");

        return wordService.createWord(request);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testGetWord_whenWordNotFound_thenThrowResourceNotFoundException() throws ResourceNotFoundException {
        wordService.getWords(0);
    }

    @Test
    public void testUpdateWord_whenValidRequestWithAllFields_thenReturnUpdatedWord() throws ResourceNotFoundException { //ORICE TEST ESTE INDEPENDENT
        Word createdWord = createWord();

        UpdateWordRequest request = new UpdateWordRequest();
        request.setWord(createdWord.getWord() + " Edited");

        Word updatedWord = wordService.updateWord(createdWord.getId(), request);

        assertThat(updatedWord.getWord(), is(request.getWord()));
        assertThat(updatedWord.getWord(), not(is(createdWord.getWord())));

        assertThat(updatedWord.getId(), is(createdWord.getId()));
    }
    // todo: Implement negative tests for update and tests for update with some fields only.

    @Test(expected = ResourceNotFoundException.class)
    public void testDeleteWord_whenExistingId_thenWordIsDeleted() throws ResourceNotFoundException {
        Word createdWord = createWord();

        wordService.deleteWord(createdWord.getId());

        wordService.getWords(createdWord.getId());
    }
}