
import java.util.Arrays;
import org.testng.annotations.*;
import org.testng.annotations.Test;
import static org.junit.Assert.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class MethodsTest {
	// =========================ArythmMeanFromKToLProvider=========================================
	@Test(dataProvider = "arythmMeanFromKToLProvider")
	public void arythmMeanFromKToLTest(double[] array, int n, int k, int l, double expected) {

		assertEquals(Methods.arythmMeanFromKToL(array, k, l), expected);

	}

	@DataProvider
	public Object[][] arythmMeanFromKToLProvider() {
		return new Object[][] { { new double[] { -1, 2, 3, 17, -8, 10, 9, 192, -32, 77, 56 }, 11, 2, 6, 49. },
				{ new double[] { -13, 19, 26, 0, -5, 4, 7, 9, -11 }, 9, 2, 5, 2.2 } };
	}

	// ========================= End of
	// ArythmMeanFromKToLProvider=========================================

	// ======================= Fibonacci ==================================
	@Test(dataProvider = "fibonacciProvider")
	public void fibonacciTest(int n, int[] expected) {

		assertArrayEquals(Methods.fibonacci(n), expected);
	}

	@DataProvider
	public Object[][] fibonacciProvider() {
		return new Object[][] {{1,new int[]{1}}, { 2, new int[] { 1, 1 } }, { 5, new int[] { 1, 1, 2, 3, 5 } },
				{ 12, new int[] { 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144 } } };
	}

	// =====================end of fibonacci ==============================
	// ======================MaxOf2================================
	@Test(dataProvider = "maxProvider")
	public void maxTest(int number1, int number2, int expected) {

		assertEquals(Methods.max(number1, number2), expected);

	}

	@DataProvider
	public Object[][] maxProvider() {

		return new Object[][] { { 2, 3, 3 }, { 3, -4, 3 }, { -5, 5, 5 }, { 3, 3, 3 } };
	}
	// =================End of MaxOf2====================================

	// ===================A<B<C ? ========================================
	@Test(dataProvider = "compareABCProvider")
	public void compareABCTest(int A, int B, int C, boolean expected) {

		assertEquals(Methods.compareABC(A, B, C), expected);
	}

	@DataProvider
	public Object[][] compareABCProvider() {

		return new Object[][] { { 2, 3, 4, true }, { 2, 3, 3, false }, { -1, 0, 1, true }, { 7, 6, 5, false },
				{ 3, 3, 3, false }, { -5, -5, -5, false } };
	}

	// ========================End of A<B<C ? =============================

	// ========================= Double factorial =================================

	@Test(dataProvider = "doubleFactorialProvider")
	public void doubleFactorialTest(double n, double expected) {
		assertEquals(Methods.doubleFactorial(n), expected);
	}

	@DataProvider
	public Object[][] doubleFactorialProvider() {
		return new Object[][] { { 5, 15 }, { 8, 384 }, { 7, 105 } };
	}

	@Test(dataProvider = "doubleFactProvider", expectedExceptions = IllegalArgumentException.class)
	public void doubleFactorialTest1(double n) {
		Methods.doubleFactorial(n);
	}

	@DataProvider
	public Object[][] doubleFactProvider() {
		return new Object[][] { { -1 }, { -2 } };
	}
	// ======================= End of Double factorial ==========================

	// ======================== SwapColoumnsWithMinAndMax =======================
	@Test(dataProvider = "swapColoumnsWithMinAndMaxProvider")

	public void swapColoumnsWithMinAndMaxTest(int[][] array, int[][] expected, int n, int m) {
		assertTrue(Arrays.deepEquals(Methods.swapColoumnsWithMinAndMax(array, n, m), expected));
	}

	@DataProvider
	public Object[][] swapColoumnsWithMinAndMaxProvider() {

		int[][] array1 = { { 1, 2 }, { 34, -1 } };
		int[][] expected1 = { { 2, 1 }, { -1, 34 } };
		int[][] array2 = { { 1, 2, -5, 9 }, { 32, 16, -40, 10 }, { -3, -8, 21, -11 } };

		int[][] expected2 = { { -5, 2, 1, 9 }, { -40, 16, 32, 10 }, { 21, -8, -3, -11 }

		};
		int[][] array3 = { { -3, 2 }, { 29, 4 }, { 7, 11 } };
		int[][] expected3 = { { -3, 2 }, { 29, 4 }, { 7, 11 } };
		return new Object[][] { { array1, expected1, 2, 2 }, { array2, expected2, 3, 4 }, { array3, expected3, 3, 2 } };
	}

	@Test(dataProvider = "swapProvider", expectedExceptions = IllegalArgumentException.class)
	public void swapTest1(int n, int m) {
		Methods.swapColoumnsWithMinAndMax(null, n, m);
	}

	@DataProvider
	public Object[][] swapProvider() {
		return new Object[][] { { -1, 0 }, { -2, 9 } };
	}

	// ===================== end of SwapColoumnsWithMinAndMax ===================

	// ===================== length measure =================================
	@Test(dataProvider = "toMetresProvider")
	public void toMetresTest(int lenNum, double len, double expected) {
		assertEquals(Methods.toMetres(lenNum, len), expected, 0.01);
	}

	@DataProvider
	public Object[][] toMetresProvider() {

		return new Object[][] { { 1, 1.5, 0.15 }, { 2, 7.589, 7589. }, { 3, 3.45, 3.45 }, { 4, 37., 0.037 },
				{ 5, -56., -0.56 } };

	}

	@Test(dataProvider = "toMetresProvider1", expectedExceptions = IllegalArgumentException.class)
	public void toMetersTest1(int lenNum) {
		Methods.toMetres(lenNum, lenNum);
	}

	@DataProvider
	public Object[][] toMetresProvider1() {
		return new Object[][] { { -1 }, { 0 } };
	}

	// ====================end of length measure ============================

	@DataProvider(name = "splitNumberDataProvider1")
	public Object[][] splitNumberDataProvider1() {

		return new Object[][] { { new ModDiv(1, 2) } };

	}

	@Test(dataProvider = "splitNumberDataProvider1")
	public void splitNumberTest1(ModDiv expected) {
		ModDiv result = new Methods().splitNumber(12);
		assertTrue(result.getFirst() == expected.getFirst() && result.getSecond() == expected.getSecond());

	}

	@DataProvider(name = "splitNumberDataProvider2")
	public Object[][] splitNumberDataProvider2() {

		return new Object[][] { { new ModDiv(5, 0) } };

	}

	@Test(dataProvider = "splitNumberDataProvider2")
	public void splitNumberTest2(ModDiv expected) {
		ModDiv result = new Methods().splitNumber(50);
		assertTrue(result.getFirst() == expected.getFirst() && result.getSecond() == expected.getSecond());

	}

	@DataProvider(name = "parSquareVolumeDataProvider1")
	public Object[][] parSquareVolumeDataProvider1() {

		return new Object[][] { { new Paralelepiped(22, 6) } };

	}

	@Test(dataProvider = "parSquareVolumeDataProvider1")
	public void parSquareVolumeTest1(Paralelepiped expected) {
		Paralelepiped result = new Methods().parSquareVolume(1, 2, 3);
		assertTrue(result.getSquare() == expected.getSquare() && result.getVolume() == expected.getVolume());

	}

	@DataProvider(name = "parSquareVolumeDataProvider2")
	public Object[][] parSquareVolumeDataProvider2() {

		return new Object[][] { { new Paralelepiped(214, 210) } };

	}

	@Test(dataProvider = "parSquareVolumeDataProvider2")
	public void parSquareVolumeTest2(Paralelepiped expected) {
		Paralelepiped result = new Methods().parSquareVolume(5, 6, 7);
		assertTrue(result.getSquare() == expected.getSquare() && result.getVolume() == expected.getVolume());

	}
}
