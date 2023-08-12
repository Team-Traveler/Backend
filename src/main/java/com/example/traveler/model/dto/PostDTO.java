package com.example.traveler.model.dto;

import com.example.traveler.model.entity.TravelIntensity;
import com.example.traveler.model.entity.TravelTheme;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class PostDTO {
    private String title;
    private List<String> hashtags;
    private String oneLineReview;
    private int rating;
    private String location;
    private TravelTheme travelTheme;
    private TravelIntensity travelIntensity;
    private String travelWith;
    private String goodPoints;
    private String badPoints;
    private MultipartFile[] images;

    // Constructors
    public PostDTO() {
    }

    public PostDTO(String title, List<String> hashtags, String oneLineReview, int rating, String location,
                   TravelTheme travelTheme, TravelIntensity travelIntensity, String travelWith, String goodPoints,
                   String badPoints, MultipartFile[] images) {
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
        this.images = images;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<String> hashtags) {
        this.hashtags = hashtags;
    }

    public String getOneLineReview() {
        return oneLineReview;
    }

    public void setOneLineReview(String oneLineReview) {
        this.oneLineReview = oneLineReview;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public TravelTheme getTravelTheme() {
        return travelTheme;
    }

    public void setTravelTheme(TravelTheme travelTheme) {
        this.travelTheme = travelTheme;
    }

    public TravelIntensity getTravelIntensity() {
        return travelIntensity;
    }

    public void setTravelIntensity(TravelIntensity travelIntensity) {
        this.travelIntensity = travelIntensity;
    }

    public String getTravelWith() {
        return travelWith;
    }

    public void setTravelWith(String travelWith) {
        this.travelWith = travelWith;
    }

    public String getGoodPoints() {
        return goodPoints;
    }

    public void setGoodPoints(String goodPoints) {
        this.goodPoints = goodPoints;
    }

    public String getBadPoints() {
        return badPoints;
    }

    public void setBadPoints(String badPoints) {
        this.badPoints = badPoints;
    }

    public MultipartFile[] getImages() {
        return images;
    }

    public void setImages(MultipartFile[] images) {
        this.images = images;
    }
}

