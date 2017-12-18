package com.parser;

import com.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;
import java.nio.file.Paths;

/**
 * Singleton Design Pattern implementation for Hibernate Factory. 
 * 
 * Sessions for hibernate required the SessionFactory object often. To avoid building 
 * of session factory for each class and injecting dependcy to classes this class is 
 * used
 *  
 * @author sriee
 */
public class Factory {

	// Hibernate Configuration file & path 
    private final String hibernateConfigFileName = "hibernate.cfg.xml";
    private final String hibernateConfigFilePath = Paths.get(Paths.get(".").toAbsolutePath().toString(),
                "resources/" + hibernateConfigFileName).normalize().toString();
    private static Factory factoryObj = null;
    private SessionFactory factory;

    /**
     * Initialize Hibernate Session Factory
     */
    private Factory(){
        super();
        File configFile = new File(hibernateConfigFilePath);
        factory = new Configuration()
                .configure(configFile)
                .addAnnotatedClass(Action.class)
                .addAnnotatedClass(Actuator.class)
                .addAnnotatedClass(Environment.class)
                .addAnnotatedClass(Link.class)
                .addAnnotatedClass(Rule.class)
                .addAnnotatedClass(Sensor.class)
                .addAnnotatedClass(Trigger.class)
                .buildSessionFactory();
    }

    /**
     * Retrieve the Hibernate Session factory instance
     * 
     * @return factory instance
     */
    public static Factory instance(){
        if(factoryObj == null){
            factoryObj = new Factory();
        }
        return factoryObj;
    }

    /**
     * Return the session factory object
     * 
     * @return session factory object
     */
    public SessionFactory getFactory(){ return factory; }

    /**
     * Return current session of factory instance
     * 
     * @return current session
     */
    public Session getCurrentSession(){ return factory.getCurrentSession(); }

    /**
     * Close the Hibernate connection
     */
    public void close(){
        if(factory != null && factory.isOpen()){
            factory.close();
        }
    }
}
