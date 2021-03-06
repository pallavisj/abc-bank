package com.abc;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class CustomerTest {

    @Test //Test customer statement generation
    public void testApp() {

        Account checkingAccount = new CheckingAccount();
        Account savingsAccount = new SavingAccount();

        Customer henry = new Customer("Henry").openAccount(checkingAccount).openAccount(savingsAccount);

        checkingAccount.deposit(BigDecimal.valueOf(100.0));
        savingsAccount.deposit(BigDecimal.valueOf(4000.0));
        savingsAccount.withdraw(BigDecimal.valueOf(200.0));

        savingsAccount.transfer(BigDecimal.valueOf(300.0), checkingAccount);

        assertEquals("Statement for Henry\n" +
                "\n" +
                "Checking Account\n" +
                "  deposit $100.00\n" +
                "  deposit $300.00\n" +
                "Total $400.00\n" +
                "\n" +
                "Savings Account\n" +
                "  deposit $4,000.00\n" +
                "  withdrawal $200.00\n" +
                "  withdrawal $300.00\n" +
                "Total $3,500.00\n" +
                "\n" +
                "Total In All Accounts $3,900.00", henry.getStatement());
    }

    @Test
    public void testOneAccount() {
        Customer oscar = new Customer("Oscar").openAccount(new SavingAccount());
        assertEquals(1, oscar.getNumberOfAccounts());
    }

    @Test
    public void testTwoAccount() {
        Customer oscar = new Customer("Oscar")
                .openAccount(new SavingAccount());
        oscar.openAccount(new CheckingAccount());
        assertEquals(2, oscar.getNumberOfAccounts());
    }

    @Test
    public void testThreeAcounts() {
        Customer oscar = new Customer("Oscar").openAccount(new SavingAccount());
        oscar.openAccount(new CheckingAccount());
        oscar.openAccount(new MaxiSavingAccount());
        assertEquals(3, oscar.getNumberOfAccounts());
    }
}
