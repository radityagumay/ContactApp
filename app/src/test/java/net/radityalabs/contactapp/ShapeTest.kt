package net.radityalabs.contactapp

import org.junit.Before
import org.junit.Test

import java.util.ArrayList

import junit.framework.Assert.assertEquals

/**
 * Created by radityagumay on 4/18/17.
 */

class ShapeTest {

    private var rectangle: Rectangle? = null
    private var circle: Circle? = null

    @Before
    fun setup() {
        rectangle = Rectangle()
        circle = Circle()
    }

    @Test
    fun circleTest() {
        circle!!.radius = 10.0

        val actual = circle!!.radius * circle!!.radius * Math.PI
        val expected = 314.1592653589793
        assertEquals(expected, actual)
    }

    @Test
    fun rectangleTest() {
        rectangle!!.length = 10.0
        rectangle!!.height = 5.0

        val actual = rectangle!!.height * rectangle!!.length
        val expected = 50.0
        assertEquals(expected, actual)
    }

    @Test
    fun calcAllRectangle() {
        val rects = ArrayList<Rectangle>(5)
        for (i in 0..4) {
            val rect = Rectangle()
            rect.length = RECTANGLE_LENGTH[i]
            rect.height = RECTANGLE_HEIGHT[i]
            rects.add(rect)
        }
        for (i in rects.indices) {
            val actual = rects[i].height * rects[i].length
            val expected = RECTANGLE_EXPECTED[i]
            assertEquals(expected, actual)
        }
    }

    @Test
    fun calcAllShape() {
        val shapes = ArrayList<Any>()
        for (i in 0..4) {
            val rect = Rectangle()
            rect.length = RECTANGLE_LENGTH[i]
            rect.height = RECTANGLE_HEIGHT[i]
            shapes.add(rect)
        }

        for (i in 0..4) {
            val circle = Circle()
            circle.radius = CIRCLE_RADIUS[i]
            shapes.add(circle)
        }

        for (i in shapes.indices) {
            if (shapes[i] is Rectangle) {
                val rect = shapes[i] as Rectangle
                val actual = rect.height * rect.length
                val expected = RECTANGLE_CIRCLE_EXPECTED[i]
                assertEquals(expected, actual)
            } else if (shapes[i] is Circle) {
                val circle = shapes[i] as Circle
                val actual = circle.radius * circle.radius * Math.PI
                val expected = RECTANGLE_CIRCLE_EXPECTED[i]
                assertEquals(expected, actual)
            }
        }
    }

    @Test
    fun calcAllShapeRefactor() {
        val shapes = ArrayList<Shape>()
        for (i in 0..4) {
            val rect = Rectangle()
            rect.length = RECTANGLE_LENGTH[i]
            rect.height = RECTANGLE_HEIGHT[i]
            shapes.add(rect)
        }

        for (i in 0..4) {
            val circle = Circle()
            circle.radius = CIRCLE_RADIUS[i]
            shapes.add(circle)
        }

        for (i in shapes.indices) {
            val actual = shapes[i].area
            val expected = shapes[i].getExpected(RECTANGLE_CIRCLE_EXPECTED[i])
            assertEquals(expected, actual)
        }
    }

    interface Shape {
        val area: Double

        fun getExpected(expected: Double): Double
    }

    interface Draw {
        fun draw(area: Double)
    }

    class Circle : Shape {
        var radius: Double = 0.toDouble()

        override val area: Double
            get() = radius * radius * Math.PI


        override fun getExpected(expected: Double): Double {
            return expected
        }
    }

    class Rectangle : Shape {
        var length: Double = 0.toDouble()
        var height: Double = 0.toDouble()

        override val area: Double
            get() = length * height

        override fun getExpected(expected: Double): Double {
            return expected
        }
    }

    companion object {

        private val RECTANGLE_LENGTH = doubleArrayOf(1.0, 2.0, 3.0, 4.0, 5.0)

        private val RECTANGLE_HEIGHT = doubleArrayOf(1.0, 2.0, 3.0, 4.0, 5.0)

        private val RECTANGLE_EXPECTED = doubleArrayOf(1.0, 4.0, 9.0, 16.0, 25.0)

        private val CIRCLE_RADIUS = doubleArrayOf(1.0, 2.0, 3.0, 4.0, 5.0)

        private val CIRCLE_EXPECTED = doubleArrayOf(3.141592653589793, 12.566370614359172, 28.274333882308138, 50.26548245743669, 78.53981633974483)

        private val RECTANGLE_CIRCLE_EXPECTED = doubleArrayOf(1.0, 4.0, 9.0, 16.0, 25.0, 3.141592653589793, 12.566370614359172, 28.274333882308138, 50.26548245743669, 78.53981633974483)
    }
}