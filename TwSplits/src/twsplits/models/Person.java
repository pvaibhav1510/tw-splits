/*
 * Copyright (c) 2016 ChargeBee Inc
 * All Rights Reserved.
 */
package twsplits.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vaibhav
 */
public class Person {

    private String name;
    private List<Expense> expenses = new ArrayList<>();
//    private Double total =0d;
    private Double ownExpense = 0d;

    public Person(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    public Person addExpense(Expense e) {
        expenses.add(e);
//        split(e);
        return this;
    }

    public void addToOwnExpense(Double d) {
        ownExpense += d;
    }

    public Double totalExpense() {
        Double total = 0d;
        for (Expense e : expenses) {
            total += e.amount();
        }
        return total;
    }

    public Double ownExpense() {
        return ownExpense;
    }
    
//    private void split(Expense e){
//        
//        
//    }
    

}
