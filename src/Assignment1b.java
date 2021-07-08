//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Assignment1b {
//
//	public static void main(String[] args) {
//
//		// step 1: filter employees from users list
//		// step 2: find cost incurred on a project by its employees, it should not go above project budget
//		// step 3: release resource from project whose cost incurred gets beyond the budget, 
//		// however we should be utilizing maximum budget
//
//		/* Google - 1000
//			3 emps -
//				emp1-400
//				emp2-500
//				emp3-300 = 1200
//		*/
//		List<Project> projects=new ArrayList();
//		projects.add(new Project(1,"Bot",1000));
//		projects.add(new Project(2,"Web",1000));
//		projects.add(new Project(3,"Auto",15000));
//		
//		List<User> users = new ArrayList();
//		users.add(new User("01","Ramesh"));
//		users.add(new Employee("02","Suresh",projects.get(1),400));
//		users.add(new Employee("03","Mukesh",projects.get(1),500));
//		users.add(new Employee("04","Rohit",projects.get(1),300));
//		users.add(new Employee("05","Shikhar",projects.get(2),500));
//		
//		Operation o=new Operation(users);
//	}
//
//}
//
//class User{
//	String id;
//	String name;
//	User(String id,String name){
//		this.id=id;
//		this.name=name;
//	}
//}
//
//class Employee extends User{
//	//Address adress;
//	Project project;
//	double salary;
//	Employee(String id,String name,Project p,double salary){
//		super(id,name);
//		this.project=p;
//		this.salary=salary;
//	}
//}
//
//class Address{
//	String city;
//	String zipCode;
//}
//
//class Project{
//	int projectId;
//	String name;
//	double budget;
//	double cost;
//	Project(int id,String name,double budget){
//		this.projectId=id;
//		this.name=name;
//		this.budget=budget;
//		this.cost=0;
//	}
//}
//
//class Operation{
//	List usr;
//	Operation(List users){
//		 usr=users;
//		 this.filter();
//	}
//	
//	public void filter() {
//		usr.stream().filter(user -> user instanceof Employee);	}
////	public void filter(){
////		for(Object element: usr) {
////			if(element instanceof Employee) {
////				((Employee) element).project.cost+=((Employee) element).salary;
////				System.out.println(((Employee) element).project.cost);
////			}
////			if(element instanceof Employee && ((Employee) element).project.budget<((Employee) element).project.cost) {
////				System.out.println("Removed employee ID "+((Employee)element).id+" from project "+((Employee) element).project.name);
////				usr.remove(element);
////			}
////		}
////	}
//}
// 