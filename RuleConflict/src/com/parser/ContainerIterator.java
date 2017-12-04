package com.parser;

import java.util.Iterator;
import org.hibernate.Session;

import java.util.NoSuchElementException;

public class ContainerIterator implements Iterable<Container>, Iterator<Container>{

    private long position;
    private long total;
    private Factory factory;

    public ContainerIterator() {
        this.position = 1;

        // TODO: total should be count of the current Rule table
        // rows
        this.factory = Factory.instance();
        Session session = this.factory.getCurrentSession();
        session.beginTransaction();

        this.total = (long) session.createQuery("select count(*) from Rule").uniqueResult();
        session.getTransaction().commit();
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
            this.factory.close();
            throw new NoSuchElementException();
        }
        Container temp = new Container();
        temp.setName("R" + this.position);
        this.position++;
        return temp;
    }

    @Override
    public void remove(){ throw new UnsupportedOperationException(); }
}
