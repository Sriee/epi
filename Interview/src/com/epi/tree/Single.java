package com.epi.tree;

import java.util.Collection;
import java.util.Iterator;

public class Single {

    private static Single singleObj = null;
    private Single(){}

    public static Single instance(){
        if(singleObj == null){
            singleObj = new Single();
        }
        return singleObj;
    }

    public void writeEntry(String s){
        System.out.println(s);
    }

    public void writeEntry(Collection<String> s){
        Iterator<String> it = s.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }
}
