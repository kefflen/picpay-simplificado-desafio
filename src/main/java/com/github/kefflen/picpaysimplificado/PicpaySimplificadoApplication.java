package com.github.kefflen.picpaysimplificado;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;
import org.springframework.kafka.config.TopicBuilder;

@EnableJdbcAuditing
@SpringBootApplication
public class PicpaySimplificadoApplication {
    public static void main(String[] args) {
        SpringApplication.run(PicpaySimplificadoApplication.class, args);
    }

    @Bean
    NewTopic notificationTopic() {
        return TopicBuilder.name("created:transaction")
                .partitions(1)
                .replicas(1)
                .build();
    }
}
