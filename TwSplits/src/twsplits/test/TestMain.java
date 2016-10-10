/*
 * Copyright (c) 2016 ChargeBee Inc
 * All Rights Reserved.
 */
package twsplits.test;

import java.util.ArrayList;
import java.util.List;
import twsplits.models.Category;
import twsplits.models.Expense;
import twsplits.models.Person;
import twsplits.splitter.Splitter;

/**
 *
 * @author vaibhav
 */
public class TestMain {

    private static List<Person> people = new ArrayList<>();
    private static List<Expense> expenses = new ArrayList<>();

    public static void main(String[] args) {
        Category snacks = new Category("Snacks");
        Category taxi = new Category("Taxi");
        Category bus = new Category("Bus");

        Person a = new Person("A");
        Person b = new Person("B");
        Person c = new Person("C");
        Person d = new Person("D");

        people.add(a);
        people.add(b);
        people.add(c);
        people.add(d);

        Expense snacksExp = new Expense(snacks, 100.00);
        snacksExp.forPeople(a, b, c, d);
        a.addExpense(snacksExp);
        expenses.add(snacksExp);

        Expense taxiExp = new Expense(taxi, 500.00);
        taxiExp.forPeople(c, d);
        b.addExpense(taxiExp);
        expenses.add(taxiExp);

        Expense busExp = new Expense(bus, 300.00);
        busExp.forPeople(a, b);
        d.addExpense(busExp);
        expenses.add(busExp);
        
        List<Splitter.SplitOut> outList = new Splitter(people, expenses).exec();
        for (Splitter.SplitOut o : outList) {
            System.out.println(o.printStatement());
        }
    }

}
