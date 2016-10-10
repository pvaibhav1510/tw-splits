/*
 * Copyright (c) 2016 ChargeBee Inc
 * All Rights Reserved.
 */
package twsplits.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author vaibhav
 */
public class Expense {

    
    private Person payer;
    
    private Category category;
    private List<Person> sharingPeople = new ArrayList<>();
    private Double amount;

    public Expense(Category cat, Double amount) {
        this.category = cat;
        this.amount = amount;
    }

    public void forPeople(Person... people) {
        sharingPeople.addAll(Arrays.asList(people));
    }

    public void addSharingPerson(Person p) {
        sharingPeople.add(p);
    }

    public List<Person> forPeople() {
        return sharingPeople;
    }

    public Double amount() {
        return amount;
    }

}
