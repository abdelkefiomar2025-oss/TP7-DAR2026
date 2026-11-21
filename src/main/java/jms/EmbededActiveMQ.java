package jms;

import org.apache.activemq.broker.BrokerService;

public class EmbededActiveMQ {
    public static void main(String[] args) throws Exception {
        // 1. Créer l'objet BrokerService qui représente le broker
        BrokerService broker = new BrokerService();

        // 2. Ajouter un connecteur TCP : écoute sur toutes les interfaces, port 61616
        broker.addConnector("tcp://0.0.0.0:61616");

        // 3. Broker sans persistance (pas de fichiers créés sur le disque)
        broker.setPersistent(false);

        // 4. Démarrer le broker
        broker.start();
        System.out.println("Le broker ActiveMQ est démarré sur le port 61616...");

        // 5. Garder le broker actif
        broker.waitUntilStopped();
    }
}