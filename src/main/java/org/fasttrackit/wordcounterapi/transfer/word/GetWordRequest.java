package org.fasttrackit.wordcounterapi.transfer.word;

import javax.validation.constraints.NotBlank;

public class GetWordRequest {

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
        return "GetWordRequest{" +
                "word='" + word + '\'' +
                ", numberOfWords=" + numberOfWords +
                '}';
    }
}
