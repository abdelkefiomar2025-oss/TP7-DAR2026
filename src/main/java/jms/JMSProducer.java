package jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

public class JMSProducer {
    public static void main(String[] args) throws Exception {
        // 1. Créer la ConnectionFactory : protocole TCP, adresse IP du broker, port
        ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");

        // 2. Créer la connexion puis la démarrer
        Connection connection = factory.createConnection();
        connection.start();

        // 3. Créer la session : transactionnelle, accusé AUTO_ACKNOWLEDGE
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);

        // 4. Créer la destination : un Topic
        Destination destination = session.createTopic("TP7.TOPIC");

        // 5. Créer le producteur, avec des messages non persistants
        MessageProducer producer = session.createProducer(destination);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        // 6. Créer et envoyer le message
        TextMessage message = session.createTextMessage("Hello World! From: Omar");
        producer.send(message);

        // 7. Valider la transaction (obligatoire avec une session transactionnelle)
        session.commit();
        System.out.println("Message envoyé : " + message.getText());

        // 8. Fermer la session puis la connexion
        session.close();
        connection.close();
    }
}