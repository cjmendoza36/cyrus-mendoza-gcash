package com.mndz.cyrus.test.service;

import com.mndz.cyrus.test.domain.Parcel;
import com.mndz.cyrus.test.domain.Rule;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RuleEngine {
    private List<Rule> rules;

    public RuleEngine() {
        this.rules = new ArrayList<>();
    }

    public void addRule(Rule rule) {
        this.rules.add(rule);
    }

    public double processRules(Parcel parcel) {
        rules.sort(Comparator.comparingInt(Rule::getPriority));

        for (Rule rule : rules) {
            rule.setParcel(parcel);

            if (rule.evaluate()) {
                return rule.computeCost();
            }
        }

        return 0.0;
    }
}
