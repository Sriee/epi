package com.rule;

import java.nio.file.Paths;
import java.io.File;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.entity.Action;
import com.json.LogicalOperator;

public class Scratch {

	public static void main(String[] args) {
		String hibernateConfigFilePath = Paths.get(Paths.get(".").toAbsolutePath().toString(),
				"resources/hibernate.cfg.xml").normalize().toString();
		File configFile = new File(hibernateConfigFilePath);
		SessionFactory factory = new Configuration()
				.configure(configFile)
				.addAnnotatedClass(Action.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();
		LogicalOperator op = LogicalOperator.GREATER_THAN;
		try{
			Action action = new Action("AC", 1, op, 30);
			session.beginTransaction();
			session.save(action);
			session.getTransaction().commit();
		} finally {
			if(factory != null && factory.isOpen()){
				factory.close();
			}
		} 
	}
}
