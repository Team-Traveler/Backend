package com.example.traveler.model.entity;

// 열거형(Enum)으로 여행 강도 정의
public enum TravelIntensity {
    STRONG("강하게"),
    WEAK("약하게"),
    LEISURELY("여유롭게");

    private final String displayName; // 여행 강도의 표시 이름

    TravelIntensity(String displayName) {
        this.displayName = displayName;
    }

    // 여행 강도의 표시 이름 반환
    public String getDisplayName() {
        return displayName;
    }
}
