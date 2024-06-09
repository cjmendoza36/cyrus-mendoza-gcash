package com.mndz.cyrus.test.domain;

import lombok.Getter;
import lombok.Setter;

public abstract class Rule {

    @Getter
    private int priority;

    @Getter
    private String name;

    @Setter
    protected Parcel parcel;

    public Rule(int priority, String name) {
        this.priority = priority;
        this.name = name;
    }

    public abstract boolean evaluate();

    public abstract double computeCost();
}

