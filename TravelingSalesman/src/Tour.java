/**
 * Tour.java
 * 
 * Name: Krish Senthil
 * Period: 2nd
 * Last Revision Date: 02/04/2026
 * Description: 
 *  
 */
public class Tour {
   
    private class Node {
        private Point data;
        private Node next;
    }
    
   
    private Node home;
    private int size;
    
    
    public Tour() {
    	home = null;
    	size = 0;
    }

    
    public Tour(Point a, Point b, Point c, Point d) {
    	Node n1 = new Node();
    	Node n2 = new Node();
    	Node n3 = new Node();
    	Node n4 = new Node();
    	
    	n1.data = a;
    	n2.data = b;
    	n3.data = c;
    	n4.data = d;
    	
    	n1.next = n2;
    	n2.next = n3;
    	n3.next = n4;
    	n4.next = n1;
    	
    	home = n1;
    	size = 4;
    }
    
    public boolean isEmpty() {
    	return size == 0;
    }
    
    public int size() {
    	return size;
    }

    
    public double length() {
        
    }

    
    public String toString() {
        return null;
    }

   
    public void draw(){
    	
    }

    
    public void insertNearest(Point p) {
    	
    }

   
    public void insertSmallest(Point p) {
    	
    }
}
