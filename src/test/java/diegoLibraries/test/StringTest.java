package diegoLibraries.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


import static diegoLibraries.string.StringUtil.*;

public class StringTest {

	@Test
	public void testPasswords() {
		assertFalse(isValidPassword("123"));
		assertTrue(isValidPassword("mkyong1A@"));

	}
	@Test
	public void testCalendar() {
		assertFalse(isValidCalendar("2016"));
		assertTrue(isValidCalendar("2017b"));
		assertTrue(isValidCalendar("2017-2018"));
		assertFalse(isValidCalendar("2017-"));
	}
	@Test
	public void testFrequencies() {
		ArrayList<String> strings=new ArrayList<>();
		strings.add("a a a b c c");
		strings.add("b a a a c c");
		assertTrue(anagram(strings)); 
	}
	@Test
	public void testValidUsername() {
		assertFalse(isValidUsername("123Swakkhar"));
		assertFalse(isValidUsername("diego_3252_Sg@"));
		assertTrue(isValidUsername("diego25sghs34"));
	}
	@Test
	public void pruebaCamelCaseReplace() {
		String a="hello\r\n";
		String b="java";
		String compare="Hello\r\n"+" Java";
		assertEquals(compare,getCamelCaseStringWithSpaces(a+" "+b));
	}
	@Test
	public void pruebaRellenador() {
		ArrayList<String> rellenadores=new ArrayList<String>();
		String[] strings=getWords("He is a very very good boy, isn't he?",rellenadores);
		for(int i=0,j=strings.length;i<j;i++){
			System.out.println("["+strings[i]+rellenadores.get(i)+"]");
		}	
	}
	@Test
	public void pruebaSplitWords() {
		String cad;
		cad="           YES      leading spaces        are valid,    problemsetters are         evillllll\r\n" + 
				"";
		cad="                        ";
		ArrayList<String> words=splitWords(cad);
		System.out.println(words.size());
		for(String s:words) {
			System.out.println(s);
		}
	}
}
