package Concurrent;

import java.util.*;

public class MapAssignment {

	public static void main(String[] args) {

		List<Employee> empList = new ArrayList<>();
		List<Project> prjList = new ArrayList<>(
				Arrays.asList(new Project("01","prj1"),
							new Project("02","prj2"),
							new Project("03","prj3"),
							new Project("04","prj4")
						));
		
		Map<Project,Set<Employee>> prjMap=new HashMap<>();
		
		//thread 1 --> adds employees 
		//thread 2 --> adds projects to employees
		//thread 3 --> transforms employees list having project lists to map of projects and employee list
		
		TaskOne task1=new TaskOne(empList);
		Thread t1=new Thread(task1,"Thread 1");

		TaskTwo task2=new TaskTwo(empList,prjList);
		Thread t2=new Thread(task2);
		
		TaskThree task3=new TaskThree(prjMap,empList);
		Thread t3=new Thread(task3);
		
		t1.start();
		t2.start();
		t3.start();
	}

}

class TaskOne implements Runnable{
	List<Employee> empList;
	public TaskOne(List list) {
		empList=list;
	}
	int i=0;
	public void run() {
		while(i<11)
			addEmployee();
	}
	public void addEmployee() {
		synchronized(empList) {
			try {
				while(!empList.isEmpty() && empList.get(empList.size()-1).projectList.isEmpty()) {
					System.out.println("Waiting for project assignment.");
					empList.wait();
				}
				empList.add(new Employee(Integer.toString(i),"emp"+Integer.toString(i++)));
				System.out.println("Employee added -- "+"ID : "+ empList.get(empList.size()-1).id+" | Name : "+empList.get(empList.size()-1).name);
				Thread.sleep(1000);
				empList.notify();
			}catch(InterruptedException ex) {ex.printStackTrace();}
		}
	}
}

class TaskTwo implements Runnable{
	List<Employee> empList;
	List<Project> prjList;
	public TaskTwo(List empList, List prjList) {
		this.empList=empList;
		this.prjList=prjList;
	}
	
	public void run() {
		while(true)
			assignProjects();
	}
	
	public void assignProjects() {
		Random rand = new Random(); //instance of random class
	      int upperbound = 4;
	      
		synchronized(empList) {
			try {
				while(!empList.get(empList.size()-1).projectList.isEmpty()) {
					System.out.println("Assigned. Waiting for new employee.");
					empList.wait();
				}
				for(int x=0;x<rand.nextInt(upperbound);x++) 
					empList.get(empList.size()-1).projectList.add(prjList.get(rand.nextInt(upperbound)));
				System.out.println("ID - "+empList.get(empList.size()-1).id+" Assigned - "+empList.get(empList.size()-1).projectList);
				Thread.sleep(1000);
				empList.notify();
			}catch(InterruptedException ex) {ex.printStackTrace();}
		}
	}
}

class TaskThree implements Runnable{
	Map<Project, Set<Employee>> prjMap;
	List<Employee> empList;
	
	public TaskThree(Map<Project, Set<Employee>> prj,List<Employee> emp) {
		prjMap=prj;
		empList=emp;
	}
	
	public void run() {
		while(true)
			projectMapping();	
	}
	public void projectMapping() {
		synchronized(empList) {
			Iterator itr=empList.iterator();
			
			try {
				while(empList.isEmpty() || empList.stream().allMatch(e->e.projectList.isEmpty())) {
					System.out.println("Map is empty.");
					empList.wait();
				}
				
				if(itr.hasNext()) {
					Employee e=(Employee) itr.next();
					e.projectList.stream().forEach(p->{
						if(!prjMap.containsKey(p))
							prjMap.put(p, new HashSet<>());
						Set<Employee> list=prjMap.get(p);
						list.add(e);
						prjMap.put(p, list);
					});
					System.out.println(prjMap.get(0).size());
					empList.notifyAll();
				}
			}catch(InterruptedException ex) {ex.printStackTrace();}
		}
	}
}

class Project{
	String id;
	String name;
	public Project(String id, String name) {
		this.id=id;
		this.name=name;
	}
	
	public String toString() {
		return this.name;
	}
}

class Employee{
	String id;
	String name;
	List<Project> projectList=new ArrayList<>();
	
	public Employee(String id, String name) {
		this.id=id;
		this.name=name;
	}
	public void addProject(Project p) {
		this.projectList.add(p);
	}
	
	public String toString() {
		return this.name;
	}
}