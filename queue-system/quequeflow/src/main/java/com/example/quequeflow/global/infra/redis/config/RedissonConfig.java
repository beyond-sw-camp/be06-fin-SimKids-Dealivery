package com.example.quequeflow.global.infra.redis.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

    @Value("${redis.master.host}")
    private String masterHost;

    @Value("${redis.slave.host}")
    private String slaveHost;

    @Value("${redis.port}")
    private int redisPort;

    @Value("${redis.password}")
    private String redisPassword;

    @Bean(name = "writeRedissonClient")
    public RedissonClient writeRedissonClient() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://" + masterHost + ":" + redisPort)
                .setPassword(redisPassword); // 마스터에 연결

        config.setCodec(new StringCodec());

        return Redisson.create(config);
    }

    @Bean(name = "readRedissonClient")
    public RedissonClient readRedissonClient() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://" + slaveHost + ":" + redisPort)
                .setPassword(redisPassword);  // 슬레이브에 연결

        config.setCodec(new StringCodec());

        return Redisson.create(config);
    }
}
