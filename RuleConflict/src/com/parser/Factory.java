package com.parser;

import com.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;
import java.nio.file.Paths;

public class Factory {

    private final String hibernateConfigFileName = "hibernate.cfg.xml";
    private final String hibernateConfigFilePath = Paths.get(Paths.get(".").toAbsolutePath().toString(),
                "resources/" + hibernateConfigFileName).normalize().toString();
    private static Factory factoryObj = null;
    private SessionFactory factory;

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

    public static Factory instance(){
        if(factoryObj == null){
            factoryObj = new Factory();
        }
        return factoryObj;
    }

    public SessionFactory getFactory(){ return factory; }

    public Session getCurrentSession(){ return factory.getCurrentSession(); }

    public void close(){
        if(factory != null && factory.isOpen()){
            factory.close();
        }
    }
}
