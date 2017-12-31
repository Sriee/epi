package com.test.sq;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.epi.sq.NormalizePath;

public class TestPath {

	static NormalizePath norm = null;
	
	@Before
	public void setUp() throws Exception{
		norm = new NormalizePath();
	}
	
	@Test
	public void shouldReturnRoot() {
		assertEquals("/", norm.simplifyPath(""));
		assertEquals("/", norm.simplifyPath("/."));
		assertEquals("/", norm.simplifyPath("/.."));
		assertEquals("/", norm.simplifyPath("/home/../../.."));
		assertEquals("/", norm.simplifyPath("/home/../../../.."));
	}
	
	@Test
	public void correctnessTest(){
		assertEquals("/usr/lib/gcc", norm.simplifyPath("/usr/bin/../lib/gcc"));
		assertEquals("/c", norm.simplifyPath("/a/./b/../../c/"));
	}

}
