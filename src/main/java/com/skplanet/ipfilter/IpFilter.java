package com.skplanet.ipfilter;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * Created by before30 on 2014. 5. 7..
 */
public class IpFilter {

	private Node root = new Node();

	public boolean add(String ipAddress){
		List<Integer> ipNumbers = splitIpAddress(ipAddress);
		if (ipNumbers.size() != 4){
			return false;
		}

		root.addNode(ipNumbers);
		return true;
	}

	public boolean contains(String ipAddress){
		List<Integer> ipNumbers = splitIpAddress(ipAddress);
		if (ipNumbers.size() != 4){
			return false;
		}

		return root.contains(ipNumbers);
	}

	public boolean containsWithForLoop(String ipAddress){
		List<Integer> ipNumbers = splitIpAddress(ipAddress);
		if (ipNumbers.size() != 4) {
			return false;
		}

		Node node = root;
		for (int ipNum : ipNumbers){
			Node tmpNode = node.getNodes(ipNum);
			if (tmpNode == null){
				tmpNode = node.getNodes(Node.STAR_VALUE);
				if (tmpNode == null){
					return false;
				}
			}

			node = tmpNode;
		}

		return true;
	}

	private List<Integer> splitIpAddress(String ipAddress){

		ArrayList<Integer> ipNumberList = new ArrayList<Integer>();

		if (StringUtils.isEmpty(ipAddress)){
			return ipNumberList;
		}

		String[] ipNumbers = StringUtils.split(ipAddress, "\\.");
		if (ipNumbers.length != 4){
			return ipNumberList;
		}


		for(String ipNumber : ipNumbers){
			if (StringUtils.isNumeric(ipNumber)){
				int ipNum = Integer.parseInt(ipNumber);
				if (ipNum < 0 || ipNum >= 255){
					break;
				}
				ipNumberList.add(ipNum);
			} else if (StringUtils.equals("*", ipNumber)){
				ipNumberList.add(Node.STAR_VALUE);
			} else {
				break;
			}
		}

		return ipNumberList;
	}

	public void printAll(){
		root.printAll();
	}

	///////////////////////////////////////////////////////////////////////////////
	public static class Node {
		private HashMap<Integer, Node> childNodeMap = new HashMap<Integer, Node>();
		private Node parentNode;
		private int value;
		private final int depth;


		public static final int STAR_VALUE = -1;

		public Node(){
			parentNode = null;
			this.depth = 0;
		}

		public Node(Node parentNode, int value, int depth){
			this.parentNode = parentNode;
			this.depth = depth;
			this.value = value;
		}

		public void addNode(List<Integer> ipNumbers){
			if (ipNumbers.size() <= depth)
				return;

			if (childNodeMap.containsKey(ipNumbers.get(depth))){
				// get old one
				Node node = childNodeMap.get(ipNumbers.get(depth));
				node.addNode(ipNumbers);
			} else {
				// new one
				Node node = new Node(this, ipNumbers.get(depth), depth+1);
				childNodeMap.put(ipNumbers.get(depth), node);
				node.addNode(ipNumbers);
			}
		}

		public boolean contains(List<Integer> ipNumbers){
			if (ipNumbers.size() <= depth){
				return true;
			}

			Node node = childNodeMap.get(ipNumbers.get(depth));
			if (node != null){
				return node.contains(ipNumbers);
			}

			node = childNodeMap.get(STAR_VALUE);
			if (node != null){
				return node.contains(ipNumbers);
			}

			return false;
		}

		public Node getNodes(int key){
			return childNodeMap.get(key);
		}

		public void printAll(){
			if (depth == 4){
				StringBuffer buffer = new StringBuffer();
				Node node = this;
				for (int i=0; i<4; i++){
					if (node.getValue() == -1){
						buffer.insert(0, "*.");
					} else {
						buffer.insert(0, node.getValue() + ".");
					}
					node = node.parentNode;
				}
				System.out.println(buffer.toString());
				return;
			}

			Iterator iterator = childNodeMap.entrySet().iterator();

			while(iterator.hasNext()){
				Map.Entry<String, Node> entry = (Map.Entry<String, Node>)iterator.next();
				entry.getValue().printAll();
			}
		}

		public int getValue(){
			return value;
		}

	} // end of inner class

} // end of class
