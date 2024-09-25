package be.i8c.test.kafkasender;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
@RequiredArgsConstructor
@Slf4j
public class MessageProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value(value = "${kafka.topic.name}")
    private String topic;

    public void sendMessage(String topic, String message) {
        ListenableFuture<SendResult<String, String>> future = (ListenableFuture<SendResult<String, String>>) kafkaTemplate.send(this.topic, message);
        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.debug("Message {} has been sent ", message);
            }
            @Override
            public void onFailure(Throwable ex) {
                log.error("Something went wrong with the message {} ", message);
            }
        });
        kafkaTemplate.send(topic, message);
    }

}