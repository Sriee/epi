package com.parser;

import com.entity.Action;
import com.entity.Environment;
import com.entity.Trigger;

import java.util.List;

public class Container {

    private String name;
    private List<Trigger> triggerList;
    private List<Environment> environmentList;
    private List<Action> actionList;

    public Container(){}

    public Container(String name) {
        this.name = name;
    }

    public Container(String name, List<Trigger> triggerList, List<Environment> environmentList, List<Action> actionList) {
        this.name = name;
        this.triggerList = triggerList;
        this.environmentList = environmentList;
        this.actionList = actionList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Trigger> getTriggerList() {
        return triggerList;
    }

    public void setTriggerList(List<Trigger> triggerList) {
        this.triggerList = triggerList;
    }

    public List<Environment> getEnvironmentList() {
        return environmentList;
    }

    public void setEnvironmentList(List<Environment> environmentList) {
        this.environmentList = environmentList;
    }

    public List<Action> getActionList() {
        return actionList;
    }

    public void setActionList(List<Action> actionList) {
        this.actionList = actionList;
    }

    @Override
    public String toString() {
        return "Container{" +
                "name='" + name + '\'' +
                ", triggerList=" + triggerList +
                ", environmentList=" + environmentList +
                ", actionList=" + actionList +
                '}';
    }
}
