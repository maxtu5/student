package telran.java47.messageboard.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDto {
	String user;
	String message;
	@Builder.Default 
	LocalDate dateCreated = LocalDate.now();
	@Builder.Default 
	int likes = 0;
}
