package com.blog.services.impl;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.Comment;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.CommentDto;
import com.blog.repositories.CommentRepo;
import com.blog.repositories.PostRepo;
import com.blog.repositories.UserRepo;
import com.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper mdelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private PostRepo postRepo;

	@Override
	public CommentDto createComment(CommentDto commentDto,Integer userId,Integer postId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User",userId));
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post",postId));
		Comment comment=this.mdelMapper.map(commentDto, Comment.class);
		comment.setAddedDate(new Date());
		comment.setPost(post);
		comment.setUser(user);
		Comment savedComment=this.commentRepo.save(comment);
		return this.mdelMapper.map(savedComment,CommentDto.class);
	}

	@Override
	public CommentDto updateComment(CommentDto commentDto,String cmt ,Integer userId, Integer postId) {
		
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User",userId));
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post",postId));
		Comment comment=this.commentRepo.findByContent(cmt);
		if(user.getId()==comment.getUser().getId() && comment.getPost().getPostId()==post.getPostId() ) {
			comment.setContent(cmt);
			comment.setAddedDate(new Date());
			comment.setPost(comment.getPost());
			comment.setUser(comment.getUser());
			System.out.println(comment.getPost());
			Comment savedComment=this.commentRepo.save(comment);
			return this.mdelMapper.map(savedComment,CommentDto.class);
			
		}else{
			new ResourceNotFoundException("No such comment by userId",userId);
		
		}
		return null;
	}

	@Override
	public void deleteComment(String comment, Integer userId, Integer postId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User",userId));
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post",postId));
		Comment cmt=this.commentRepo.findByContent(comment);
		if(user.getId()==cmt.getUser().getId() && post.getPostId()==cmt.getPost().getPostId()) {
			this.commentRepo.delete(cmt);
			
		}else{
			new ResourceNotFoundException("No such comment by userId",userId);
		
		}
		
		
	}

	

	
}
