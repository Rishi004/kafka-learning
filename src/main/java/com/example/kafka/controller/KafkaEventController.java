package com.example.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.kafka.service.KafkaMessagePublisher;

@RestController
public class KafkaEventController {

	private final KafkaMessagePublisher kafkaMessagePublisher;

	@Autowired
	public KafkaEventController(KafkaMessagePublisher kafkaMessagePublisher) {
		this.kafkaMessagePublisher = kafkaMessagePublisher;
	}

	@GetMapping("/publish/{message}")
	public ResponseEntity<?> publishMessage(@PathVariable String message) {
		try {
			for (int i = 0; i <= 20000; i++) {
				kafkaMessagePublisher.sendMessageToTopic(message + " : " + i);
			}
			return ResponseEntity.ok("message published successfully...");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}
}
