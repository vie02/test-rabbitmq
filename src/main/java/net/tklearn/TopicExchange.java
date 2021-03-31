package net.tklearn;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class TopicExchange {

    public static String EXCHANGE_NAME = "topic-exchange";

    public static String QUEUE_NAME_1 = "topic-queue-1";
    public static String QUEUE_NAME_2 = "topic-queue-2";
    public static String QUEUE_NAME_3 = "topic-queue-3";

    public static String ROUTING_PATTERN_1 = "vietnam.hcm.*";
    public static String ROUTING_PATTERN_2 = "vietnam.hcm.#";
    public static String ROUTING_PATTERN_3 = "vietnam.*.*";

    public static String ROUTING_KEY_1 = "vietnam.hcm.q12";
    public static String ROUTING_KEY_2 = "vietnam.hcm";
    public static String ROUTING_KEY_3 = "vietnam.hn.cg";

    public void createExchangeAndQueue() {
        try {
            Connection conn = RabbitMQConnection.getConnection();
            if (conn != null) {
                Channel channel = conn.createChannel();
                channel.exchangeDeclare(EXCHANGE_NAME, ExchangeType.TOPIC.getExchangeName(), true);
                // Queue 1
                channel.queueDeclare(QUEUE_NAME_1, true, false, false, null);
                channel.queueBind(QUEUE_NAME_1, EXCHANGE_NAME, ROUTING_PATTERN_1);
                // Queue 2
                channel.queueDeclare(QUEUE_NAME_2, true, false, false, null);
                channel.queueBind(QUEUE_NAME_2, EXCHANGE_NAME, ROUTING_PATTERN_2);
                // Queue 3
                channel.queueDeclare(QUEUE_NAME_3, true, false, false, null);
                channel.queueBind(QUEUE_NAME_3, EXCHANGE_NAME, ROUTING_PATTERN_3);

                channel.close();
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
