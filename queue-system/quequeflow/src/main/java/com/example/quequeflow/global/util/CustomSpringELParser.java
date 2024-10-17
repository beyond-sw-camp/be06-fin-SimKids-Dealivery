package com.example.quequeflow.global.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

@Slf4j
public class CustomSpringELParser {
    private CustomSpringELParser() {
    }

    public static String getDynamicValue(String[] paramNames, Object[] args, String key) {
        // SpEL 파서 생성
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();

        // 파라미터 이름과 값을 context에 설정
        for (int i = 0; i < paramNames.length; i++) {
            context.setVariable(paramNames[i], args[i]);  // SpEL에서 #{paramName}으로 참조할 수 있게 설정
        }

        // SpEL 표현식 파싱 및 값 반환
        return parser.parseExpression(key).getValue(context, String.class);

    }
}
