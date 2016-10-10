/*
 * Copyright (c) 2016 ChargeBee Inc
 * All Rights Reserved.
 */
package twsplits.splitter;

import java.util.ArrayList;
import java.util.List;
import twsplits.models.Expense;
import twsplits.models.Person;

/**
 *
 * @author vaibhav
 */
public class Splitter {

    private List<Person> people;
    private List<Expense> expenses;

    public Splitter(List<Person> people, List<Expense> expenses) {
        this.people = people;
        this.expenses = expenses;
    }

    public List<SplitOut> exec() {
        initIndividualExpenses();

        List<SplitOut> out = new ArrayList<>();
        people.forEach(p -> out.add(individualSplit(p)));
        return out;
    }

    private void initIndividualExpenses() {
        for (Expense e : expenses) {
            Double sharingAmount = e.amount() / e.forPeople().size();
            for (Person p : e.forPeople()) {
                p.addToOwnExpense(sharingAmount);
            }
        }
    }

    private SplitOut individualSplit(Person p) {
        Double extraPaidAmount = p.totalExpense() - p.ownExpense();
        SplitType type = extraPaidAmount > 0 ? SplitType.Debit : SplitType.Credit;
        return new SplitOut(p, type, Math.abs(extraPaidAmount));
    }

    enum SplitType {

        Credit, Debit;
    }

    public class SplitOut {

        private Person p;
        private SplitType type;
        private Double amount;

        public SplitOut(Person p, SplitType type, Double amount) {
            this.p = p;
            this.type = type;
            this.amount = amount;
        }

        public String printStatement() {
            if (amount == 0) {
                return p.name() + " won't pay or get any amout.";
            }
            switch (type) {
                case Credit:
                    return p.name() + " has to give " + amount;
                case Debit:
                    return p.name() + " gets " + amount;
                default:
                    throw new RuntimeException("not handled type");
            }
        }
    }
}
