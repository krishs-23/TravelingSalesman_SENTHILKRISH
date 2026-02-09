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
        if (home == null) {
			return 0.0;
		}
        
        double totalDistance = 0.0;
		Node curr = home;
		
		for (int i = 0; i < size; i++) {
            totalDistance += curr.data.distanceTo(curr.next.data);
            curr = curr.next;
        }

        return totalDistance;
    }

    public String toString() {
    	if (home == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        Node current = home;

        for (int i = 0; i < size; i++) {
            sb.append(current.data.toString());
            
            if (i < size - 1) {
                sb.append("\n");
            }
            
            current = current.next;
        }

        return sb.toString();
    }

    public void draw() {
    	if (home == null) {
            return;
        }

        Node current = home;
        
        for (int i = 0; i < size; i++) {
            current.data.drawTo(current.next.data);
            current = current.next;
        }
    }
    
    public void insertNearest(Point p) {
    	if (home == null) {
            Node n = new Node();
            n.data = p;
            n.next = n; 
            home = n;
            size = 1;
            return;
        }

        Node bestNode = null;
        double minDist = Double.MAX_VALUE;
        Node current = home;

        for (int i = 0; i < size; i++) {
            double dist = current.data.distanceTo(p);
            
            if (dist < minDist) {
                minDist = dist;
                bestNode = current;
            }
            
            current = current.next;
        }

        Node n = new Node();
        n.data = p;
        n.next = bestNode.next;
        bestNode.next = n;
        size++;
    }
   
    public void insertSmallest(Point p) {
    	if (home == null) {
            Node n = new Node();
            n.data = p;
            n.next = n;
            home = n;
            size = 1;
            return;
        }

        Node bestNode = null;
        double minChange = Double.MAX_VALUE;
        Node current = home;

        for (int i = 0; i < size; i++) {
            double currentEdge = current.data.distanceTo(current.next.data);
            double newEdges = current.data.distanceTo(p) + p.distanceTo(current.next.data);
            double change = newEdges - currentEdge;

            if (change < minChange) {
                minChange = change;
                bestNode = current;
            }
            current = current.next;
        }

        Node n = new Node();
        n.data = p;
        n.next = bestNode.next;
        bestNode.next = n;
        size++;
    }
}
