package net.radityalabs.contactapp;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by radityagumay on 4/18/17.
 */

public class ShapeTest {

    private Rectangle rectangle;
    private Circle circle;

    @Before
    public void setup() {
        rectangle = new Rectangle();
        circle = new Circle();
    }

    @Test
    public void circleTest() {
        circle.radius = 10;

        double actual = circle.radius * circle.radius * Math.PI;
        double expected = 314.1592653589793;
        assertEquals(expected, actual);
    }

    @Test
    public void rectangleTest() {
        rectangle.length = 10;
        rectangle.height  = 5;

        double actual = rectangle.height  * rectangle.length;
        double expected = 50;
        assertEquals(expected, actual);
    }

    public static class Circle {
        public double radius;
    }

    public static class Rectangle {
        public double length;
        public double height ;
    }
}


