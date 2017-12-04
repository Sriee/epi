package com.rule;

import com.parser.Factory;
import org.hibernate.Session;

import com.entity.*;
import com.json.LogicalOperator;

public class Scratch {

	public static void main(String[] args) {
		Factory factory = Factory.instance();
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
			session.close();
		} finally {
				factory.close();
		}
	}
}
