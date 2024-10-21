package org.example.auth.global.security.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Value("${redis.master.host}")
    private String masterHost;

    @Value("${redis.slave.host}")
    private String slaveHost;

    @Value("${redis.port}")
    private int redisPort;

    @Value("${redis.password}")
    private String redisPassword;


    // 마스터용 LettuceConnectionFactory 설정
    @Bean
    public RedisConnectionFactory masterConnectionFactory() {
        RedisStandaloneConfiguration masterConfig = new RedisStandaloneConfiguration();
        masterConfig.setHostName(masterHost);      // 마스터 Redis 호스트
        masterConfig.setPort(redisPort);           // Redis 포트
        masterConfig.setPassword(redisPassword);   // Redis 비밀번호 (필요 시)

        return new LettuceConnectionFactory(masterConfig);
    }

    // 슬레이브용 LettuceConnectionFactory 설정
    @Bean
    public RedisConnectionFactory slaveConnectionFactory() {
        RedisStandaloneConfiguration slaveConfig = new RedisStandaloneConfiguration();
        slaveConfig.setHostName(slaveHost);        // 슬레이브 Redis 호스트
        slaveConfig.setPort(redisPort);            // Redis 포트
        slaveConfig.setPassword(redisPassword);    // Redis 비밀번호 (필요 시)

        return new LettuceConnectionFactory(slaveConfig);
    }

    // 기본 RedisTemplate 설정 (마스터 사용)
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(masterConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;
    }

    // 마스터 RedisTemplate 설정
    @Bean(name = "masterRedisTemplate")
    public RedisTemplate<String, Object> masterRedisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(masterConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;
    }

    // 슬레이브 RedisTemplate 설정
    @Bean(name = "slaveRedisTemplate")
    public RedisTemplate<String, Object> slaveRedisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(slaveConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;
    }
}
