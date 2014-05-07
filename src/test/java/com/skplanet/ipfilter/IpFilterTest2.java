package com.skplanet.ipfilter;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by before30 on 2014. 5. 7..
 */
//
public class IpFilterTest2 extends TestCase{
	IpFilter ipFilter;
	@Before
	public void setUp(){
		ipFilter = new IpFilter();
		ipFilter.add("*.*.*.*");
	}

	@Test
	public void testIpContains() throws Exception {
		assertTrue(ipFilter.contains("1.1.1.1"));
		assertTrue(ipFilter.contains("1.2.3.4"));
		assertTrue(ipFilter.contains("10.10.10.10"));
		assertTrue(ipFilter.contains("127.1.1.1"));

	}
} // end of test class
