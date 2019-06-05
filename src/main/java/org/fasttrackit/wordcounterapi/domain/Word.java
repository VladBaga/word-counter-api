package org.fasttrackit.wordcounterapi.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private Integer numberOfWords;

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

    @Bean
    public ConversionService conversionService() {
        return new DefaultConversionService();
    }

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

    public Integer getNumberOfWords() {
        return numberOfWords;
    }

    public void setNumberOfWords(Integer numberOfWords) {
        this.numberOfWords = numberOfWords;
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
                Objects.equals(numberOfWords, word1.numberOfWords) &&
                Objects.equals(letters, word1.letters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, word, numberOfWords, letters);
    }
}