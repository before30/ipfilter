package com.skplanet.ipfilter;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by before30 on 2014. 5. 7..
 */
public class IpFilterTest3 extends TestCase {

	IpFilter ipFilter;

	@Before
	public void setUp() {
		ipFilter = new IpFilter();
		assertTrue(ipFilter.add("127.0.0.1"));
		assertTrue(ipFilter.add("127.0.0.2"));
		assertTrue(ipFilter.add("127.0.0.4"));
		assertTrue(ipFilter.add("127.1.1.*"));
		assertTrue(ipFilter.add("127.1.2.*"));
		assertTrue(ipFilter.add("127.1.3.1"));
		assertTrue(ipFilter.add("10.*.1.*"));
		assertTrue(ipFilter.add("10.*.2.*"));
	}

	@Test
	public void testContinas() throws Exception {
		assertTrue(ipFilter.contains("127.0.0.1"));
		assertTrue(ipFilter.contains("127.0.0.2"));
		assertTrue(ipFilter.contains("127.0.0.4"));
		assertTrue(ipFilter.contains("127.1.1.1"));
		assertTrue(ipFilter.contains("127.1.1.2"));
		assertTrue(ipFilter.contains("127.1.1.100"));
		assertTrue(ipFilter.contains("127.1.2.101"));
		assertTrue(ipFilter.contains("127.1.2.254"));
		assertTrue(ipFilter.contains("127.1.3.1"));

		assertFalse(ipFilter.contains("127.0.0.3"));
		assertFalse(ipFilter.contains("127.1.3.2"));
		assertFalse(ipFilter.contains("127.1.3.3"));

		assertTrue(ipFilter.contains("10.1.1.1"));
		assertTrue(ipFilter.contains("10.1.2.1"));
		assertFalse(ipFilter.contains("10.1.3.1"));

		assertTrue(ipFilter.containsWithForLoop("127.0.0.1"));
		assertTrue(ipFilter.containsWithForLoop("127.0.0.2"));
		assertTrue(ipFilter.containsWithForLoop("127.0.0.4"));
		assertTrue(ipFilter.containsWithForLoop("127.1.1.1"));
		assertTrue(ipFilter.containsWithForLoop("127.1.1.2"));
		assertTrue(ipFilter.containsWithForLoop("127.1.1.100"));
		assertTrue(ipFilter.containsWithForLoop("127.1.2.101"));
		assertTrue(ipFilter.containsWithForLoop("127.1.2.254"));
		assertTrue(ipFilter.containsWithForLoop("127.1.3.1"));

		assertFalse(ipFilter.containsWithForLoop("127.0.0.3"));
		assertFalse(ipFilter.containsWithForLoop("127.1.3.2"));
		assertFalse(ipFilter.containsWithForLoop("127.1.3.3"));

		assertTrue(ipFilter.containsWithForLoop("10.1.1.1"));
		assertTrue(ipFilter.containsWithForLoop("10.1.2.1"));
		assertFalse(ipFilter.containsWithForLoop("10.1.3.1"));

	}


}
