package com.example.quequeflow.global.aop;

import com.example.quequeflow.global.util.CustomSpringELParser;
import java.lang.reflect.Method;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class DistributedLockAop {
    private final RedissonClient redissonClient;
    private final AopForTransaction aopForTransaction;

    public DistributedLockAop(@Qualifier("writeRedissonClient") RedissonClient redissonClient,
                              AopForTransaction aopForTransaction) {
        this.redissonClient = redissonClient;
        this.aopForTransaction = aopForTransaction;
    }

    @Around("@annotation(com.example.quequeflow.global.aop.DistributedLock)")
    public Object lock(final ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        DistributedLock distributedLock = method.getAnnotation(DistributedLock.class);

        String key = method.getName() + CustomSpringELParser.getDynamicValue(signature.getParameterNames(), joinPoint.getArgs(), distributedLock.key());
        RLock rLock = redissonClient.getLock(key);

        try {
            boolean available = rLock.tryLock(distributedLock.waitTime(), distributedLock.leaseTime(), distributedLock.timeUnit());  // (2)
            if (!available) {
                log.info("Lock 획득 실패={}", key);
                return false;
            }

            log.info("로직 수행");
            return aopForTransaction.proceed(joinPoint);
        } catch (InterruptedException e) {
            log.info("에러 발생");
            throw new InterruptedException();
        } finally {
            try {
                log.info("락 해제");
                rLock.unlock();   // (4)
            } catch (IllegalMonitorStateException e) {
                log.info("락 이미 해제");
            }
        }
    }
}
