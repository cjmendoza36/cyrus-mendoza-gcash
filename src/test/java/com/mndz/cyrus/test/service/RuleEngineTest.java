package com.mndz.cyrus.test.service;

import com.mndz.cyrus.test.domain.Parcel;
import com.mndz.cyrus.test.domain.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RuleEngineTest {

    private RuleEngine ruleEngine;

    @BeforeEach
    void setUp() {
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

    @Test
    void testRejectRule() {
        Parcel parcel = new Parcel(10, 10, 10, 55, null);
        double cost = ruleEngine.processRules(parcel);
        assertEquals(0.0, cost);
    }

    @Test
    void testHeavyParcelRule() {
        Parcel parcel = new Parcel(10, 10, 10, 20, null);
        double cost = ruleEngine.processRules(parcel);
        assertEquals(400.0, cost);
    }

    @Test
    void testSmallParcelRule() {
        Parcel parcel = new Parcel(5, 10, 10, 5, null);
        double cost = ruleEngine.processRules(parcel);
        assertEquals(15.0, cost);
    }

    @Test
    void testMediumParcelRule() {
        Parcel parcel = new Parcel(10, 10, 20, 5, null);
        double cost = ruleEngine.processRules(parcel);
        assertEquals(80.0, cost);
    }

    @Test
    void testLargeParcelRule() {
        Parcel parcel = new Parcel(20, 20, 10, 5, null);
        double cost = ruleEngine.processRules(parcel);
        assertEquals(200.0, cost);
    }
}

