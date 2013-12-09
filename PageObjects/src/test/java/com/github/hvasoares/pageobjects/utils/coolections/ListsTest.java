package com.github.hvasoares.pageobjects.utils.coolections;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ListsTest {

	@Test
	public void testReverse() {
		List<Integer> result = Lists.revert(Arrays.asList(
				0,
				1,
				2,
				3
		));
		
		assertTrue(result.get(0)==3);
		assertTrue(result.get(1)==2);
		assertTrue(result.get(2)==1);
		assertTrue(result.get(3)==0);
	}
	
	public void testMultiply(){
		List<Integer> result = Arrays.asList(
				1,
				2,
				3,
				4
		);
		
		assertEquals(Lists.multiply(result, 4,3,2,1),1*4+2*3+3*2+4*1);
	}
	
	public void testConcat(){
		List<Integer> tail = Arrays.asList(
				1,
				2,
				3
		);
		List<Integer> result = Lists.concat(0, tail);
		
		assertTrue(result.get(0)==0);
		assertTrue(result.get(1)==1);
		assertTrue(result.get(2)==2);
		assertTrue(result.get(3)==3);
	}

}
