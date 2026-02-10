/**
 * Tour.java
 * 
 * Name: Krish Senthil
 * Period: 2nd
 * Last Revision Date: 02/09/2026
 * Description: The tour class uses a circular linked list to represent a tour of points, 
 *  and has methods to calculate the length of the tour, insert new points using two different 
 *  heuristics, and draw the tour using StdDraw.
 *  
 */
public class Tour {
   
	/*
	 * Inner class Node to represent each point in the tour as a node in a circular linked list
	 */
    private class Node {
        private Point data;
        private Node next;
    }
    
    // Instance variables for the Tour class
    private Node home;
    private int size;
   
    /*
     * Default constructor to initialize an empty tour with home set to null and size set to 0
     */
    public Tour() {
    	home = null;
    	size = 0;
    }

    /*
     * Constructor that takes 4 Point objects and creates a tour by linking them in the order 
     * they are given, and then linking the last point back to the first to form a circular tour
     * @param a the first point in the tour
     * @param b the second point in the tour
     * @param c the third point in the tour
     * @param d the fourth point in the tour
     */
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
    
    /*
     * Method to check if the tour is empty by checking if the size is 0
     * @return true if the tour is empty, false otherwise
     */
    public boolean isEmpty() {
    	return size == 0;
    }
    
    /*
     * Method to return the number of points in the tour by returning the size variable
     * @return the number of points in the tour
     */
    public int size() {
    	return size;
    }

    /*
     * Method to calculate the total length of the tour by iterating through the circular linked list
     * @return the total length of the tour as a double
     */
    public double length() {
    	// If the tour is empty, return 0.0 as the length
        if (home == null) {
			return 0.0;
		}
        
        // Initialize totalDistance to 0.0 and start from the home node
        double totalDistance = 0.0;
		Node curr = home;
		
		// Iterate through the circular linked list, adding the distance from each point to the next
		for (int i = 0; i < size; i++) {
            totalDistance += curr.data.distanceTo(curr.next.data);
            curr = curr.next;
        }

		// Return the total distance calculated for the tour
        return totalDistance;
    }

    /*
     * Method to return string of the tour by iterating through the circular linked list 
     * and appending each point's string 
     * @return a string of the tour with each point on a new line
     */
    public String toString() {
    	// If the tour is empty, return an empty string
    	if (home == null) {
            return "";
        }

    	// Use a StringBuilder to build the string of the tour
        StringBuilder sb = new StringBuilder();
        Node current = home;

        // Iterate through the circular linked list, appending each point then a newline
        for (int i = 0; i < size; i++) {
            sb.append(current.data.toString());
            sb.append("\n");
            current = current.next;
        }

        // Return the final string of the tour
        return sb.toString();
    }

    /*
     * Method to draw the tour using StdDraw by iterating through the circular linked list
     *  and drawing a line from each point to the next 
     */
    public void draw() {
    	// If the tour is empty, return without drawing anything
    	if (home == null) {
            return;
        }

    	// Start from the home node, setting current to home
        Node current = home;
        
        // Iterate through the circular linked list, drawing a line from each point to the next
        for (int i = 0; i < size; i++) {
            current.data.drawTo(current.next.data);
            current = current.next;
        }
    }
    
    /*
     * Method to insert a new point into the tour using the nearest neighbor heuristic by 
     * iterating through the circular linked list to find the point that is closest to the 
     * new point, and then inserting the new point after that closest point in the tour
     * @param p the new point to be inserted into the tour
     */
    public void insertNearest(Point p) {
    	// If the tour is empty, create a new node with the point and set it as home
    	if (home == null) {
            Node n = new Node();
            n.data = p;
            n.next = n; 
            home = n;
            size = 1;
            return;
        }

    	// Initialize variables to keep track of the closest node and the minimum distance found
        Node bestNode = null;
        double minDist = Double.MAX_VALUE;
        Node current = home;

        // Iterate through the circular linked list to find the node that is closest to the new one
        for (int i = 0; i < size; i++) {
            double dist = current.data.distanceTo(p);
            
            // If the distance from the current node to the new point is less than the minimum 
            // distance found so far, update minimum distance and set best node to current node
            if (dist <= minDist) {
                minDist = dist;
                bestNode = current;
            }
            
            // Move to the next node in the circular linked list
            current = current.next;
        }

        // After finding the closest node, create a new node for the new point and insert it
        // after the closest node
        Node n = new Node();
        n.data = p;
        n.next = bestNode.next;
        bestNode.next = n;
        size++;
    }
   
    /*
	 * Method to insert a new point into the tour using the smallest increase heuristic by 
	 * iterating through the circular linked list to find the edge that would result in the 
	 * smallest increase in tour length if the new point were inserted between those two points, 
	 * and then inserting the new point between those two points in the tour
	 * @param p the new point to be inserted into the tour
	 */
    public void insertSmallest(Point p) {
    	// If the tour is empty, create a new node with the point and set it as home
    	if (home == null) {
            Node n = new Node();
            n.data = p;
            n.next = n;
            home = n;
            size = 1;
            return;
        }

    	// Initialize variables to keep track of the best node to insert after and the minimum 
    	// increase in tour length found
        Node bestNode = null;
        double minChange = Double.MAX_VALUE;
        Node current = home;

        // Iterate through the circular linked list to find the edge 
        // that would result in the smallest
        for (int i = 0; i < size; i++) {
            double currentEdge = current.data.distanceTo(current.next.data);
            double newEdges = current.data.distanceTo(p) + p.distanceTo(current.next.data);
            double change = newEdges - currentEdge;

            // If the increase in tour length from inserting the new point between the current node
            // and the next node is less than the min increase found so far, update minimum change
            if (change < minChange) {
                minChange = change;
                bestNode = current;
            }
            
            // Move to the next node in the circular linked list
            current = current.next;
        }

        // After finding the best node to insert after, create a new node for the new point and 
        // insert it between the best node and its next node
        Node n = new Node();
        n.data = p;
        n.next = bestNode.next;
        bestNode.next = n;
        size++;
    }
}
