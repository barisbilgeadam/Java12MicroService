package com.barisd.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;


@Configuration
@EnableCaching // Cache mekanizmalarını aktifleştirmek için
@EnableRedisRepositories // Redisi vt gibi kullanmak için.
public class RedisConfig {

    @Value("${redisconfig.host}")
    private String redisHost;
    @Value("${redisconfig.port}")
    private int redisPort;

    @Bean
    public LettuceConnectionFactory rediseBagla() {

        return new LettuceConnectionFactory(new RedisStandaloneConfiguration(redisHost, redisPort));
    }
}
