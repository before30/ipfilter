package com.skplanet.ipfilter;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class IpFilterTest extends TestCase {
	IpFilter ipFilter;

	@Before
	public void setUp() throws Exception {
		ipFilter = new IpFilter();

		ipFilter.add("127.0.0.1");
		ipFilter.add("127.0.0.2");
		ipFilter.add("127.1.0.1");
		ipFilter.add("127.0.1.1");
		ipFilter.add("10.0.0.1");
		ipFilter.add("192.0.0.*");
		ipFilter.add("172.19.*.*");
		ipFilter.add("168.*.*.1");

	}

	@Test
	public void testAddIp() throws Exception {
		assertTrue(ipFilter.add("1.1.1.1"));
		assertFalse(ipFilter.add(null));
		assertFalse(ipFilter.add(""));
		assertFalse(ipFilter.add("1.1.1"));
	}


	@Test
	public void testSuccess() throws Exception {

		assertTrue(ipFilter.contains("127.0.0.1"));
		assertTrue(ipFilter.contains("10.0.0.1"));
		assertTrue(ipFilter.contains("192.0.0.1"));
		assertTrue(ipFilter.contains("172.19.0.1"));
		assertTrue(ipFilter.contains("168.100.100.1"));


		assertTrue(ipFilter.containsWithForLoop("127.0.0.1"));
		assertTrue(ipFilter.containsWithForLoop("10.0.0.1"));
		assertTrue(ipFilter.containsWithForLoop("192.0.0.1"));
		assertTrue(ipFilter.containsWithForLoop("172.19.0.1"));
		assertTrue(ipFilter.containsWithForLoop("168.100.100.1"));

	}

	@Test
	public void testFail() throws Exception {


		assertFalse(ipFilter.contains("127.1.1.2"));
		assertFalse(ipFilter.contains(""));
		assertFalse(ipFilter.contains("172.1"));
		assertFalse(ipFilter.contains("168.100.100.2"));

		assertFalse(ipFilter.containsWithForLoop("127.1.1.2"));
		assertFalse(ipFilter.containsWithForLoop(""));
		assertFalse(ipFilter.containsWithForLoop("172.1"));
		assertFalse(ipFilter.containsWithForLoop("168.100.100.2"));
	}


	@Test
	public void testPrintAll() throws Exception {
		ipFilter.printAll();
	}
} // end of test class
