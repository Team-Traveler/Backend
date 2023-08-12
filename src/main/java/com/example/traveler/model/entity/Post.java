package com.example.traveler.model.entity;
//게시글 정보

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity // JPA 엔티티 클래스를 나타내는 어노테이션
public class Post {
    @Id // 기본키(PK)
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동 생성되는 PK 값을 지정
    private Long id;

    @Column(nullable = false) // 필드와 매핑되는 테이블의 컬럼 속성을 설정
    private String title;

    @Column(nullable = false)
    private String content;

    @ElementCollection
    @CollectionTable(name = "hashtags", joinColumns = @JoinColumn(name = "post_id"))
    @Column(name = "hashtag")
    private List<String> hashtags;

    @Column(nullable = false)
    private String oneLineReview;

    @Column(nullable = false)
    private int rating;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TravelTheme travelTheme;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TravelIntensity travelIntensity;

    @Column(nullable = false)
    private String travelWith;

    @Column(nullable = false)
    private String goodPoints;

    @Column(nullable = false)
    private String badPoints;


    @Column(nullable = false)
    private int likes = 0; // 좋아요 수 기본값 0으로 설정

    @Column(nullable = false)
    private LocalDateTime createdAt; // 작성일시

    // 댓글과 게시글은 일대다 관계 (하나의 게시글에 여러 개의 댓글)
    //일대다(One-to-Many) 관계 => 하나의 게시글에 여러 개의 댓글이 속하는 관계
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();


    // 기본 생성자
    public Post() {
        this.createdAt = LocalDateTime.now(); // 작성일시 기본값으로 현재 시간 설정
    }

    // 모든 필드를 받는 생성자
    public Post(String title, List<String> hashtags, String oneLineReview, int rating, String location,
                TravelTheme travelTheme, TravelIntensity travelIntensity, String travelWith,
                String goodPoints, String badPoints) {
        this.title = title;
        this.hashtags = hashtags;
        this.oneLineReview = oneLineReview;
        this.rating = rating;
        this.location = location;
        this.travelTheme = travelTheme;
        this.travelIntensity = travelIntensity;
        this.travelWith = travelWith;
        this.goodPoints = goodPoints;
        this.badPoints = badPoints;
        this.createdAt = LocalDateTime.now();
    }

    // Getter, Setter
    /**
     * 게시글 ID를 반환하는 메서드
     *
     * @return 게시글 ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 게시글 ID를 설정하는 메서드
     *
     * @param id 게시글 ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 게시글 제목을 반환하는 메서드
     *
     * @return 게시글 제목
     */
    public String getTitle() {
        return title;
    }

    /**
     * 게시글 제목을 설정하는 메서드
     *
     * @param title 게시글 제목
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 게시글 내용을 반환하는 메서드
     *
     * @return 게시글 내용
     */
    public String getContent() {
        return content;
    }

    /**
            * 게시글 내용을 설정하는 메서드
     *
             * @param content 게시글 내용
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 좋아요 수를 반환하는 메서드
     *
     * @return 좋아요 수
     */
    public int getLikes() {
        return likes;
    }

    /**
     * 좋아요 수를 설정하는 메서드
     *
     * @param likes 좋아요 수
     */
    public void setLikes(int likes) {
        this.likes = likes;
    }

    /**
     * 게시글 작성일시를 반환하는 메서드
     *
     * @return 게시글 작성일시
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * 게시글에 속하는 댓글 리스트를 반환하는 메서드
     *
     * @return 댓글 리스트
     */
    public List<Comment> getComments() {
        return comments;
    }

    /**
     * 게시글에 속하는 댓글 리스트를 설정하는 메서드
     *
     * @param comments 게시글에 속하는 댓글 리스트
     */
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    /**
     * 게시글에 댓글을 추가하는 메서드
     *
     * @param comment 추가할 댓글 객체
     */
    // 댓글 추가 메서드
    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setPost(this);
    }
    /**
     * 게시글에서 댓글을 삭제하는 메서드
     *
     * @param comment 삭제할 댓글 객체
     */
    public void removeComment(Comment comment) {
        comments.remove(comment);
        comment.setPost(null);
    }

    // Post 클래스에 필요한 setter 메서드들
    public void setHashtags(List<String> hashtags) {
        this.hashtags = hashtags;
    }

    public void setOneLineReview(String oneLineReview) {
        this.oneLineReview = oneLineReview;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setTravelTheme(TravelTheme travelTheme) {
        this.travelTheme = travelTheme;
    }

    public void setTravelIntensity(TravelIntensity travelIntensity) {
        this.travelIntensity = travelIntensity;
    }

    public void setTravelWith(String travelWith) {
        this.travelWith = travelWith;
    }

    public void setGoodPoints(String goodPoints) {
        this.goodPoints = goodPoints;
    }

    public void setBadPoints(String badPoints) {
        this.badPoints = badPoints;
    }

    // toString 메서드
    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", likes=" + likes +
                ", comments=" + comments +
                '}';
    }
}
