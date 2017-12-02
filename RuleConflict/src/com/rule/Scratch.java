package com.rule;

import java.io.File;
import java.nio.file.Paths;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.entity.*;
import com.json.LogicalOperator;

public class Scratch {

	public static void main(String[] args) {
		String hibernateConfigFilePath = Paths.get(Paths.get(".").toAbsolutePath().toString(),
				"resources/hibernate.cfg.xml").normalize().toString();
		File configFile = new File(hibernateConfigFilePath);
		SessionFactory factory = new Configuration()
				.configure(configFile)
				.addAnnotatedClass(Action.class)
				.addAnnotatedClass(Actuator.class)
				.addAnnotatedClass(Environment.class)
				.addAnnotatedClass(Link.class)
				.addAnnotatedClass(Rule.class)
				.addAnnotatedClass(Sensor.class)
				.addAnnotatedClass(Trigger.class)
				.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		LogicalOperator op = LogicalOperator.GREATER_THAN;
		Long id = null;
		try{
			session.beginTransaction();
			// Triggers
			Sensor aSensor = (Sensor) session.createQuery("from Sensor s where s.id = 4").getSingleResult();
			System.out.println(aSensor.toString());
			Trigger trigger = new com.entity.Trigger(id, "H", aSensor, op, 40);
		
			
			session.save(trigger);
			
			// Environment Table
			Environment env = new Environment(null, "E1", "<30", ">=30");
			session.save(env);
			
			session.getTransaction().commit();
		} finally {
			if(factory != null && factory.isOpen()){
				factory.close();
			}
		} 
	}
}
