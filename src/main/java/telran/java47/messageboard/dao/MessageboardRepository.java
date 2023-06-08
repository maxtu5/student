package telran.java47.messageboard.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import telran.java47.messageboard.dto.PostDto;

public interface MessageboardRepository extends CrudRepository<PostDto, String> {
	Optional<List<PostDto>> findAllByAuthor(String author);

	Optional<List<PostDto>> findAllByDateCreatedBetween(LocalDate dateFrom, LocalDate dateTo);
	
	Optional<List<PostDto>> findByTagsIn(List<String> tags);
}
