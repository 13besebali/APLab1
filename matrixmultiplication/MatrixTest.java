package matrix;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class MatrixTest {

	@BeforeClass
	public static void beforeClass() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testiteration() {
		int[][] expected = {{16,19},{36,43}};
		
		int[][] aTemp = {{1,2},{3,4}};
		Matrix A = new Matrix(aTemp);
		
		int[][] bTemp = {{4,5},{6,7}};
		Matrix B = new Matrix(bTemp);
		
		Assert.assertArrayEquals(expected,A.iteration(B).data);
	}

	@Test
	public final void testStrassen() {
int[][] expected = {{16,19},{36,43}};
		
		int[][] aTemp = {{1,2},{3,4}};
		Matrix A = new Matrix(aTemp);
		
		int[][] bTemp = {{4,5},{6,7}};
		Matrix B = new Matrix(bTemp);
		
		Matrix C = new Matrix(2,2);
		Assert.assertArrayEquals(expected,C.Strassen(A.data,B.data).data);
	}

}

