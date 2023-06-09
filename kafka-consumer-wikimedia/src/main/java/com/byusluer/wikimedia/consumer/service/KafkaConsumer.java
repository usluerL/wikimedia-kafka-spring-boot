package com.byusluer.wikimedia.consumer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class KafkaConsumer {

    @KafkaListener(topics = "wikimedia_recentchange", groupId = "wikimediaGroup")
    public void consume(String eventMessage){
        log.info("messege received {}",eventMessage);
    }
}
