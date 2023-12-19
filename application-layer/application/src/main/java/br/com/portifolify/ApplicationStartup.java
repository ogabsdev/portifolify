package br.com.portifolify;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ApplicationStartup {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ApplicationStartup.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run();
    }

}
