import static org.junit.Assert.*;

import org.junit.Test;

public class SudokuVerifierTest {

//implement tests to test Sudokuverifier with boundary values.  Use templates from Task 1 to derive and document test cases.
	
// A correct Sudoku string: 417369825632158947958724316825437169791586432346912758289643571573291684164875293
// An incorrect Sudoku string: 123456789912345678891234567789123456678912345567891234456789123345678912234567891
String c = "417369825632158947958724316825437169791586432346912758289643571573291684164875293";
String i = "123456789912345678891234567789123456678912345567891234456789123345678912234567891";
SudokuVerifier v = new SudokuVerifier();

	@Test
	public void testCorrectString() {
		int a = v.verify(c);
		assertEquals("correct string", a, 0);
	}

	@Test
	public void testIncorrectString() {
		int a = v.verify(i);
		assertEquals("incorrect string", a, -2);
	}
	
	@Test
	public void testSudokuMainTest() {
		v.main(null);	
	}
	
	@Test
	public void testSudokuReturn0() {
		//Act
		int return0Check = v.verify(c);
		// Assert
		assertEquals(0, return0Check);		
	}
	
	@Test
	public void testSudokuNotCorrectLength() {
		//Arrange
		String notCorrectLength = "41736982563215894795872431682543716979158643";
		//Act
		int notCorrectLengthCheck = v.verify(notCorrectLength);
		// Assert
		assertEquals(-1, notCorrectLengthCheck);		
	}
	
	@Test
	public void testSudokuNotANumber() {
		//Arrange
		String notANumber = "41736982563215894795872431682543716979158643234691275828964357157329168416487529Ω";
		//Act
		int notANumberCheck = v.verify(notANumber);
		// Assert
		assertEquals(1, notANumberCheck);		
	}
	
	//Equivalence Classes: the 9 sub-grids, rows, columns
	
	//Sub-grid testing:
	
	@Test
	public void testSudokuRule1() {
		
		// - Boundaries: 0-1, 9-10
		
		//Lower boundary:
		
		//Arrange 
		String violatingRule1 = "017369825632158947958724316825437169791586432346912758289643571573291684164875293";
		//Act
		int rule1Check = v.verify(violatingRule1);
		// Assert
		assertEquals(-1, rule1Check);
		
		//Upper boundary:
		
		//cannot receive 10 in the string
	}
	
	@Test
	public void testSudokuRule2() {
		//Arrange
		String violatingRule2 = "517369825632158947958724316825437169791586432346912758289643571573291684164875293";
		//Act
		int rule2Check = v.verify(violatingRule2);
		// Assert
		assertEquals(-2, rule2Check);		
	}
	
	//Row testing: 
	
	@Test
	public void testSudokuRule3() {
		//Arrange
		String violatingRule3 = "617369825432158947958724316825437169791586432346912758289643571573291684164875293";
		//Act
		int rule3Check = v.verify(violatingRule3);
		// Assert
		assertEquals(-3, rule3Check);		
	}
	
	//Column testing:
	
	@Test
	public void testSudokuRule4() {
		//Arrange
		String violatingRule4 = "147369825632158947958724316825437169791586432346912758289643571573291684164875293";
		//Act
		int rule4Check = v.verify(violatingRule4);
		// Assert
		assertEquals(-4, rule4Check);		
	}
}
