//
//public class MyLinkedList {
//	Node head;
//	
//	public static class Node{
//		Object value;
//		Node next;
//		
//		Node(Object value){
//			this.value=value;
//			next=null;
//		}
//	}
//	//try passing this instead of list
//	public static MyLinkedList add(MyLinkedList l, Object o) {
//		Node new_node = new Node(o);
//		
//		if(l.head==null)
//			l.head=new_node;
//		else {
//			Node last=l.head;
//			while(last.next!=null)
//				last=last.next;
//			
//			last.next=new_node;
//		}
//		return l;
//	}
//	
//	public static void printList(MyLinkedList ll) {
//			Node head=ll.head;
//			while(head!=null) {
//				System.out.print(head.value+" ");
//				head=head.next;
//			}
//	}
//	
//	public static void main(String args[]) {
//		MyLinkedList ll=new MyLinkedList();
//		
//		ll.add(ll,2);
//		ll.add(ll,"Hello");
//		ll.add(ll, 5);
//		
//		ll.printList(ll);
//	}
//}
