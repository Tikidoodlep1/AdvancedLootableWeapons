package tiki.advancedlootableweapons.util;

import java.util.TreeMap;

import tiki.advancedlootableweapons.items.ItemHotToolHead;

public class HTHTree {

	private TreeMap<ItemHotToolHead, ItemHotToolHead[]> adjList = new TreeMap<ItemHotToolHead, ItemHotToolHead[]>();
	
	public HTHTree() {}
	
	public ItemHotToolHead[] getNode(ItemHotToolHead node) {
		return adjList.get(node);
	}
	
	public ItemHotToolHead getRoot() {
		return adjList.firstKey();
	}
	
	public void setNode(ItemHotToolHead node, ItemHotToolHead... children) {
		adjList.put(node, new ItemHotToolHead[children.length + 1]);
		
		ItemHotToolHead nodeSet[] = adjList.get(node);
		nodeSet[0] = node;
		for(int i = 1; i <= children.length; i++) {
			nodeSet[i] = children[i - 1];
		}
		
		if(nodeSet.length >= 3) { // Don't sort 1 child
			radixSortBase10(nodeSet, 1, nodeSet.length);
		}
	}
	
	//quick Radix Sort implementation to use for sorting the children of each node so we can BinarySearch among the children nodes to find the parents of
	//any given node in O(H*log n) time where H is the height of the tree.
	private void countSortBase10(ItemHotToolHead[] orig, int[] digits, int left, int right) {
		int[] counter = new int[10];
		ItemHotToolHead[] temp = new ItemHotToolHead[right - left];
		
		for(int i = 0; i < digits.length; i++) {
			counter[digits[i]]++;
		}
		for(int i = 1; i < counter.length; i++) {
			counter[i] += counter[i-1];
		}
		for(int i = right - 1; i >= left; i--) {
			counter[digits[i - left]]--;
			temp[counter[digits[i - left]]] = orig[i];
		}
		for(int i = left; i < right; i++) {
			orig[i] = temp[i - left];
		}
	}
	
	private void radixSortBase10(ItemHotToolHead[] children, int left, int right) {
		ItemHotToolHead max = children[left];
		for(int i = left; i < right; i++) {
			if(children[i].compareTo(max) > 0) {
				max = children[i];
			}
		}
		int[] digits = new int[right - left];
		int e = 1;
		while(max.type/e > 0) {
			for(int i = left; i < right; i++) {
				digits[i - left] = (children[i].type/e)%10;
			}
			countSortBase10(children, digits, left, right);
		}
	}
	
}
