package com.parser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.hibernate.Session;

import com.entity.Action;
import com.entity.Environment;
import com.entity.Trigger;
import com.entity.Type;

public class ContainerIterator implements Iterable<Container>, Iterator<Container>{

    private long position;
    private long total;
    private Factory factory;

    public ContainerIterator() {
        this.position = 1;
        this.factory = Factory.instance();
        Session session = this.factory.getFactory().openSession();
        session.beginTransaction();

        this.total = (long) session.createQuery("select count(*) from Rule").getSingleResult();
        session.close();
    }

    @Override
    public Iterator<Container> iterator(){ return this; }

    @Override
    public boolean hasNext(){
        return this.position <= this.total;
    }

    @Override
    public Container next(){
        if(!this.hasNext()) {
            throw new NoSuchElementException();
        }

        Container temp = new Container(null,
                "R" + this.position,
                this.list(Trigger.class, Type.TRIGGER),
                this.list(Environment.class, Type.ENVIRONMENT),
                this.list(Action.class, Type.ACTION)
        );
        this.position++;
        return temp;
    }

    @Override
    public void remove(){ throw new UnsupportedOperationException(); }

    private <T> List<T> list(Class<T> klazz, Type type){
    	List<T> list = null;
        String name = "R" + this.position;
        String query = "";
        Long ruleId = null;
        List<Integer> intList = null;
        List<Long> longList = null;
        Session session = this.factory.getFactory().openSession();
        session.beginTransaction();

        ruleId = (Long) session.createQuery("SELECT id FROM Rule where name = :name")
        		.setParameter("name", name)
        		.getSingleResult();
        
        intList = session.createQuery("SELECT typeId FROM Link WHERE linkPK.ruleId = :id AND type = :type", Integer.class)
        		.setParameter("id", ruleId)
        		.setParameter("type", type)
        		.getResultList();
        
        longList = new ArrayList<>();
        
        if(klazz == Trigger.class){
        	query = "FROM Trigger WHERE id IN (:idList)"; 
        } else if(klazz == Environment.class){
        	query = "FROM Environment WHERE id IN (:idList)";
        } else if(klazz == Action.class){
        	query = "FROM Action WHERE id IN (:idList)";
        }
        
        for(Integer i : intList) longList.add((long) i);
        
        list = (List<T>) session.createQuery(query, klazz)
        		.setParameter("idList", longList)
        		.getResultList();
        
        session.close();
        return list;
    }
}
