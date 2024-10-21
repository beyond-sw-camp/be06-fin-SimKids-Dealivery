package org.example.auth.global.constants;

import lombok.Getter;

@Getter
public enum BaseResponseStatus {
    // 모든 요청 성공 1000
    SUCCESS(true, 1000, "요청이 성공하였습니다."),

    // 회원 기능 2000
    USER_SIGNUP_FAIL(false, 2000, "회원가입에 실패했습니다."),
    USER_SIGNUP_FAIL_EMPTY_NAME(false, 2001, "회원가입에 실패했습니다. 이름을 입력해주세요."),
    USER_SIGNUP_FAIL_EMPTY_EMAIL(false, 2002, "회원가입에 실패했습니다. 이메일을 입력해주세요."),
    USER_SIGNUP_FAIL_EMPTY_PASSWORD(false, 2003, "회원가입에 실패했습니다. 비밀번호를 입력해주세요."),
    USER_SIGNUP_FAIL_EMPTY_PHONE_NUMBER(false, 2004, "회원가입에 실패했습니다. 휴대폰 번호를 입력해주세요."),
    USER_SIGNUP_FAIL_EMPTY_POST_NUMBER(false, 2005, "회원가입에 실패했습니다. 우편번호를 입력해주세요."),
    USER_SIGNUP_FAIL_EMPTY_ADDRESS_DETAIL(false, 2006, "회원가입에 실패했습니다. 상세주소를 입력해주세요."),
    USER_SIGNUP_FAIL_INVALID_NAME(false, 2007, "회원가입에 실패했습니다. 유효한 이름을 입력해주세요."),
    USER_SIGNUP_FAIL_INVALID_EMAIL(false, 2008, "회원가입에 실패했습니다. 유효한 이메일을 입력해주세요."),
    USER_SIGNUP_FAIL_INVALID_PASSWORD(false, 2009, "회원가입에 실패했습니다. 유효한 비밀번호를 입력해주세요."),
    USER_SIGNUP_FAIL_INVALID_PHONE_NUMBER(false, 2010, "회원가입에 실패했습니다. 유효한 휴대폰 번호를 입력해주세요."),
    USER_SIGNUP_FAIL_INVALID_POST_NUMBER(false, 2011, "회원가입에 실패했습니다. 유효한 우편번호를 입력해주세요."),
    USER_SIGNUP_FAIL_INVALID_ADDRESS_DETAIL(false, 2012, "회원가입에 실패했습니다. 유효한 상세주소를 입력해주세요."),
    USER_SIGNUP_FAIL_INVALID_EMAIL_CODE(false, 2058, "회원가입에 실패했습니다. 이메일 인증 코드가 만료되었거나 일치하지 않습니다."),

    USER_LOGIN_FAIL(false, 2013, "로그인에 실패했습니다. 아이디 또는 비밀번호를 확인해주세요."),
    USER_LOGIN_FAIL_EMPTY_EMAIL(false, 2014, "로그인에 실패했습니다. 아이디를 입력해주세요."),
    USER_LOGIN_FAIL_EMPTY_PASSWORD(false, 2015, "로그인에 실패했습니다. 비밀번호를 입력해주세요."),
    USER_LOGIN_FAIL_UNAUTHORIZED_EMAIL(false, 2016, "로그인에 실패했습니다. 인증되지 않은 이메일입니다."),

    USER_EDIT_FAIL_INVALID_NAME(false, 2017, "회원정보 수정에 실패했습니다. 유효한 이름을 입력해주세요."),
    USER_EDIT_FAIL_INVALID_PASSWORD(false, 2018, "회원정보 수정에 실패했습니다. 유효한 비밀번호를 입력해주세요."),
    USER_EDIT_FAIL_INVALID_PHONE_NUMBER(false, 2019, "회원정보 수정에 실패했습니다. 유효한 휴대폰 번호를 입력해주세요."),
    USER_EDIT_FAIL_INVALID_POST_NUMBER(false, 2020, "회원정보 수정에 실패했습니다. 유효한 우편번호를 입력해주세요."),
    USER_EDIT_FAIL_INVALID_ADDRESS(false, 2021, "회원정보 수정에 실패했습니다. 유효한 주소를 입력해주세요."),
    USER_EDIT_FAIL_INVALID_ADDRESS_DETAIL(false, 2022, "회원정보 수정에 실패했습니다. 유효한 상세주소를 입력해주세요."),

