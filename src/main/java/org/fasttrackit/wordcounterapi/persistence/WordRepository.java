package org.fasttrackit.wordcounterapi.persistence;

import org.fasttrackit.wordcounterapi.domain.Word;
import org.springframework.data.repository.PagingAndSortingRepository;

//Long in wrapper for primitive long
public interface WordRepository extends PagingAndSortingRepository<Word, Long> {
}
