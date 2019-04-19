package org.fasttrackit.wordcounterapi.transfer.character;

import javax.validation.constraints.NotBlank;

public class GetCharacterRequest {

    @NotBlank
    private String character;

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    @Override
    public String toString() {
        return "CreateCharacterRequest{" +
                "character='" + character + '\'' +
                '}';
    }
}
