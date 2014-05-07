package com.skplanet.ipfilter;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by before30 on 2014. 5. 7..
 */
public class IpFilter {

	private Node root = new Node();

	public boolean add(String ipAddress){
		if (StringUtils.isEmpty(ipAddress)){
			return false;
		}

		String[] ipNumbers = StringUtils.split(ipAddress, "\\.");
		if (ipNumbers.length != 4){
			return false;
		}

		root.addNode(ipNumbers);
		return true;
	}

	public boolean contains(String ipAddress){
		String[] ipNumbers = StringUtils.split(ipAddress, "\\.");
		if (ipNumbers.length != 4){
			return false;
		}

		return root.contains(ipNumbers);
	}

	public void printAll(){
		root.printAll();
	}

	public static class Node {
		private HashMap<String, Node> childeNodeMap = new HashMap<String, Node>();
		private Node parentNode;
		private String value;
		private final int depth;

		public Node(){
			parentNode = null;
			this.depth = 0;
		}

		public Node(Node parentNode, String value, int depth){
			this.parentNode = parentNode;
			this.depth = depth;
			this.value = value;
		}

		public void addNode(String[] ipNumbers){
			if (depth >= 4)
				return;

			if (childeNodeMap.containsKey(ipNumbers[depth])){
				Node node = childeNodeMap.get(ipNumbers[depth]);
				node.addNode(ipNumbers);
			} else {
				Node node = new Node(this, ipNumbers[depth], depth+1);
				childeNodeMap.put(ipNumbers[depth], node);
				node.addNode(ipNumbers);
			}
		}

		public boolean contains(String[] ipNumbers){
			if (depth >= 4){
				return true;
			}

			Node node = childeNodeMap.get(ipNumbers[depth]);
			if (node != null){
				return node.contains(ipNumbers);
			}

			node = childeNodeMap.get("*");
			if (node != null){
				return node.contains(ipNumbers);
			}

			return false;
		}

		public void printAll(){
			if (depth == 4){

				String first = this.parentNode.parentNode.parentNode.getValue();
				String second = this.parentNode.parentNode.getValue();
				String third = this.parentNode.getValue();
				String fourth = this.getValue();

				System.out.println(first +"." +second+"."+third+"."+fourth);
				return;
			}
			Iterator iterator = childeNodeMap.entrySet().iterator();

			while(iterator.hasNext()){
				Map.Entry<String, Node> entry = (Map.Entry<String, Node>)iterator.next();
				entry.getValue().printAll();
			}
		}

		public String getValue(){
			return value;
		}

	} // end of inner class

} // end of class
