package com.epi.tree;

import java.util.ArrayList;
import java.util.List;

public class TestSingle {

    public static void main(String[] args) {
        Single s = Single.instance();
        s.writeEntry("Hi from singleton logger");

        List<String> array = new ArrayList<>();
        array.add("Log file check should be done in constructor.");
        array.add("get time should be made private.");

        s.writeEntry(array);
    }
}
