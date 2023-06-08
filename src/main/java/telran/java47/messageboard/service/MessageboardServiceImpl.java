package telran.java47.messageboard.service;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java47.messageboard.dao.MessageboardRepository;
import telran.java47.messageboard.dto.CommentDto;
import telran.java47.messageboard.dto.NewCommentDto;
import telran.java47.messageboard.dto.NewPostDto;
import telran.java47.messageboard.dto.PeriodDto;
import telran.java47.messageboard.dto.PostDto;
import telran.java47.messageboard.exceptions.PostNotFoundException;

@Service
@RequiredArgsConstructor
public class MessageboardServiceImpl implements MessageboardService {
	
	final MessageboardRepository messageboardRepository;
	final ModelMapper modelMapper;


	@Override
	public PostDto addPost(String name, NewPostDto newPostDto) {
		PostDto post = modelMapper.map(newPostDto, PostDto.class);
		post.setAuthor(name);
		post.setDateCreated(LocalDate.now());
		messageboardRepository.save(post);
		return post;
	}

	@Override
	public PostDto findPost(String id) {
		return messageboardRepository.findById(id).orElseThrow(() -> new PostNotFoundException());
	}

	@Override
	public void likePost(String id) {
		PostDto post = messageboardRepository.findById(id).orElseThrow(() -> new PostNotFoundException());
		post.setLikes(post.getLikes() + 1);
		messageboardRepository.save(post);
	}

	@Override
	public List<PostDto> findPostsByAuthor(String author) {
		return messageboardRepository.findAllByAuthor(author).get();
	}

	@Override
	public PostDto commentPost(String id, String user, NewCommentDto comment) {
		PostDto post = messageboardRepository.findById(id).orElseThrow(() -> new PostNotFoundException());
		CommentDto newComment = CommentDto.builder()
				.user(user)
				.message(comment.getMessage())
				.build();
		post.getComments().add(newComment);
		messageboardRepository.save(post);
		return post;
	}

	@Override
	public PostDto deletePost(String id) {
		PostDto post = messageboardRepository.findById(id).orElseThrow(() -> new PostNotFoundException());
		messageboardRepository.deleteById(id);
		return post;
	}

	@Override
	public List<PostDto> findPostsByPeriod(PeriodDto period) {
		return messageboardRepository.findAllByDateCreatedBetween(period.getDateFrom(), period.getDateTo()).get();
		
	}

	@Override
	public PostDto updatePost(String id, NewPostDto newPostDto) {
		PostDto post = messageboardRepository.findById(id).orElseThrow(() -> new PostNotFoundException());
		if (newPostDto.getTitle() != null) {
			post.setTitle(newPostDto.getTitle());
		}
		if (newPostDto.getContent() != null) {
			post.setContent(newPostDto.getContent());
		}
		newPostDto.getTags().stream()
			.filter(s -> !post.getTags().contains(s))
			.forEach(post.getTags()::add);
		messageboardRepository.save(post);
		return post;
	}

	@Override
	public List<PostDto> findPostsByTags(List<String> tags) {
		return messageboardRepository.findByTagsIn(tags).get();
	}

}
