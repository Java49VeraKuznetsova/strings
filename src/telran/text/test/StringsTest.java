package telran.text.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.text.Strings;

class StringsTest {

	@Test
	void test() {
	//String regex = "gray|grey|griy";
		//String regex = "gra|e|iy";
		/*
		String regex = "gr(a|e|i)y";
		assertTrue("gray".matches(regex));
		assertTrue("grey".matches(regex));
		assertTrue("griy".matches(regex));
		assertFalse("groy".matches(regex));
		*/
		/*
		String regex = "a?1234";
		assertTrue("1234".matches(regex));
		assertTrue("a1234".matches(regex));
		assertFalse("b1234".matches(regex));
		assertFalse("aa1234".matches(regex));
		*/
		/*
		String regex = "a*1234";
		assertTrue("1234".matches(regex));
		assertTrue("a1234".matches(regex));
		assertFalse("b1234".matches(regex));
		assertTrue("aa1234".matches(regex));
		*/
		/*
		String regex = "a+1234";
		assertFalse("1234".matches(regex));
		assertTrue("a1234".matches(regex));
		assertFalse("b1234".matches(regex));
		assertTrue("aa1234".matches(regex));
		*/
		
		String regex = "(a|b)+1234";
		assertFalse("1234".matches(regex));
		assertTrue("a1234".matches(regex));
		assertTrue("b1234".matches(regex));
		assertTrue("aa1234".matches(regex));
		
	}
	@Test
	void javaVariableNameTest() {
		String regex = Strings.javaVariableName();
		assertTrue("a".matches(regex));
		assertTrue("$".matches(regex));
		assertTrue("$2".matches(regex));
		assertTrue("$_".matches(regex));
		assertTrue("__".matches(regex));
		assertTrue("_2".matches(regex));
		assertTrue("a_b".matches(regex));
		assertTrue("A_B".matches(regex));
		assertTrue("abc1234567890000".matches(regex));
		assertFalse("1a".matches(regex));
		assertFalse("_".matches(regex));
		assertFalse("a#".matches(regex));
		assertFalse("a b".matches(regex));
		assertFalse("a-b".matches(regex));
		assertFalse(" ab".matches(regex));
	}
	@Test
	void zero_300Test() {
		String regex = Strings.zero_300();
		assertTrue("0".matches(regex));
		assertTrue("9".matches(regex));
		assertTrue("299".matches(regex));
		assertTrue("300".matches(regex));
		assertTrue("99".matches(regex));
		assertFalse("01".matches(regex));
		assertFalse("00".matches(regex));
		assertFalse("1111".matches(regex));
		assertFalse("301".matches(regex));
		assertFalse("3000".matches(regex));
		assertFalse("310".matches(regex));
		assertFalse("-1".matches(regex));
		assertFalse("3 ".matches(regex));
	}
	@Test
	void ipV4OctetTest () {
		String regex = Strings.ipV4Octet();
		assertTrue("000".matches(regex));
		assertTrue("00".matches(regex));
		assertTrue("0".matches(regex));
		assertTrue("99".matches(regex));
		assertTrue("1".matches(regex));
		assertTrue("10".matches(regex));
		assertTrue("199".matches(regex));
		assertTrue("200".matches(regex));
		assertTrue("249".matches(regex));
		assertTrue("250".matches(regex));
		assertTrue("255".matches(regex));
		assertFalse("0000".matches(regex));
		assertFalse(" 1".matches(regex));
		assertFalse(".0".matches(regex));
		assertFalse("-1".matches(regex));
		assertFalse("256".matches(regex));
		assertFalse("1000".matches(regex));
	}
	@Test
	void IpV4Test() {
		String regex = Strings.ipV4();
		assertTrue("0.0.0.0".matches(regex));
		assertTrue("1.1.1.1".matches(regex));
		assertTrue("99.99.12.09".matches(regex));
		assertTrue("100.199.200.255".matches(regex));
		assertFalse(".1.2.3.4".matches(regex));
		assertFalse("1.2.3.4.".matches(regex));
		assertFalse("1.2.3".matches(regex));
		assertFalse("1.2.3.4.5".matches(regex));
		assertFalse("1.2.&3.4.".matches(regex));
	}
	@Test
	void arithmeticExpressionTest() {
		assertTrue(Strings.isArithmeticExpression(" 12 "));//12
		assertTrue(Strings.isArithmeticExpression(" 12/ 6"));//2
		assertTrue(Strings.isArithmeticExpression("12/2"));//6
		assertTrue(Strings.isArithmeticExpression(" 12*  2 / 3 + 1000 "));//1008
		assertTrue(Strings.isArithmeticExpression(" 120 / 50 + 100 - 2 * 3 / 500 "));//0
		assertFalse(Strings.isArithmeticExpression(" 12 18"));
		assertFalse(Strings.isArithmeticExpression(" 12/3&4"));
		assertFalse(Strings.isArithmeticExpression(" 12+20-"));
		assertFalse(Strings.isArithmeticExpression(" 12/ 18 + 100 10"));
		
		assertTrue(Strings.isArithmeticExpression(" 12.0/ 18.12345 + .100 - 10. + 5.4"));
		assertTrue(Strings.isArithmeticExpression(" 150.5"));
		assertFalse(Strings.isArithmeticExpression(" .150.5"));
		assertFalse(Strings.isArithmeticExpression("+ 0.150.5"));
		
		assertTrue(Strings.isArithmeticExpression(" 150.5 + a_b "));
		assertTrue(Strings.isArithmeticExpression(" 18.12345 + $23 "));
		assertTrue(Strings.isArithmeticExpression(" 0.436 + a$23 "));
		assertTrue(Strings.isArithmeticExpression("a_b + 150.5"));
		assertTrue(Strings.isArithmeticExpression("a_b + 150.5"));
		assertFalse(Strings.isArithmeticExpression("a# / 150.5"));
		assertTrue(Strings.isArithmeticExpression(" 150.5 + lmn "));
	
		
		
	}
	@Test
	void computeExpressionTest() {
		assertEquals(12, Strings.computeExpression(" 12 "));
		assertEquals(2, Strings.computeExpression(" 12/ 6"));
		assertEquals(6, Strings.computeExpression("12/2"));
		assertEquals(1008, Strings.computeExpression(" 12*  2 / 3 + 1000 "));
		//assertEquals(0, Strings.computeExpression(" 120 / 50 + 100 - 2 * 3 / 500 "));
		assertEquals(1.5, Strings.computeExpression(" 120 / 60 + 100 - 2 * 3 / 200 "));
		assertEquals(11.6, Strings.computeExpression(" 150.5 / 5 + 29.9-2/5.0"));
	    assertEquals(4.8, Strings.computeExpression("150.5 / 5 + 29.9 / 12.5"));
		
		assertThrowsExactly(IllegalArgumentException.class,
				() -> Strings.computeExpression(" 12/ 18 + 100 10"));
	}
	
	
	@Test
	void computeExpressionTest2() {
		String[] keys = {"lmn", "abc", "ab", "a"};
		Double[] values = {3.0, 0.2, 2.25, 1.};
	
		
		 HashMap <String, Double> mapVariables = new HashMap();
		 for (int i = 0; i<keys.length; i++) {
				mapVariables.put(keys[i], values[i]);
				
			}
			assertThrowsExactly(NoSuchElementException.class,
					() -> Strings.computeExpression(" 150.5 + a_b ", mapVariables));
		
		assertEquals(153.5, Strings.computeExpression(" 150.5 + lmn ", mapVariables));
		assertEquals(153.5, Strings.computeExpression(" 150.5 + lmn ", mapVariables));
		assertEquals(20, Strings.computeExpression(" abc / 0.01 ", mapVariables));
		assertEquals(23.4375, Strings.computeExpression(" 150.5 + ab - 1.75 - a / 6.4  ", mapVariables));
		
	}
}
