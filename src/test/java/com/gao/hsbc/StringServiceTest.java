/**
 * 
 */
package com.gao.hsbc;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 
 */
public class StringServiceTest {
	private final StringService stringService = new StringService();

	// test remove
	@Test
	void testRemoveWithNoConsecutiveChars() {
		assertEquals("abc", stringService.remove("abc"));
	}

	@Test
	void testRemoveWithConsecutiveChars() {
		assertEquals("d", stringService.remove("aabcccbbad"));
		assertEquals("d", stringService.remove("aaad"));
	}

	@Test
	void testRemoveWithEmptyString() {
		assertEquals("", stringService.remove(""));
	}

	// test replace
	@Test
	void testReplace() {
		assertEquals("d", stringService.replace("abcccbad"));
		assertEquals("d", stringService.replace("abbbad"));
	}

	@Test
	void testReplaceWithMinimumChar() {
		assertEquals("a", stringService.replace("bbba"));
	}

}
