package com.byusluer.wikimedia.producer.service;

import com.launchdarkly.eventsource.EventSource;
import com.launchdarkly.eventsource.background.BackgroundEventHandler;
import com.launchdarkly.eventsource.background.BackgroundEventSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class WikimediaChangesProducer {

    private final KafkaTemplate<String,String> kafkaTemplate;
    private final BackgroundEventHandler eventHandler;
    private final String topic = "wikimedia_recentchange";
    String url = "https://stream.wikimedia.org/v2/stream/recentchange";


    public void sendMessage(){
        EventSource.Builder eventSourceBuilder = new EventSource.Builder(URI.create(url));
        BackgroundEventSource.Builder builder = new BackgroundEventSource.Builder(eventHandler, eventSourceBuilder);

        BackgroundEventSource eventSource = builder.build();

        eventSource.start();
        try {
            TimeUnit.MINUTES.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
