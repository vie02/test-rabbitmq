package net.tklearn;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Receiver {

    public void receive() {
        try {
            Connection conn = RabbitMQConnection.getConnection();
            if (conn != null) {
                Channel channel = conn.createChannel();
                // Read queue 1
                Consumer consumer1 = new DefaultConsumer(channel) {
                    @Override
                    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                        String message = new String(body, StandardCharsets.UTF_8);
                        System.out.println("Message received Queue 1 '" + message + "'");
                    }
                };
                channel.basicConsume(TopicExchange.QUEUE_NAME_1, true, consumer1);

                // Read queue 2
                Consumer consumer2 = new DefaultConsumer(channel) {
                    @Override
                    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                        String message = new String(body, StandardCharsets.UTF_8);
                        System.out.println("Message received Queue 2 '" + message + "'");
                    }
                };
                channel.basicConsume(TopicExchange.QUEUE_NAME_2, true, consumer2);

                // Read queue 3
                Consumer consumer3 = new DefaultConsumer(channel) {
                    @Override
                    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                        String message = new String(body, StandardCharsets.UTF_8);
                        System.out.println("Message received Queue 3'" + message + "'");
                    }
                };
                channel.basicConsume(TopicExchange.QUEUE_NAME_3, true, consumer3);

                channel.close();
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
