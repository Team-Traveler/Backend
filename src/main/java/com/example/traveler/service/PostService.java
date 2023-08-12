package com.example.traveler.service;
//비즈니스 로직처리

import com.example.traveler.model.dto.PostDTO;
import com.example.traveler.model.entity.Post;
import com.example.traveler.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PostService {
    private final PostRepository postRepository;

    // PostService 생성자에 PostRepository를 주입받도록 설정
    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    /**
     * 글 작성 메서드
     *
     * @param postDTO 작성할 글 정보를 담은 PostDTO 객체
     * @return 작성된 글 객체
     */
    public Post createPost(PostDTO postDTO) {
        Post post = new Post(
                postDTO.getTitle(),
                postDTO.getHashtags(),
                postDTO.getOneLineReview(),
                postDTO.getRating(),
                postDTO.getLocation(),
                postDTO.getTravelTheme(),
                postDTO.getTravelIntensity(),
                postDTO.getTravelWith(),
                postDTO.getGoodPoints(),
                postDTO.getBadPoints()
        );
        return postRepository.save(post);
    }


    /**
     * 글 상세보기 메서드
     *
     * @param postId 글 ID
     * @return 주어진 글 ID에 해당하는 글 객체를 Optional로 반환
     */
    public Optional<Post> getPost(Long postId) { // 새로운 글 객체 생성
        return postRepository.findById(postId);
        // PostRepository를 통해 글을 데이터베이스에 저장하고, 저장된 글 객체 반환
    }

    /**
     * 글 목록 조회 메서드
     *
     * @return 최신순으로 정렬된 모든 글 객체의 목록
     */
    public List<Post> getPostList() {
        // PostRepository를 통해 주어진 글 ID에 해당하는 글 객체를 조회하여 Optional로 반환
        return postRepository.findAllByOrderByCreatedAtDesc();
    }


    public Post updatePost(Long postId, PostDTO postDTO) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.setTitle(postDTO.getTitle());
            post.setHashtags(postDTO.getHashtags());
            post.setOneLineReview(postDTO.getOneLineReview());
            post.setRating(postDTO.getRating());
            post.setLocation(postDTO.getLocation());
            post.setTravelTheme(postDTO.getTravelTheme());
            post.setTravelIntensity(postDTO.getTravelIntensity());
            post.setTravelWith(postDTO.getTravelWith());
            post.setGoodPoints(postDTO.getGoodPoints());
            post.setBadPoints(postDTO.getBadPoints());
            return postRepository.save(post);
        } else {
            throw new IllegalArgumentException("해당 글이 존재하지 않습니다.");
        }
    }

    /**
     * 글 삭제 메서드
     *
     * @param postId 삭제할 글의 ID
     */
    public void deletePost(Long postId) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()) {
            postRepository.deleteById(postId);
        } else {
            throw new IllegalArgumentException("해당 글이 존재하지 않습니다.");
        }
    }
    public List<Post> searchPostsByHashtag(String hashtag) {
        return postRepository.findByHashtagsContaining(hashtag);
    }
    /**
     * 좋아요 수 증가 메서드
     *
     * @param postId 좋아요 수를 증가시킬 글의 ID
     */
    public void incrementLikes(Long postId) {
        postRepository.incrementLikes(postId);
    }
}



