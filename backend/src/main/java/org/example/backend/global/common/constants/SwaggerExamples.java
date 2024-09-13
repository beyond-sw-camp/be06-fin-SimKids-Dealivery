package org.example.backend.global.common.constants;

public class SwaggerExamples {
    //업체회원가입 요청 틀
    public static final String COMPANY_SIGNUP_REQUEST = """
       {
         "name": "강한승",
         "email": "이메일입력",
         "emailCode": "인증코드입력",
         "password": "Qwer1234!",
         "phoneNumber": "010-3757-2297",
         "postNumber": "01111",
         "address": "보라매로 87",
         "addressDetail": "1층 1호",
         "companyName": "비욘드서점",
         "regNumber": "1208800767",
         "openedAt": "20130716",
         "mosNumber": "1111-서울동작-1111"
       }
    """;
    //일반회원가입 요청 틀
    public static final String USER_SIGNUP_REQUEST = """
       {
         "name": "이름입력",
         "email": "이메일입력",
         "emailCode": "인증코드입력",
         "password": "Qwer1234!",
         "phoneNumber": "010-3757-2297",
         "postNumber": "01111",
         "address": "보라매로 87",
         "addressDetail": "1층 1호"
       }
    """;
    //이메일 인증 요청 틀
    public static final String EMAIL_AUTH_REQUEST = """
       {
           "email": "이메일입력"
       }""";


    // 주문 등록 요청 틀
    public static final String ORDERS_REGISTER_REQUEST = """
       {
           "boardIdx" : "게시글 idx 입력",
           "orderedProducts" : [
                {
                    "idx" : "게시글에서 주문한 상품 idx",
                    "quantity" : "주문한 수량"
                },
                {
                    "idx" : "게시글에서 주문한 상품 idx",
                    "quantity" : "주문한 수량"
                }
           ]
       }
    """;

}
