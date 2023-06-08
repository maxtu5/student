package telran.java47.messageboard.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.java47.messageboard.dto.NewCommentDto;
import telran.java47.messageboard.dto.NewPostDto;
import telran.java47.messageboard.dto.PeriodDto;
import telran.java47.messageboard.dto.PostDto;
import telran.java47.messageboard.service.MessageboardService;

@RestController
@RequiredArgsConstructor
public class MessageboardController {

	final MessageboardService messageboardService;

	@PostMapping("/forum/post/{name}")
	public PostDto addPost(@PathVariable String name, @RequestBody NewPostDto newPostDto) {
		return messageboardService.addPost(name, newPostDto);
	}
	
	@GetMapping("/forum/post/{id}")
	public PostDto findPost(@PathVariable String id) {
		return messageboardService.findPost(id);
	}
	
	@PutMapping("/forum/post/{id}/like")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void likePost(@PathVariable String id) {
		messageboardService.likePost(id);
	}
	
	@GetMapping("/forum/posts/author/{author}")
	public List<PostDto> findPostsByAuthor(@PathVariable String author) {
		return messageboardService.findPostsByAuthor(author);
	}
	
	@PutMapping("/forum/post/{id}/comment/{user}")
	public PostDto commentPost(@PathVariable String id, @PathVariable String user, @RequestBody NewCommentDto comment) {
		return messageboardService.commentPost(id, user, comment);
	}

	@DeleteMapping("/forum/post/{id}")
	public PostDto deletePost(@PathVariable String id) {
		return messageboardService.deletePost(id);
	}
	
	@PostMapping("/forum/posts/period")
	List<PostDto> findPostsByPeriod(@RequestBody PeriodDto period) {
		return messageboardService.findPostsByPeriod(period);
	}
	
	@PutMapping("/forum/post/{id}")
	public PostDto updatePost(@PathVariable String id, @RequestBody NewPostDto newPostDto) {
		return messageboardService.updatePost(id, newPostDto);
	}
	
	@PostMapping("/forum/posts/tags")
	List<PostDto> findPostsByTags(@RequestBody List<String> tags) {
		return messageboardService.findPostsByTags(tags);

	}

}
