package ru.korvin.dominion.mechanic.baseObject.quest.work.customers;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CustomersQueue implements Iterator<Customer>, Iterable<Customer> {
    List<Customer> customers = new LinkedList<Customer>();

    public void initNewDay() {

    }


    public void add(Customer customer) {
        int i;
        for (i = 0; i < customers.size(); i++) {
            if (customers.get(i).timeArraive.before(customer.timeArraive)) {
                break;
            }
        }
        customers.add(i, customer);
    }

    @Override
    public boolean hasNext() {
        return customers.size() > 0;
    }

    @Override
    public Customer next() {
        Customer next = customers.get(0);
        customers.remove(0);
        return next;
    }

    @Override
    public void remove() {
        customers.remove(0);
    }

    @Override
    public Iterator<Customer> iterator() {
        return this;
    }
}
