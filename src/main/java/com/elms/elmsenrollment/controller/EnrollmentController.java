package com.elms.elmsenrollment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/enrollment/v1")
public class EnrollmentController {

    Logger logger = LoggerFactory.getLogger(EnrollmentController.class);

    @Value("${cloud.aws.end-point.uri}")
    private String endPoint;

    @Autowired
    private QueueMessagingTemplate queueMessagingTemplate;

    @GetMapping("/send/{message}")
    public void sendMessageToQueue(@PathVariable String message) {

        queueMessagingTemplate.send(endPoint, MessageBuilder.withPayload(message).build());
    }


    // Listner
    @SqsListener("enrollment-queue")
    public void loadMessageFromSQS(String message){
        logger.info("message from SQS Queue: {} ", message);

    }


}
