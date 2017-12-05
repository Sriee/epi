package com.parser;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.hibernate.Session;

import com.entity.Action;
import com.entity.Environment;
import com.entity.Trigger;

public class ContainerIterator implements Iterable<Container>, Iterator<Container>{

    private long position;
    private long total;
    private Factory factory;

    public ContainerIterator() {
        this.position = 1;
        this.factory = Factory.instance();
        Session session = this.factory.getCurrentSession();
        session.beginTransaction();

        this.total = (long) session.createQuery("select count(*) from Rule").getSingleResult();
        session.close();
    }

    @Override
    public Iterator<Container> iterator(){ return this; }

    @Override
    public boolean hasNext(){
        return this.position < this.total;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Container next(){
        if(!this.hasNext()) {
            this.factory.close();
            throw new NoSuchElementException();
        }
        
        Container temp = new Container(null,
                "R" + this.position,
                (List<Trigger>) this.list(Trigger.class),
                (List<Environment>)this.list(Environment.class),
                (List<Action>)this.list(Action.class)
        );
        this.position++;
        return temp;
    }

    @Override
    public void remove(){ throw new UnsupportedOperationException(); }

    public List<?> list(Object inst){
        String name = "R" + this.position;
        String type = "";

        if(inst instanceof Trigger){
            type = "TRIGGER";
        } else if (inst instanceof Environment){
            type = "ENVIRONMENT";
        } else if(inst instanceof Action){
            type = "ACTION";
        }

        Session session = this.factory.getCurrentSession();
        session.beginTransaction();

        List<?> list = session.createQuery("SELECT l.type_id from Rule r JOIN Link l ON r.Id = l.rule_id " +
                "WHERE r.name = :name AND t.type = :type")
                .setParameter("type", type)
                .setParameter("name", name)
                .getResultList();

        session.close();
        return list;
    }

}