    USER_FIND_ID_FAIL_NO_MATCH_EMAIL(false, 2023, "아이디 찾기에 실패했습니다. 일치하는 아이디가 없습니다."),
    USER_FIND_ID_FAIL_EMPTY_NAME(false, 2024, "아이디 찾기에 실패했습니다. 이름을 입력해주세요."),
    USER_FIND_ID_FAIL_EMPTY_PHONE_NUMBER(false, 2025, "아이디 찾기에 실패했습니다. 휴대폰 번호를 입력해주세요."),
    USER_FIND_PASSWORD_FAIL(false, 2026, "비밀번호 찾기에 실패했습니다."),
    USER_RESET_PASSWORD_FAIL(false, 2056, "비밀번호 재설정에 실패했습니다."),
    USER_FIND_PASSWORD_FAIL_EMAIL_VERIFY_FAIL(false, 2027, "비밀번호 찾기에 실패했습니다. 입력하신 정보와 일치하는 회원정보가 없습니다."),

    USER_SIGNUP_FAIL_INVALID_REG_NUMBER(false, 2043, "회원가입에 실패했습니다. 유효한 사업자등록번호를 입력해주세요."),
    USER_SIGNUP_FAIL_INVALID_OPENED_AT(false, 2044, "회원가입에 실패했습니다. 유효한 개업일자를 입력해주세요."),
    USER_SIGNUP_FAIL_INVALID_MOS_NUMBER(false, 2045, "회원가입에 실패했습니다. 유효한 통신판매업신고번호를 입력해주세요."),
    USER_SIGNUP_FAIL_EMPTY_REG_NUMBER(false, 2046, "회원가입에 실패했습니다. 사업자등록번호를 입력해주세요."),
    USER_SIGNUP_FAIL_EMPTY_OPENED_AT(false, 2047, "회원가입에 실패했습니다. 개업일자를 입력해주세요."),
    USER_SIGNUP_FAIL_EMPTY_MOS_NUMBER(false, 2048, "회원가입에 실패했습니다. 통신판매업신고번호를 입력해주세요."),
    USER_SIGNUP_FAIL_UNAUTHORIZED_REG_NUMBER(false, 2049, "사업자등록번호 인증에 실패했습니다."),
    USER_SIGNUP_FAIL_UNAUTHORIZED_MOS_NUMBER(false, 2050, "통신판매업신호번호 인증에 실패했습니다."),

    EMAIL_VERIFY_FAIL(false, 2051, "이메일 인증요청에 실패했습니다."),
    USER_DETAIL_FAIL(false, 2052, "회원정보 조회에 실패했습니다."),
    USER_REMOVE_FAIL(false, 2053, "회원탈퇴에 실패했습니다."),
    USER_SOCIAL_LOGIN_FAIL_NEED_TO_SIGNUP(true, 2057, "추가 회원정보 입력이 필요합니다."),
    EMAIL_VERIFY_FAIL_EXPIRED(false, 2059, "이메일 인증에 실패했습니다. 인증코드가 만료되었습니다."),
    EMAIL_VERIFY_FAIL_INCORRECT(false, 2060, "이메일 인증에 실패했습니다. 인증코드가 일치하지 않습니다."),
    EMAIL_VERIFY_FAIL_CAN_NOT_CREATE(false, 2061, "인증코드를 생성할 수 없습니다. 이메일을 확인해주세요."),
    EMAIL_VERIFY_FAIL_CAN_NOT_SEND(false, 2061, "인증메일 발송에 실패했습니다."),
    USER_SIGNUP_FAIL_ALREADY_EXIST(false, 2062, "이미 가입된 이메일입니다."),
    USER_LOGIN_FAIL_UNAUTHORIZED_REG_NUMBER(false, 2063, "로그인에 실패했습니다. 사업자등록번호 인증 정보가 없는 회원입니다."),
    USER_DETAIL_FAIL_USER_NOT_FOUND(false, 2064, "회원정보 조회에 실패했습니다. 해당 정보로 가입된 회원정보가 없습니다."),
    USER_DELIVERY_EDIT_FAIL_USER_NOT_MATCH(false, 2065, "배송지 정보수정에 실패했습니다. 회원 정보가 올바르지 않습니다."),
    USER_DETAIL_EDIT_FAIL_USER_NOT_FOUND(false, 2066, "회원정보 수정에 실패했습니다. 없는 회원입니다."),

    // 모든 요청 실패 9000
    FAIL(false, 9000, "요청에 실패했습니다. 관리자에게 문의해주세요."),
    FAIL_UNAUTHORIZED(false, 9001, "요청이 실패했습니다. 권한이 없습니다.");

    private boolean isSuccess;
    private Integer code;
    private String message;

    BaseResponseStatus(Boolean isSuccess, Integer code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
