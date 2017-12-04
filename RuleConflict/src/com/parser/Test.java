package com.parser;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class Test {

    public static void main(String[] args) {
        List<Container> containerList = new ArrayList<>();
        containerList.add(new Container());
        containerList.add(new Container());

        ContainerIterator ci = new ContainerIterator();
        Iterator<Container> iter = ci.iterator();
        while(iter.hasNext()){
            System.out.println(iter.next().getName());
        }
    }
}
