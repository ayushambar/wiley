package BankingPlatform;
import java.util.*;

public class Main {
	static List<User> users=new ArrayList();;
	static List<Branch> branch=new ArrayList();

	public static void main(String[] args) {
		//creating hardcoded branches
		branch.add(new Branch("1001","BRB005","HYD"));
		branch.add(new Branch("1002","BRB010","PAT"));
		branch.add(new Branch("1003","BRB015","DWR"));
		branch.add(new Branch("1004","BRB020","DEL"));
		branch.add(new Branch("1005","BRB025","BLR"));
		
		newUser();
	}
	
	static void newUser() {
		Branch brn;
		Scanner sc=new Scanner(System.in);
		System.out.print("WELCOME!\nPlease enter your name :");
		User usr=new User(sc.nextLine());
		System.out.println("Where would you like to open your account? (HYD | PAT | DWR | DEL | BLR)");
		String city=sc.nextLine();
		try {
			brn=branch.stream().filter(br->br.city.equalsIgnoreCase(city)).findFirst().get();
			usr.addAccount(brn);
		}catch(NoSuchElementException e) {
			System.out.println("This branch does not exist.");
			return;
		}
		users.add(usr);
		existingUser();
	}
	
	static void existingUser() {
		User usr;
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter user ID:");
		try {
			usr=users.stream().filter(user->user.ID==sc.nextInt()).findFirst().get();
		}catch(NoSuchElementException e) {
			System.out.println("ID does not exist.");
			return;
		}
		System.out.println("Welcome "+usr.name+" !");
		usr.selectAccount("12-07-2021");
	}
}


class User{
	int ID;
	static int idItr=0;
	String name;

	List<Account> accounts = new ArrayList();
	
	User(String name){
		this.ID=idItr++;
		System.out.println("Your user ID is "+this.ID+". Please remember it.");
		this.name=name;
	}
	void selectAccount(String date) {
		System.out.print("You have accounts in : ");
		accounts.stream().forEach(ac->System.out.print(ac.branchCity + " "));
		System.out.println();
		
		System.out.print("Choose account : ");
		Scanner sc=new Scanner(System.in);
		String br=sc.nextLine();
		
		Account ac;
		try {
			ac=accounts.stream().filter(a->a.branchCity.equalsIgnoreCase(br)).findFirst().get();
		}catch(NoSuchElementException e) {
			System.out.println("You do not have account in this branch.");
			return;
		}
		selectOperation(ac,date);
	}
	public void selectOperation(Account ac,String date) {
		Scanner sc=new Scanner(System.in);
		int choice;
		while(true) {
			System.out.println("1> Deposit    2> Withdraw    3> Check Balance    4> Account Statement    5> Previous menu    s6> Exit");	
			switch(sc.nextInt()) {
			case 1: ac.deposit(date);
					break;
			case 2: ac.withdraw(date);
					break;
			case 3: ac.checkBalance();
					break;
			case 4: ac.statement();
					break;
			case 5: selectAccount(date);
					break;
			case 6: System.out.println("Program terminating.");
					System.exit(0);
			}
		}
	}
	void addAccount(Branch br) {
		accounts.add(new Account(this.ID,br));
	}
}

class Branch{
	String ID;
	String ifsc;
	String city;
	
	Branch(String id,String ifsc,String city){
		this.ID=id;
		this.ifsc=ifsc;
		this.city=city;
	}
}

class Account{
	static int accItr=1001;
	int accountNumber;
	int userId;
	String branchCity;
	String branchId;
	int balance;
	List<Transaction> transactions=new ArrayList();
	
	Account(int userId,Branch br){
		this.accountNumber=accItr++;
		this.userId=userId;
		this.branchId=br.ID;
		this.balance=0;
		this.branchCity=br.city;
	}
	
	public void deposit(String date) {
		Scanner sc=new Scanner(System.in);
		System.out.print("Amount to deposit : ");
		int amount=sc.nextInt();
		this.balance+=amount;
		transactions.add(new Transaction("Credit",date,amount));
	//	sc.close();
	}
	
	public void withdraw(String date) {
		if(this.balance<=0) 
			System.out.println("Not enough balance to withdraw.");

		Scanner sc=new Scanner(System.in);
		System.out.print("Amount to withdraw : ");
		int amount=sc.nextInt();
		if(this.balance-amount<0) {
			System.out.println("Not enough balance to withdraw.");
			return;
		}
		
		this.balance-=amount;
		transactions.add(new Transaction("Debit",date,amount));
	}
	
	public void statement() {
		System.out.println("Account statement -- ");
		transactions.stream().forEach(t -> System.out.println(t.type+"\t"+t.date+"\t"+t.amount));
		System.out.println("Current balance -- "+this.balance);
	}
	
	public void checkBalance() {
		System.out.println("Current balance : "+ this.balance);
	}
}

class Transaction{
	String type;
	String date;
	int amount;
	
	Transaction(String type,String date,int amount){
		this.type=type;
		this.date=date;
		this.amount=amount;
	}
}



