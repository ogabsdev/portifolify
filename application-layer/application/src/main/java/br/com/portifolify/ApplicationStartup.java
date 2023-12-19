package br.com.portifolify;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationStartup {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ApplicationStartup.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run();
    }

}
