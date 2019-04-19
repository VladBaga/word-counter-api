package org.fasttrackit.wordcounterapi.persistence;

import org.fasttrackit.wordcounterapi.domain.Character;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CharacterRepository extends PagingAndSortingRepository<Character, Long> {
}
