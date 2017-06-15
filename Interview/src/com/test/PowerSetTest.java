/**
 * 
 */
package com.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import org.junit.Test;

import com.epi.primitives.PowerSet;

/**
 * @author sriee
 *
 */
public class PowerSetTest {

	@Test(expected=NullPointerException.class)
	public void shouldAvoidNull() {
		PowerSet set = new PowerSet(null);
		set.powerSet();
	}

	@Test(expected=NullPointerException.class)
	public void shouldAvoidEmptyString() {
		PowerSet set = new PowerSet("");
		set.powerSet();
	}
	
	@Test
	public void tokenTest() {
		PowerSet set = new PowerSet("ab3d6");
		assertEquals(set.powerSet().size(), 32);
		
		set.setInput("abcd");
		assertEquals(set.powerSet().size(), 16);
	}
	
	public boolean powerSetComparator(LinkedList<String> actual, LinkedList<String> inp){
		Set<String> actualSet = new HashSet<>();
		Set<String> inpSet = new HashSet<>();
		
		actualSet.addAll(actual);
		inpSet.addAll(inp);
		
		inpSet.removeAll(actualSet);
		
		return inpSet.isEmpty();
	}
	
	@Test
	public void elementChecker(){
		PowerSet set = new PowerSet("ab8cd3");
		LinkedList<String> inp = new LinkedList<>();

		inp.add(null);
		inp.add("a");
		inp.add("ab");
		inp.add("ab");
		inp.add("8");
		inp.add("a8");
		inp.add("b8");
		inp.add("ab8");
		inp.add("c");
		inp.add("ac");
		inp.add("bc");
		inp.add("abc");
		inp.add("8c");
		inp.add("a8c");
		inp.add("b8c");
		inp.add("ab8c");
		inp.add("d");
		inp.add("ad");
		inp.add("bd");
		inp.add("abd");
		inp.add("8d");
		inp.add("a8d");
		inp.add("b8d");
		inp.add("ab8d");
		inp.add("cd");
		inp.add("acd");
		inp.add("bcd");
		inp.add("abcd");
		inp.add("8cd");
		inp.add("a8cd");
		inp.add("b8cd");
		inp.add("ab8cd");
		inp.add("3");
		inp.add("a3");
		inp.add("b3");
		inp.add("ab3");
		inp.add("83");
		inp.add("a83");
		inp.add("b83");
		inp.add("ab83");
		inp.add("c3");
		inp.add("ac3");
		inp.add("bc3");
		inp.add("abc3");
		inp.add("8c3");
		inp.add("a8c3");
		inp.add("b8c3");
		inp.add("ab8c3");
		inp.add("d3");
		inp.add("ad3");
		inp.add("bd3");
		inp.add("abd3");
		inp.add("8d3");
		inp.add("a8d3");
		inp.add("b8d3");
		inp.add("ab8d3");
		inp.add("cd3");
		inp.add("acd3");
		inp.add("bcd3");
		inp.add("abcd3");
		inp.add("8cd3");
		inp.add("a8cd3");
		inp.add("b8cd3");
		inp.add("ab8cd3");
		
		assertTrue(powerSetComparator(set.powerSet(), inp));
	}
}
