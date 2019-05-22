package org.fasttrackit.wordcounterapi.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(min = 1)
    private String word;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "word_letter",
            joinColumns = @JoinColumn(name = "word_id"),
            inverseJoinColumns = @JoinColumn(name = "letter_id")
    )
    private Set<Letter> letters = new HashSet<>();

    public void addLetter(Letter letter) {
        letters.add(letter);
        letter.getWords().add(this);
    }

    public void removeLetter(Letter letter) {

        letters.remove(letter);
        letter.getWords().remove(this);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Set<Letter> getLetters() {
        return letters;
    }

    public void setLetters(Set<Letter> letters) {
        this.letters = letters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word1 = (Word) o;
        return id == word1.id &&
                Objects.equals(word, word1.word) &&
                Objects.equals(letters, word1.letters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, word, letters);
    }
}