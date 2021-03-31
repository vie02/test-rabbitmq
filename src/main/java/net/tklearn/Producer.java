package net.tklearn;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.nio.charset.StandardCharsets;

public class Producer {

    private static final String MESSAGE_1 = "First topic message";
    private static final String MESSAGE_2 = "Second topic message";
    private static final String MESSAGE_3 = "Third topic message";

    public void publish() {
        try {
            Connection conn = RabbitMQConnection.getConnection();
            if (conn != null) {
                Channel channel = conn.createChannel();
                // sent first message
                channel.basicPublish(TopicExchange.EXCHANGE_NAME, TopicExchange.ROUTING_KEY_1, null, MESSAGE_1.getBytes(StandardCharsets.UTF_8));
                System.out.println("Message sent '" + MESSAGE_1 + "'");
                // sent second message
                channel.basicPublish(TopicExchange.EXCHANGE_NAME, TopicExchange.ROUTING_KEY_2, null, MESSAGE_2.getBytes(StandardCharsets.UTF_8));
                System.out.println("Message sent '" + MESSAGE_2 + "'");
                // sent third message
                channel.basicPublish(TopicExchange.EXCHANGE_NAME, TopicExchange.ROUTING_KEY_3, null, MESSAGE_3.getBytes(StandardCharsets.UTF_8));
                System.out.println("Message sent '" + MESSAGE_3 + "'");

                channel.close();
                conn.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
