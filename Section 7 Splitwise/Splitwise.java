import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

enum Split{EXACT,EQUAL,PERCENT};

class ExpensePair{
    User user;
    Double expense;

    public ExpensePair(User user, Double expense) {
        this.user = user;
        this.expense = expense;
    }
}

class User{
    int id;
    String name;
    static int userId = 1;
    public User(int id, String name) {
        this.id = id;
        this.name = name;
        this.totalExpenseSoFar = 0;
        this.userExpenseSheet = new ArrayList<>();
    }

    List<ExpensePair> userExpenseSheet;
    double totalExpenseSoFar;

    void addToUserExpenseSheet(User user, double expense){
        if (this == user){
            return;
        }
        this.totalExpenseSoFar += expense;
        for(ExpensePair ep: userExpenseSheet){
            if (ep.user == user){
                ep.expense += expense;
                return;
            }
        }
        userExpenseSheet.add(new ExpensePair(user,expense));
    }

    double getTotalExpenseSoFar(){
        return totalExpenseSoFar;
    }

    public int getUniqueId(){
        return userId++;
    }

    void printTotalBalance(){
        if (totalExpenseSoFar > 0){
            System.out.println(name + " owes a total of " + totalExpenseSoFar + "\n");
        } else
        {
            System.out.println(name + " is owed a total of " + totalExpenseSoFar + "\n");
        }
    }
}

class Expense{
    int id;
    String description;
    static int expenseId = 1;
    User creditor;
    List<User> defaulters;
    double exactTotalAmount;

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Split getSplit() {
        return split;
    }

    public List<Double> getPercentDistribution() {
        return percentDistribution;
    }

    public void setPercentDistribution(List<Double> percentDistribution) {
        this.percentDistribution = percentDistribution;
    }

    public List<Double> getExactDistribution() {
        return exactDistribution;
    }

    public void setExactDistribution(List<Double> exactDistribution) {
        this.exactDistribution = exactDistribution;
    }

    public Expense(User creditor, Split split, List<User> defaulters, double amount) {
        this.id = getUniqueId();
        this.exactTotalAmount = amount;
        this.defaulters = defaulters;
        this.creditor = creditor;
        this.split = split;

        if (split == Split.EXACT ){
    }

    Split split;
    List<Double> percentDistribution;
    List<Double> exactDistribution;
    public int getUniqueId(){
        return expenseId++;
    }
}

public class Splitwise {
    List<User> users;
    HashSet<Integer> userIds;
    boolean calculateExpenses(){}
    boolean verifyUsers(){}
    void registerUser(){}
    void addExpense(){}
    void printBalanceForAllUsers(){}
}
