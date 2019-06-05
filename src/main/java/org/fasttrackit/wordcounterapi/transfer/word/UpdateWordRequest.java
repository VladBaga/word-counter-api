package org.fasttrackit.wordcounterapi.transfer.word;

import javax.validation.constraints.NotBlank;

public class UpdateWordRequest {

    @NotBlank
    private String word;


    private Integer numberOfWords;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Integer getNumberOfWords() {
        return numberOfWords;
    }

    public void setNumberOfWords(Integer numberOfWords) {
        this.numberOfWords = numberOfWords;
    }

    @Override
    public String toString() {
        return "UpdateWordRequest{" +
                "word='" + word + '\'' +
                ", numberOfWords=" + numberOfWords +
                '}';
    }
}
