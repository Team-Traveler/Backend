package com.example.traveler.service;

import com.example.traveler.model.dto.CommentDTO;
import com.example.traveler.model.entity.Comment;
import com.example.traveler.model.entity.Post;
import com.example.traveler.repository.CommentRepository;
import com.example.traveler.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public Comment createComment(Long postId, CommentDTO commentDTO) {
        Post post = getPostById(postId);
        Comment comment = new Comment();
        comment.setContent(commentDTO.getContent());
        comment.setPost(post);
        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsByPostId(Long postId) {
        return commentRepository.findAllByPostIdOrderByCreatedAt(postId);
    }

    public Comment getComment(Long postId, Long commentId) {
        Post post = getPostById(postId);
        return commentRepository.findByIdAndPostId(commentId, post)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found with id: " + commentId));
    }

    public Comment updateComment(Long postId, Long commentId, CommentDTO commentDTO) {
        Comment comment = getComment(postId, commentId);
        comment.setContent(commentDTO.getContent());
        return commentRepository.save(comment);
    }

    public void deleteComment(Long postId, Long commentId) {
        Comment comment = getComment(postId, commentId);
        commentRepository.deleteById(commentId);
    }

    private Post getPostById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found with id: " + postId));
    }
}
