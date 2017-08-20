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

    private static final double[] RECTANGLE_LENGTH = new double[]{
            1, 2, 3, 4, 5
    };

    private static final double[] RECTANGLE_HEIGHT = new double[]{
            1, 2, 3, 4, 5
    };

    private static final double[] RECTANGLE_EXPECTED = new double[]{
            1, 4, 9, 16, 25
    };

    private static final double[] CIRCLE_RADIUS = new double[]{
            1, 2, 3, 4, 5
    };

    private static final double[] CIRCLE_EXPECTED = new double[]{
            3.141592653589793, 12.566370614359172, 28.274333882308138, 50.26548245743669, 78.53981633974483
    };

    private static final double[] RECTANGLE_CIRCLE_EXPECTED = new double[]{
            1, 4, 9, 16, 25, 3.141592653589793, 12.566370614359172, 28.274333882308138, 50.26548245743669, 78.53981633974483
    };

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
        rectangle.height = 5;

        double actual = rectangle.height * rectangle.length;
        double expected = 50;
        assertEquals(expected, actual);
    }

    @Test
    public void calcAllRectangle() {
        List<Rectangle> rects = new ArrayList<>(5);
        for (int i = 0; i < 5; i++) {
            Rectangle rect = new Rectangle();
            rect.length = RECTANGLE_LENGTH[i];
            rect.height = RECTANGLE_HEIGHT[i];
            rects.add(rect);
        }
        for (int i = 0; i < rects.size(); i++) {
            double actual = rects.get(i).height * rects.get(i).length;
            double expected = RECTANGLE_EXPECTED[i];
            assertEquals(expected, actual);
        }
    }

    @Test
    public void calcAllShape() {
        List<Object> shapes = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Rectangle rect = new Rectangle();
            rect.length = RECTANGLE_LENGTH[i];
            rect.height = RECTANGLE_HEIGHT[i];
            shapes.add(rect);
        }

        for (int i = 0; i < 5; i++) {
            Circle circle = new Circle();
            circle.radius = CIRCLE_RADIUS[i];
            shapes.add(circle);
        }

        for (int i = 0; i < shapes.size(); i++) {
            if (shapes.get(i) instanceof Rectangle) {
                Rectangle rect = (Rectangle) shapes.get(i);
                double actual = rect.height * rect.length;
                double expected = RECTANGLE_CIRCLE_EXPECTED[i];
                assertEquals(expected, actual);
            } else if (shapes.get(i) instanceof Circle) {
                Circle circle = (Circle) shapes.get(i);
                double actual = circle.radius * circle.radius * Math.PI;
                double expected = RECTANGLE_CIRCLE_EXPECTED[i];
                assertEquals(expected, actual);
            }
        }
    }

    @Test
    public void calcAllShapeRefactor() {
        List<Shape> shapes = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Rectangle rect = new Rectangle();
            rect.length = RECTANGLE_LENGTH[i];
            rect.height = RECTANGLE_HEIGHT[i];
            shapes.add(rect);
        }

        for (int i = 0; i < 5; i++) {
            Circle circle = new Circle();
            circle.radius = CIRCLE_RADIUS[i];
            shapes.add(circle);
        }

        for (int i = 0; i < shapes.size(); i++) {
            double actual = shapes.get(i).getArea();
            double expected = shapes.get(i).getExpected(RECTANGLE_CIRCLE_EXPECTED[i]);
            assertEquals(expected, actual);
        }
    }

    public interface Shape {
        double getArea();

        double getExpected(double expected);
    }

    public interface Draw {
        void draw(double area);
    }

    public static class Circle implements Shape {
        public double radius;

        @Override
        public double getArea() {
            return radius * radius * Math.PI;
        }


        @Override
        public double getExpected(double expected) {
            return expected;
        }
    }

    public static class Rectangle implements Shape {
        public double length;
        public double height;

        @Override
        public double getArea() {
            return length * height;
        }

        @Override
        public double getExpected(double expected) {
            return expected;
        }
    }
}