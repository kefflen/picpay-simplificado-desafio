package com.github.kefflen.picpaysimplificado.notification;

import com.github.kefflen.picpaysimplificado.transaction.Transaction;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationProducer {
    private final KafkaTemplate<String, Transaction> kafkaTemplate;

    public NotificationProducer(KafkaTemplate<String, Transaction> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(Transaction transaction) {
        var notification = new Notification(true);
        kafkaTemplate.send("created:transaction", transaction);
    }
}
