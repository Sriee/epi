package com.rule;

import java.io.File;
import java.nio.file.Paths;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.entity.*;

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
		try{
			Rule rule = new Rule(null, "R2");
		
			session.beginTransaction();
			session.save(rule);
			session.getTransaction().commit();
		} finally {
			if(factory != null && factory.isOpen()){
				factory.close();
			}
		} 
	}
}
