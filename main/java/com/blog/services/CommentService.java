package com.blog.services;

import com.blog.payloads.CommentDto;

public interface CommentService {
	CommentDto createComment(CommentDto commentDto,Integer userId,Integer postId);
	CommentDto updateComment(CommentDto commentDto,String comment,Integer userId,Integer postId);
	void deleteComment(String comment,Integer userId,Integer postId);

}
