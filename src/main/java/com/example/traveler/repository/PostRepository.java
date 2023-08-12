package com.example.traveler.repository;
import com.example.traveler.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    // 게시글 목록을 최신순으로 조회하는 메서드
    List<Post> findAllByOrderByCreatedAtDesc();
    List<Post> findByTitleContainingOrContentContaining(String keyword1, String keyword2); //인터페이스라
    List<Post> findAllByOrderByLikesDesc(); //자동 쿼리 생성되나..????
    // 좋아요 수 기준으로 글 목록을 내림차순으로 조회하는 메서드 구현 -> ??????
    // 좋아요 수 증가
    @Modifying
    @Query("UPDATE Post p SET p.likes = p.likes + 1 WHERE p.id = :postId")
    int incrementLikes(@Param("postId") Long postId);

    List<Post> findByHashtagsContaining(String hashtag);


}

