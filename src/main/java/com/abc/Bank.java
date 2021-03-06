package com.abc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<Customer> customers;

    public Bank() {
        customers = new ArrayList<>();
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    /**
     * build summary for all customers
     * @return
     */
    public String customerSummary() {
        String summary = "Customer Summary";
        for (Customer c : customers)
            summary += "\n - " + c.getName() + " (" + format(c.getNumberOfAccounts(), "account") + ")";
        return summary;
    }

    //Make sure correct plural of word is created based on the number passed in:
    //If number passed in is 1 just return the word otherwise add an 's' at the end
    private String format(int number, String word) {
        return number + " " + (number == 1 ? word : word + "s");
    }

    /**
     * get total interest paid for all customers
     * @return
     */
    public BigDecimal totalInterestPaid() {
        BigDecimal total = BigDecimal.ZERO;
            for (Customer c : customers) {
                total = total.add(c.totalInterestEarned());
            }
            return total;
    }

    public Customer getFirstCustomer() {
        return customers.isEmpty() ? null : customers.get(0);
    }
}
