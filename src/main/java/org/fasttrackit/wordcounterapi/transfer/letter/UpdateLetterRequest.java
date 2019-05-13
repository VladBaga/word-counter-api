package org.fasttrackit.wordcounterapi.transfer.letter;

import javax.validation.constraints.NotBlank;

public class UpdateLetterRequest {

    @NotBlank
    private String letter;

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    @Override
    public String toString() {
        return "CreateLetterRequest{" +
                "letter='" + letter + '\'' +
                '}';
    }
}
