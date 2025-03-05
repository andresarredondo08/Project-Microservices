package com.andres.notificationService.service;

import com.andres.notificationService.model.Notification;
import com.andres.notificationService.repository.NotificationRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private JavaMailSender mailSender;

    @RabbitListener(queues = "notificationQueue")
    public void handleNotification(String recipient) {
        // Enviar notificación por email
        sendEmail(recipient, "Nueva Notificación", "¡Tienes una nueva notificación!");

        // Guardar en la base de datos
        Notification notification = Notification.builder()
                .recipient(recipient)
                .message("¡Tienes una nueva notificación!")
                .timestamp(LocalDateTime.now())
                .build();
        notificationRepository.save(notification);
    }

    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
}