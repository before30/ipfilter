package com.skplanet.ipfilter3;

import java.util.HashSet;

/**
 * Created by before30 on 2014. 5. 7..
 */
public class IpFilter {

	HashSet<String> ipSet = new HashSet<String>();

	public boolean add(String ipAddress){
		return ipSet.add(ipAddress);
	}

	public boolean contains(String ipAddress){
		return ipSet.contains(ipAddress);
	}
} // end of class
