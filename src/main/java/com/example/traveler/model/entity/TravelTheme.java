package com.example.traveler.model.entity;

// 열거형(Enum)으로 여행 테마 정의
public enum TravelTheme {
    DRIVE("드라이브"),
    FOOD("먹방"),
    ACTIVITY("액티비티");

    private final String displayName; // 여행 테마의 표시 이름

    TravelTheme(String displayName) {
        this.displayName = displayName;
    }

    // 여행 테마의 표시 이름 반환
    public String getDisplayName() {
        return displayName;
    }
}
