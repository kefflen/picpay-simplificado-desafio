package com.github.kefflen.picpaysimplificado.notification;

import com.github.kefflen.picpaysimplificado.transaction.Transaction;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private final NotificationProducer notificationProducer;

    public NotificationService(NotificationProducer notificationProducer) {
        this.notificationProducer = notificationProducer;
    }

    public void notify(Transaction transaction) {
        this.notificationProducer.send(transaction);
    }
}
