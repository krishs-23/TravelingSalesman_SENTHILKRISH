/**
 * TourTest.java
 * 
 * Name: Krish Senthil
 * Period: 2nd
 * Last Revision Date: 02/09/2026
 * Description: A unit test class for the Tour class using JUnit
 */

import static org.junit.Assert.*;
import org.junit.Test;

public class TourTest {

    /*
     * Test the default constructor creates an empty tour with size 0 and isEmpty() true
     */
    @Test
    public void testConstructorDefault() {
        Tour t = new Tour();
        assertEquals(0, t.size());
        assertTrue(t.isEmpty());
    }

    /*
     * Test the constructor with 4 points creates a tour of size 4 and isEmpty() false
     */
    @Test
    public void testConstructor4Points() {
        Point a = new Point(0, 0);
        Point b = new Point(10, 0);
        Point c = new Point(10, 10);
        Point d = new Point(0, 10);
        
        Tour t = new Tour(a, b, c, d);
        
        assertEquals(4, t.size());
        assertFalse(t.isEmpty());
    }

    /*
	 * Test that size() increments correctly when inserting points
	 */
    @Test
    public void testSizeIncrements() {
        Tour t = new Tour();
        assertEquals(0, t.size());
        
        t.insertNearest(new Point(0,0));
        assertEquals(1, t.size());
        
        t.insertNearest(new Point(1,1));
        assertEquals(2, t.size());
        
        t.insertSmallest(new Point(2,2));
        assertEquals(3, t.size());
    }

    /*
     * Test that length() returns 0.0 for an empty tour, and calculates length 
     */
    @Test
    public void testLengthEmpty() {
        Tour t = new Tour();
        assertEquals(0.0, t.length(), 0.0001);
    }

    /*
     * Test that length() returns 0.0 for a tour with one point, and calculates length for points
     */
    @Test
    public void testLengthSinglePoint() {
        Tour t = new Tour();
        t.insertNearest(new Point(100, 100));
        assertEquals(0.0, t.length(), 0.0001);
    }

    /*
     * Test that length() calculates the correct distance for two points, and returns to the start
     */
    @Test
    public void testLengthTwoPoints() {
        Tour t = new Tour();
        t.insertNearest(new Point(0, 0));
        t.insertNearest(new Point(10, 0));
        
        assertEquals(20.0, t.length(), 0.0001);
    }

    /*
     * Test that length() calculates the correct distance for a square of points
     */
    @Test
    public void testLengthSquare() {
        Point a = new Point(0, 0);
        Point b = new Point(10, 0);
        Point c = new Point(10, 10);
        Point d = new Point(0, 10);
        Tour t = new Tour(a, b, c, d);
        
        assertEquals(40.0, t.length(), 0.0001);
    }

    /*
     * Test that toString() returns an empty string for an empty tour, and formats points correctly
     */
    @Test
    public void testToStringEmpty() {
        Tour t = new Tour();
        assertEquals("", t.toString());
    }

    /*
     * Test that toString() formats points correctly, with each point on a new line 
     * and in the format (x, y)
     */
    @Test
    public void testToStringFormat() {
        Tour t = new Tour();
        t.insertNearest(new Point(0,0));
        t.insertNearest(new Point(5,5));
        
        // Must end with newline
        String result = t.toString();
        String expected = "(0.0, 0.0)\n(5.0, 5.0)\n";
        
        assertEquals(expected, result);
    }

    /*
     * Test that draw() does not throw an exception when called on an empty tour
     */
    @Test
    public void testDrawEmpty() {
        // Should return without error
        Tour t = new Tour();
        try {
            t.draw();
        } catch (Exception e) {
            fail("Draw crashed on empty list");
        }
    }

    /*
     * Test that draw() does not throw an exception when called on a filled tour 
     */
    @Test
    public void testDrawFilled() {
        Point a = new Point(0, 0);
        Point b = new Point(10, 0);
        Point c = new Point(5, 10);
        Point d = new Point(5, 5); // Use a valid point instead of null
        Tour t = new Tour(a, b, c, d); 
        
        try {
            t.draw();
        } catch (Exception e) {
            fail("Draw crashed on filled list");
        }
    }

    /*
	 * Test that insertNearest() correctly inserts the first point into an empty tour, 
	 * and that size increments
	 */
    @Test
    public void testNearestEmpty() {
        Tour t = new Tour();
        t.insertNearest(new Point(5, 5));
        
        assertEquals(1, t.size());
    }

    /*
     * Test that insertNearest() correctly inserts points in the order they are added, 
     * and calculates length 
     */
    @Test
    public void testNearestChoiceLogic() {
        Tour t = new Tour();
        t.insertNearest(new Point(0, 0));   // A
        t.insertNearest(new Point(100, 0)); // B
        t.insertNearest(new Point(2, 0));   // C

        assertEquals(200.0, t.length(), 0.0001);
    }

    /*
     * Test that insertNearest() finds the nearest point to the current tour, 
     * and it creates a square when points are added in order
     */
    @Test
    public void testNearestSquareSequence() {
        // If points are in order, nearest should find the optimal square
        Tour t = new Tour();
        t.insertNearest(new Point(0,0));
        t.insertNearest(new Point(10,0));
        t.insertNearest(new Point(10,10));
        t.insertNearest(new Point(0,10));
        
        assertEquals(40.0, t.length(), 0.0001);
    }

    /*
     * Test that insertSmallest() correctly inserts the first point into an empty tour, 
     * and that size increments 
     */
    @Test
    public void testSmallestEmpty() {
        Tour t = new Tour();
        t.insertSmallest(new Point(1, 1));
        assertEquals(1, t.size());
    }

    /*
     * Test that insertSmallest() correctly finds the insertion point that minimizes the increase 
     * in tour length, and calculates length
     */
    @Test
    public void testSmallestDetour() {
        Tour t = new Tour();
        t.insertSmallest(new Point(0, 0));
        t.insertSmallest(new Point(10, 0));
        t.insertSmallest(new Point(5, 5));
        
        assertEquals(24.142, t.length(), 0.001);
    }
    
}
