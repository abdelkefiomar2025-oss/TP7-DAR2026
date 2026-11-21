package jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

public class JMSConsumer {
    public static void main(String[] args) throws Exception {
        // 1. Créer la ConnectionFactory avec l'URL du broker
        ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");

        // 2. Créer la connexion
        Connection connection = factory.createConnection();

        // 3. Créer la session (non transactionnelle, accusé automatique)
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // 4. Créer la destination : un Topic
        Destination destination = session.createTopic("TP7.TOPIC");

        // 5. Créer le consommateur sur cette destination
        MessageConsumer consumer = session.createConsumer(destination);

        // 6. Mettre en place le listener pour la réception asynchrone
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                try {
                    if (message instanceof TextMessage) {
                        TextMessage textMessage = (TextMessage) message;
                        System.out.println("Message reçu : " + textMessage.getText());
                    }
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

        // 7. Démarrer la connexion : le consommateur peut recevoir des messages
        connection.start();
        System.out.println("Le consommateur attend des messages...");
    }
}