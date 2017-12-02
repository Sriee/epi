package com.rule;

import java.io.File;
import java.nio.file.Paths;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.entity.Action;
import com.entity.Link;
import com.entity.LinkPK;
import com.entity.Sensor;
import com.entity.Type;

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
			LinkPK pk = new LinkPK(null, (long)1);
			Link link = new Link(pk, Type.ACTION, 1);
			
			session.beginTransaction();
			session.save(link);
			session.getTransaction().commit();
		} finally {
			if(factory != null && factory.isOpen()){
				factory.close();
			}
		} 
	}
}
