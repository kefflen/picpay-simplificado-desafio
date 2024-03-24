package com.github.kefflen.picpaysimplificado.notification;

import com.github.kefflen.picpaysimplificado.authorization.AuthorizationService;
import com.github.kefflen.picpaysimplificado.transaction.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class NotificationConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationService.class);
    private final RestClient restClient;

    public NotificationConsumer(RestClient.Builder restClient) {
        this.restClient = restClient.baseUrl("https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6").build();
    }

    @KafkaListener(topics = "created:transaction", groupId = "notification")
    public void receiveNotification(Transaction transaction) {
        LOGGER.info("Notifying transaction: {}", transaction);
        var response = restClient.get().retrieve()
                .toEntity(Notification.class);
        if (response.getStatusCode().isError() || !response.getBody().message()) {
            LOGGER.error("Failed to notify transaction: {}", transaction);
            throw new NotificationException("Failed to notify transaction");
        }
        LOGGER.info("Transaction notified: {}", transaction);
    }
}
