package com.skplanet.ipfilter3;

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
	}

	@Test
	public void testContains() throws Exception {
		assertTrue(ipFilter.contains("127.0.0.1"));
		assertTrue(ipFilter.contains("10.0.0.1"));
	}
}
