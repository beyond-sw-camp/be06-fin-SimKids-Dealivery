package org.example.auth.global.constants;

public class SwaggerDescription {
    //업체회원가입 설명 틀
    public static final String COMPANY_SIGNUP_REQUEST =
            "먼저 이메일 인증 요청 후 회원가입 요청을 진행해주세요\n" +
            "테스트 가능한 사업자 등록정보:\n" +
            "- 번호: 1208800767\n" +
            "- 회원이름: 강한승\n" +
            "- 개업일자: 20130716";
    //일반회원가입 설명 틀
    public static final String USER_SIGNUP_REQUEST = "먼저 이메일 인증 요청 후 회원가입 요청을 진행해주세요";
    //이메일 인증 설명 틀
    public static final String EMAIL_AUTH_REQUEST = "이메일을 입력하면 인증코드를 생성하여 이메일로 발송합니다. 만료기한은 10분입니다.";

}
