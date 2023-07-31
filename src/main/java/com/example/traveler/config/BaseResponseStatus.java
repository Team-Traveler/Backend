package com.example.traveler.config;

import lombok.Getter;

/**
 * 에러 코드 관리
 */
@Getter
public enum BaseResponseStatus {
    /**
     * 1000 : 요청 성공
     */
    SUCCESS(true, 1000, "요청에 성공하였습니다."),


    /**
     * 2000 : Request 오류
     */
    // Common
    INVALID_JWT(false, 2000, "유효하지 않은 JWT입니다."),
    EMPTY_JWT(false,2001,"JWT를 입력하세요"),

    // users
    USERS_EMPTY_USER_ID(false, 2010, "유저 아이디 값을 확인해주세요."),

    // [POST] /users
    POST_USERS_EMPTY_EMAIL(false, 2015, "이메일을 입력해주세요."),
    POST_USERS_INVALID_EMAIL(false, 2016, "이메일 형식을 확인해주세요."),
    POST_USERS_EXISTS_EMAIL(false,2017,"중복된 이메일입니다."),



    /**
     * 3000 : Response 오류
     */
    // Common
    SAVE_TRAVEL_FAIL(false, 3001, "여행 생성에 실패했습니다."),
    TRAVEL_IS_EMPTY(false, 3002, "여행이 존재하지 않습니다."),
    DELETE_TRAVEL_FAIL(false, 3003, "여행 삭제에 실패했습니다."),
    PATCH_TRAVEL_FAIL(false, 3004, "여행 수정에 실패했습니다."),
    TRAVEL_USER_NOT_MATCH(false, 3005, "여행의 유저 정보가 일치하지 않습니다."),
    SAVE_DAYCOURSE_FAIL(false, 3006, "코스 생성에 실패했습니다."),
    DAYCOURSE_IS_EMPTY(false, 3007, "코스가 존재하지 않습니다."),
    DAYCOURSE_EXISTS(false, 3008, "코스가 이미 존재합니다."),

    // Post
    DELETE_POST_FAIL(false, 5000, "삭제에 실패했습니다."),

    /**
     * 4000 : Database, Server 오류
     */
    DATABASE_ERROR(false, 9000, "데이터베이스 연결에 실패하였습니다."),
    SERVER_ERROR(false, 4001, "서버와의 연결에 실패하였습니다."),

    //[PATCH] /users/{userIdx}
    MODIFY_FAIL_USERNAME(false,4014,"유저네임 수정 실패"),

    PASSWORD_ENCRYPTION_ERROR(false, 4011, "비밀번호 암호화에 실패하였습니다."),
    PASSWORD_DECRYPTION_ERROR(false, 4012, "비밀번호 복호화에 실패하였습니다.");


    // 5000 : 필요시 만들어서 쓰세요
    // 6000 : 필요시 만들어서 쓰세요


    private final boolean isSuccess;
    private final int code;
    private final String message;

    private BaseResponseStatus(boolean isSuccess, int code, String message) { //BaseResponseStatus 에서 각 해당하는 코드를 생성자로 맵핑
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}