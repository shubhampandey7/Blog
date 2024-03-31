package com.blog.payloads;





import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CommentDto {
	
	@NotEmpty
	@Size(min=3,message="min/max size 3-15")
	private String content;
	private UserDto user;
	private PostDto post;

}
