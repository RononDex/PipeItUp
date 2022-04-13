package ch.fhnw.ip12.pipeitup.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ch.fhnw.ip12.pipeitup.ui.views.gameboard.software.Vector2;

/**
* Vector2Test
*/
public class Vector2Test {

	private static final double ALLOWED_ERROR_MARGIN = 0.0000001d;

	@Test
	void equals_WithUnequalVectors_ReturnsFalse() {
		Vector2 testee = new Vector2();

		boolean actual = testee.equals(new Vector2(0.0, 0.1));
		boolean actual2 = testee.equals(new Vector2(0.1, 0.0));

		assertFalse(actual);
		assertFalse(actual2);
	}

	@Test
	void equals_WithEqualVectors_ReturnsTrue() {
		Vector2 testee = new Vector2();

		boolean actual = testee.equals(new Vector2(0.0, 0.0));

		assertTrue(actual);
	}

	@Test
	void distance_WithTwoNonEqualVectors_ReturnsDistance() {
		Vector2 testee = new Vector2(1.0, 1.0);

		double actual = testee.distance(new Vector2(2.0, 3.0));

		assertEquals(Math.sqrt(5), actual);
	}

	@Test
	void distance_WithTwoEqualVectors_Returns0() {
		Vector2 testee = new Vector2(1.0, 1.0);

		double actual = testee.distance(new Vector2(1.0, 1.0));

		assertEquals(0, actual);
	}

	@Test
	void add_WithTwoVectors_ReturnsNewVector() {
		Vector2 testee = new Vector2(1.0, 1.0);

		Vector2 actual = testee.add(new Vector2(2.0, 1.5));

		assertTrue(new Vector2(3.0, 2.5).equals(actual));
	}

	@Test
	void subtract_WithTwoVectors_ReturnsNewVector() {
		Vector2 testee = new Vector2(1.0, 1.0);

		Vector2 actual = testee.subtract(new Vector2(2.0, 1.5));

		assertTrue(new Vector2(-1.0, -0.5).equals(actual));
	}

	@Test
	void multiply_WithTwoVectors_ReturnsNewVector() {
		Vector2 testee = new Vector2(1.0, 1.0);

		Vector2 actual = testee.multiply(2.0);

		assertTrue(new Vector2(2.0, 2.0).equals(actual));
	}

	@Test
	void divide_WithTwoVectors_ReturnsNewVector() {
		Vector2 testee = new Vector2(1.0, 1.0);

		Vector2 actual = testee.divide(2.0);

		assertTrue(new Vector2(0.5, 0.5).equals(actual));
	}

	@Test
	void length_WithVectorThatHasPositiveAndNegativeComponents_ReturnsLength() {
		Vector2 testee = new Vector2(-2.0, 1.0);

		double actual = testee.length();

		assertEquals(Math.sqrt(5), actual);
	}

	@Test
	void normalize_WithVectorThatHasPositiveAndNegativeComponents_ReturnsNewVectorWithLengthOfOne() {
		Vector2 testee = new Vector2(-2.0, 2.0);

		Vector2 actual = testee.normalize();
		
		assertEquals(actual.x, testee.x / Math.sqrt(8));
		assertEquals(actual.y, testee.y / Math.sqrt(8));
	}

	@Test
	void dot_WithTwoVectors_ReturnsDotProductOfTheTwoVectors() {
		Vector2 vector1 = new Vector2(1.0, 2.0);
		Vector2 vector2 = new Vector2(2.0, 3.0);

		double actual = Vector2.dot(vector1, vector2);
		
		assertEquals(8, actual);
	}

	@Test
	void rotate_WithRotationOf180Degrees_ReturnsFlippedVector() {
		Vector2 testee = new Vector2(1.0, 2.0);

		Vector2 actual = testee.rotate(Math.PI);
		
		assertEquals(actual.x, -1.0, ALLOWED_ERROR_MARGIN);
		assertEquals(actual.y, -2.0, ALLOWED_ERROR_MARGIN);
	}
}
