package br.com.portifolify.application.config.cryptography;

import lombok.extern.slf4j.Slf4j;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Slf4j
@Configuration
public class HashIdConfig {

    @Bean
    Hashids hashids(@Value("${cryptography.salt}") String cryptoSalt) {
        return new Hashids(cryptoSalt, 32);
    }

    @PostConstruct
    void logOnReady() {
        log.info("HashIds initialized...");
    }

}
