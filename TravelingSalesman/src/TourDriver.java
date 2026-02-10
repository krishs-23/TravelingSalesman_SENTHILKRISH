import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * TourDriver.java
 * 
 * Name: Krish Senthil
 * Period: 2nd
 * Last Revision Date: 02/09/2026
 * Description: A driver class to read points from a file, 
 *  insert them into a Tour using both heuristics, and display the results
 */
public class TourDriver {
	
	/*
	 * Main method to read points from a file, insert them into a Tour using both heuristics, 
	 * and display the results while drawing the tour using StdDraw
	 */
    public static void main(String[] args) {
    	// Create a new Tour object to hold the points
        Tour test = new Tour();
        
        // Read points from a file and insert them into the tour using both heuristics
        try {
            String filename = "./input/tsp10.txt"; // Try out different sample input files!
            BufferedReader bf = new BufferedReader(new FileReader(new File(filename)));
            String scale = bf.readLine();
            String[] points = scale.split(" ");
            StdDraw.setXscale(0, Double.parseDouble(points[0]));
            StdDraw.setYscale(0, Double.parseDouble(points[1]));
            String nextLine = bf.readLine();
            
            while (nextLine != null && !nextLine.equals("")) {
                // Add your code here to parse each line of the input.
            	String[] coordinates = nextLine.trim().split("\\s+");
            	double x = Double.parseDouble(coordinates[0]);
            	double y = Double.parseDouble(coordinates[1]);
            	Point p = new Point(x, y);
            	
            	test.insertNearest(p);
            	test.insertSmallest(p);
            	
                nextLine = bf.readLine();
            }
            
            bf.close();
        }
        // Catch any exceptions that occur during file reading and parsing
        catch (Exception e) {
            e.printStackTrace();
        }

        // Print the tour, its length, and the number of points in the tour
        System.out.println(test);
        System.out.printf("Tour length = %.4f\n", test.length());
        System.out.println("Number of points = " + test.size());

        // Draw the tour using StdDraw
        test.draw();
    }
}
