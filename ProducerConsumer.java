package Concurrent;
import java.util.*;

public class ProducerConsumer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> list=new ArrayList<>();
			
		Producer producer=new Producer(list);
		Consumer consumer=new Consumer(list);
		
		Thread produce=new Thread(producer,"Producer");
		Thread consume1=new Thread(consumer,"Consumer1");
		Thread consume2=new Thread(consumer,"Consumer2");
		Thread consume3=new Thread(consumer,"Consumer3");
		
		consume3.setPriority(Thread.MAX_PRIORITY);
		consume2.setPriority(Thread.NORM_PRIORITY);
		consume1.setPriority(Thread.MIN_PRIORITY);
		
		produce.start();
	
		consume3.start();
		consume1.start();
		consume2.start();
	}
}

class Producer extends Thread{
	List<Integer> list;
	
	public Producer(List<Integer> list) {
		this.list=list;
	}
	
	int i=0;
	public void run() {
		while(true)
			produce(i++);
	}
	
	void produce(int i) {
		
		synchronized(list) {
			try {
				while(list.size()==2) {
					System.out.println("List is full. Waiting for consumer.");
					list.wait();
				}
				Thread.sleep(300);
				
				this.list.add(i);
				System.out.println("Produced : "+i);
				list.notify();
			}catch(InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}
}

class Consumer extends Thread{
	List<Integer> list;
	
	public Consumer(List<Integer> list) {
		this.list=list;
	}
	
	public void run() {
		while(true)
			consume();
	}
	
	void consume() {
		synchronized(list) {
			try {
				while(list.isEmpty()) {
					System.out.println("No elements found. "+Thread.currentThread().getName() +" Waiting...");
					list.wait();
				}
				Thread.sleep(500);
				System.out.println("Consumed : "+Thread.currentThread().getName()+"--"+list.get(0));
				list.remove(0);
				list.notify();
			}catch(InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}
}
