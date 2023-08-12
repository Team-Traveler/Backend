package com.example.traveler.controller;

import com.example.traveler.model.dto.CommentDTO;
import com.example.traveler.model.dto.PostDTO;
import com.example.traveler.model.entity.Comment;
import com.example.traveler.model.entity.Post;
import com.example.traveler.service.CommentService;
import com.example.traveler.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;
    private final CommentService commentService;

    @Autowired
    public PostController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody PostDTO postDTO) {
        Post post = postService.createPost(postDTO);
        return ResponseEntity.ok(post);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<Post> updatePost(@PathVariable Long postId, @RequestBody PostDTO postDTO) {
        Post updatedPost = postService.updatePost(postId, postDTO);
        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPost(@PathVariable Long postId) {
        Optional<Post> post = postService.getPost(postId);
        return post.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Post>> getPostList() {
        List<Post> posts = postService.getPostList();
        return ResponseEntity.ok(posts);
    }

    @PostMapping("/{postId}/likes")
    public ResponseEntity<Void> incrementLikes(@PathVariable Long postId) {
        postService.incrementLikes(postId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Post>> searchPostsByHashtag(@RequestParam String hashtag) {
        List<Post> posts = postService.searchPostsByHashtag(hashtag);
        return ResponseEntity.ok(posts);
    }

    @PostMapping("/{postId}/comments")
    public ResponseEntity<Comment> createComment(@PathVariable Long postId, @RequestBody CommentDTO commentDTO) {
        Comment comment = commentService.createComment(postId, commentDTO);
        return ResponseEntity.ok(comment);
    }

    @GetMapping("/{postId}/comments")
    public ResponseEntity<List<Comment>> getCommentsByPostId(@PathVariable Long postId) {
        List<Comment> comments = commentService.getCommentsByPostId(postId);
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<Comment> getComment(@PathVariable Long postId, @PathVariable Long commentId) {
        Comment comment = commentService.getComment(postId, commentId);
        return ResponseEntity.ok(comment);
    }

    @PutMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long postId, @PathVariable Long commentId, @RequestBody CommentDTO commentDTO) {
        Comment updatedComment = commentService.updateComment(postId, commentId, commentDTO);
        return ResponseEntity.ok(updatedComment);
    }

    @DeleteMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long postId, @PathVariable Long commentId) {
        commentService.deleteComment(postId, commentId);
        return ResponseEntity.noContent().build();
    }
}
