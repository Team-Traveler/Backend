package com.example.traveler.repository;

import com.example.traveler.model.entity.Comment;
import com.example.traveler.model.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByPostIdOrderByCreatedAt(Long postId);

    Optional<Comment> findByIdAndPostId(Long commentId, Post post);

    void deleteById(Long commentId);

    List<Comment> findByContentContaining(String keyword);

    Page<Comment> findAllByPostId(Long postId, Pageable pageable);
}


