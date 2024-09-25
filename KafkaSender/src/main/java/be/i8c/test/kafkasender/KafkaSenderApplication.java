package be.i8c.test.kafkasender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class KafkaSenderApplication {

    public static void main(String[] args) {
        ApplicationContext apc = SpringApplication.run(KafkaSenderApplication.class, args);
        MessageConsumer consumer = apc.getBean(MessageConsumer.class);
        MessageProducer producer = apc.getBean(MessageProducer.class);

        producer.sendMessage("my-topic","hallo");


    }

}
