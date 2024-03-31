package com.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.ApiResponse;
import com.blog.payloads.CommentDto;
import com.blog.services.impl.CommentServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class CommentController {
	
	@Autowired
	private CommentServiceImpl commentServiceImpl;
	
	
	@PostMapping("/user/{uId}/post/{pId}/comment")
	public ResponseEntity<CommentDto> postComment(@Valid @RequestBody CommentDto cmt,@PathVariable Integer uId,@PathVariable Integer pId){
		
		CommentDto comment=this.commentServiceImpl.createComment(cmt,uId,pId);
		
		return new ResponseEntity<CommentDto>(comment,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/user/{uId}/post/{pId}/comment/{oldcomment}")
	public ResponseEntity<CommentDto> updateComment(@Valid @RequestBody CommentDto cmt,
			@PathVariable Integer uId,@PathVariable Integer pId,@PathVariable String oldcomment){
			
			CommentDto comment=this.commentServiceImpl.updateComment(cmt,oldcomment,uId,pId);
			
			return new ResponseEntity<CommentDto>(comment,HttpStatus.ACCEPTED);
		}
	
	@DeleteMapping("/user/{uId}/post/{pId}/comment/{oldcomment}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer uId,@PathVariable Integer pId,
			@PathVariable String oldcomment){
		this.commentServiceImpl.deleteComment(oldcomment, uId, pId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User with id"+uId+"has deleted Comment on post with postId"+pId,true),
				HttpStatus.ACCEPTED);
		
	}
			

}
