package org.fasttrackit.wordcounterapi.transfer.word;

import javax.validation.constraints.NotBlank;

public class GetWordRequest {

    @NotBlank
    private String word;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public String toString() {
        return "CreateWordRequest{" +
                "word='" + word + '\'' +
                '}';
    }
}
