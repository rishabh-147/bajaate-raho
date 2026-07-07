package com.rishabh.musicstream;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class MusicStreamingApplication {
    public static void main(String[] args) {
        SpringApplication.run(MusicStreamingApplication.class);
        log.info("Music Streaming Application Started!!");
    }
}
