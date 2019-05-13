package org.fasttrackit.wordcounterapi.persistence;

import org.fasttrackit.wordcounterapi.domain.Letter;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LetterRepository extends PagingAndSortingRepository<Letter, Long> {
}
