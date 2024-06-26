package com.example.demo.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class TopicConsumer {

	@RabbitListener(queues = "topic-queue1")
	public void receiveTopicQueue1(String message) {
		System.out.println("Receive from topic-queue1: " + message);
	}
	
	@RabbitListener(queues = "topic-queue2")
	public void receiveTopicQueue2(String message) {
		System.out.println("Receive from topic-queue2: " + message);
	}
}
