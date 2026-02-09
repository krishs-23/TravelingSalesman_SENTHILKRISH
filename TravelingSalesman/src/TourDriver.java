import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * TourDriver.java
 * 
 * Name: Krish Senthil
 * Period: 2nd
 * Last Revision Date: 02/04/2026
 * Description:
 *  
 */
public class TourDriver
{
    public static void main(String[] args)
    {
        Tour test = new Tour();
        try
        {
            String filename = "./input/tsp1000.txt"; // Try out different sample input files!
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
            	point p = new point(x, y);
            	
            	//TEMP test.insertNearest(p);
            	test.insertSmallest(p);
            	
                nextLine = bf.readLine();
            }
            
            bf.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        System.out.println(test);
        System.out.println("Tour length = " + test.length());
        System.out.println("Number of points = " + test.size());

        test.draw();
    }
}
