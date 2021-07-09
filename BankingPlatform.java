package assignment;
import java.util.*;

public class BankingPlatform {
	Map<Address,List<User>> adrMap=new TreeMap<>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//ranking system of offices based on no. of employees
		List<Address> addr = new ArrayList<>();
		addr.add(new Address("DEL",110001));
		addr.add(new Address("BLR",560001));
		addr.add(new Address("GN",201305));
		addr.add(new Address("BLR",560002));
			
		List<User> users=new ArrayList();
		users.add(new User(02,"User2",new ArrayList<>(Arrays.asList(
												addr.get(0),addr.get(1)))));
		users.add(new User(01,"User1",new ArrayList<>(Arrays.asList(
				addr.get(2),
				addr.get(0)))));
		
		users.add(new User(03,"User3",new ArrayList<>(Arrays.asList(
				addr.get(2),
				addr.get(0)))));
		
		users.add(new User(04,"User4",new ArrayList<>(Arrays.asList(
				addr.get(3),
				addr.get(0)))));
		
		users.add(new User(05,"User5",new ArrayList<>(Arrays.asList(
				addr.get(3),
				addr.get(2)))));
		
		users.add(new User(06,"User6",new ArrayList<>(Arrays.asList(
				addr.get(0)))));
		
		Map<Address,List<User>> adrMap=new TreeMap<>();
		
		users.stream().forEach(usr->usr.address.stream()
				.forEach(adr->{
					if(!adrMap.containsKey(adr))
						adrMap.put(adr,new ArrayList<>());
					List userList=adrMap.get(adr);
					userList.add(usr);
					adrMap.put(adr,userList);
				}));
		
		adrMap.forEach((K,E)->System.out.println("Address : "+K.city+" "+K.zip+"| Users : "+E));;
	}
}

class User{
 Integer id;
 String name;
 List<Address> address;
 
 public User(int id,String name,ArrayList cities) {
	 this.id=id;
	 this.name=name;
	 this.address=cities;
 }
 
 public int hashCode() {
	 return this.id.hashCode();
 }
 
 public String toString() {
	 return this.name;
 }
}

class Address implements Comparable{
	String city;
	int zip; 	
	int noOfUsers=0;
	
	public Address(String city,int zip) {
		this.city=city;
		this.zip=zip;
		noOfUsers++;
	}
	
	public int compareTo(Object ad) {
		Address a=(Address) ad;

		if(this.noOfUsers>a.noOfUsers)
			return -1;
		else if(this.noOfUsers<a.noOfUsers)
			return 1;
		else
			return this.zip-a.zip;
	}
}
