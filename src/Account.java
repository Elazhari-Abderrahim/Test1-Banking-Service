import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Account implements AccountService {
    private int balance;
    private List<Transaction> transactions;

    public Account() {
        this.balance = 0;
        this.transactions = new ArrayList<>();
    }

    @Override
    public void deposit(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Le montant doit être positif");
        }
        balance += amount;
        transactions.add(new Transaction(new Date(), amount, balance));
    }

    @Override
    public void withdraw(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Le montant doit être positif");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Fonds insuffisants");
        }
        balance -= amount;
        transactions.add(new Transaction(new Date(), -amount, balance));
    }

    @Override
    public void printStatement() {
        System.out.println("Date       || Amount || Balance");
        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction t = transactions.get(i);
            System.out.printf("%tD || %6d || %7d%n", t.getDate(), t.getAmount(), t.getBalance());
        }
    }

    private static class Transaction {
        private Date date;
        private int amount;
        private int balance;

        public Transaction(Date date, int amount, int balance) {
            this.date = date;
            this.amount = amount;
            this.balance = balance;
        }

        public Date getDate() { return date; }
        public int getAmount() { return amount; }
        public int getBalance() { return balance; }
    }
}