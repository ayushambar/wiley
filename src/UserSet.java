import java.time.Instant;
import java.util.*;
import java.util.stream.Stream;

public class UserSet {

    public static void main(String args[]){

        Set set = new HashSet(); 

        set.add(11);
        set.add(2);
        set.add("Hello");
        set.add(null);
        set.add("");
        //5. order not retained

        set.forEach(System.out::println);


       // 1. System.out.println(Instant.now());
        System.out.println("*****************");

        //2.set.parallelStream().forEachOrdered(e1->System.out.println("Parallel Ordered "+e1));

        set.parallelStream().forEach(ee-> System.out.println("Parallel "+ee));
        System.out.println("*****************");

        System.out.println("TREE SET.... ");
        //3. treemap doesnt allow null values
        Set<String> set1 = new TreeSet<>(Comparator.reverseOrder()); // inherits from sorted set

        set1.add("Hi");
        set1.add("Hello");
        set1.add("Hola!");
        set1.add(new String("HI"));
        set1.add(new String("Hi"));
        set1.add("Hi");		//nothing happens on duplicate values;

        set1.forEach(System.out::println);

        // custom objects stored in set
        Set<User> userSet = new TreeSet(Comparator.comparing(User::getId)); 
        //4. ClassCastException if we dont pass the comparator for custom object
        //Set<User> userSet = new TreeSet<>((u1,u2)->u2.getName().compareTo(u1.getName()));
        //Set<User> userSet = new TreeSet();

        userSet.add(new User(1,"User"));
        userSet.add(new User(3,"User3"));
        userSet.add(new User(5,"User5"));
        userSet.add(new User(2,"User2"));
        userSet.add(new User(4,"User4"));
        userSet.add(new User(5,"User6"));
        //userSet.add(new User(6,"User6"));

        userSet.stream().forEach(u-> System.out.println("User "+"ID: "+u.getId()+"::"+u.getName()));
        System.out.println("*****************");
        
        Set<Type> customSet = new TreeSet<>();

        customSet.add(new Type(new Integer(10)));
        customSet.add(new Type("Elephant"));
        customSet.add(new Type(new Integer(4)));
        customSet.add(new Type(new Integer(6)));
        customSet.add(new Type(new Integer(5)));

        customSet.add(new Type("Atimal"));
        customSet.add(new Type("Animal"));
        customSet.add(new Type("Horse"));

        customSet.add(new Type(new Integer(2)));

        customSet.add(new Type(null));
        customSet.add(new Type(null));

        //6. customSet.add(null);  --NullPointerException

        customSet.forEach(System.out::println);
    }
}

class Type implements Comparable<Type>{
    private Object object;

    public Type(){

    }
    public Type(Object object){
        this.object = object;
    }
    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "--"+ object +"";
    }

    @Override
    public int hashCode() {
        return object.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int compareTo(Type next) {
        // handle null values at the beginning
        if(this.object == null || next.object == null){
            return -1;
        }
        if(this.object instanceof Integer){
            if(next.object instanceof Integer){
                int i = (int)next.object;
                int j = (int)this.object;
                return j - i;
            }else if(next.object instanceof String){
                return -1;
            }
            return 0;
        }else if(this.object instanceof String){
            if(next.object instanceof String){
                String str1 = (String)this.object;
                String str2 = (String)next.object;
                return str1.compareTo(str2);
            }else if(next.object instanceof Integer){
                return 1;
            }
            return 0;
        }
        return 0;
    }
}
class User {
    private int id;
    private String name;

    public User(){

    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return this.getId();
    }
}