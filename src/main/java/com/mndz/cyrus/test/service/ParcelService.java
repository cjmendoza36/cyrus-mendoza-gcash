package com.mndz.cyrus.test.service;

import com.mndz.cyrus.test.domain.Parcel;
import com.mndz.cyrus.test.domain.Rule;
import org.springframework.stereotype.Service;

@Service
public class ParcelService {
    private RuleEngine ruleEngine;

    public ParcelService() {
        ruleEngine = new RuleEngine();

        ruleEngine.addRule(new Rule(1, "Reject") {
            @Override
            public boolean evaluate() {
                return this.parcel.getWeight() > 50;
            }

            @Override
            public double computeCost() {
                return 0;
            }
        });

        ruleEngine.addRule(new Rule(2, "Heavy Parcel") {
            @Override
            public boolean evaluate() {
                return this.parcel.getWeight() > 10 && this.parcel.getWeight() <= 50;
            }

            @Override
            public double computeCost() {
                return this.parcel.getWeight() * 20;
            }
        });

        ruleEngine.addRule(new Rule(3, "Small Parcel") {
            @Override
            public boolean evaluate() {
                return this.parcel.getVolume() < 1500;
            }

            @Override
            public double computeCost() {
                return this.parcel.getVolume() * 0.03;
            }
        });

        ruleEngine.addRule(new Rule(4, "Medium Parcel") {
            @Override
            public boolean evaluate() {
                return this.parcel.getVolume() < 2500;
            }

            @Override
            public double computeCost() {
                return this.parcel.getVolume() * 0.04;
            }
        });

        ruleEngine.addRule(new Rule(5, "Large Parcel") {
            @Override
            public boolean evaluate() {
                return true; // default as there's no condition
            }

            @Override
            public double computeCost() {
                return this.parcel.getVolume() * 0.05;
            }
        });
    }

    public double computeCost(Parcel parcel) {
        return ruleEngine.processRules(parcel);
    }

}
