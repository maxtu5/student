package telran.java47.messageboard.service;

import java.util.List;

import telran.java47.messageboard.dto.NewCommentDto;
import telran.java47.messageboard.dto.NewPostDto;
import telran.java47.messageboard.dto.PeriodDto;
import telran.java47.messageboard.dto.PostDto;

public interface MessageboardService {

	PostDto addPost(String name, NewPostDto newPostDto);

	PostDto findPost(String id);

	void likePost(String id);

	List<PostDto> findPostsByAuthor(String author);

	PostDto commentPost(String id, String user, NewCommentDto comment);
	
	PostDto deletePost(String id);

	List<PostDto> findPostsByPeriod(PeriodDto period);

	PostDto updatePost(String id, NewPostDto newPostDto);
	
	List<PostDto> findPostsByTags(List<String> tags);

}
