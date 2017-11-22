# se2-class-example

In order to configure Glassfish with the necessary JMS resources that are used by the example application you can used the following two Glassfish commands:



    asadmin create-jms-resource --restype javax.jms.ConnectionFactory --description "connection factory for durable subscriptions" --property ClientId=TestID jms/QueueConnectionFactory
     
    asadmin create-jms-resource --restype javax.jms.Queue --property Name=TestQueue jms/testQueue
