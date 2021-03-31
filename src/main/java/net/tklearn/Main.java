package net.tklearn;

public class Main {

    public static void main(String[] args) {
        try {
            TopicExchange exchange = new TopicExchange();
            exchange.createExchangeAndQueue();

            Producer producer = new Producer();
            producer.publish();

            Receiver receiver = new Receiver();
            receiver.receive();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
