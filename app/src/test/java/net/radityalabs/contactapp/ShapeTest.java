package net.radityalabs.contactapp;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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

    private static final double[] RECTANGLE_LENGTH = new double[]{
            1, 2, 3, 4, 5
    };

    private static final double[] RECTANGLE_HEIGHT = new double[]{
            1, 2, 3, 4, 5
    };

    private static final double[] RECTANGLE_EXPECTED = new double[]{
            1, 4, 9, 16, 25
    };

    @Test
    public void calcAll() {
        List<Rectangle> rects = new ArrayList<>(5);
        for (int i = 0; i < 5; i++) {
            Rectangle rect = new Rectangle();
            rect.length = RECTANGLE_LENGTH[i];
            rect.height  = RECTANGLE_HEIGHT[i];
            rects.add(rect);
        }
        for (int i = 0; i < rects.size(); i++) {
            double actual = rects.get(i).height  * rects.get(i).length;
            double expected = RECTANGLE_EXPECTED[i];
            assertEquals(expected, actual);
        }
    }

    public static class Circle {
        public double radius;
    }

    public static class Rectangle {
        public double length;
        public double height ;
    }
}


