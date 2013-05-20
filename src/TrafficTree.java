import java.util.ArrayList;

public class TrafficTree {
	private TreeNode root;
	private int size;
	
	public TrafficTree() {
		root = null;
		size = 0;
	}
	
	private void addNodeRec(TreeNode node, TreeNode insertNode) {
		if (node.compareTo(insertNode.address) == 0) {
			node.addTraffic(insertNode.traffic.remove(0));
			return;
		}
		//Go left on comparison
		else if (node.compareTo(insertNode.address) > 0) {
			if (node.left == null) {
				node.left = insertNode;
				size++;
				System.out.println(insertNode.address);
				return;
			}
			else {
				addNodeRec(node.left, insertNode);
			}
		}
		//Go right automatically
		else {
			if (node.right == null) {
				node.right = insertNode;
				size++;
				System.out.println(insertNode.address);
				return;
			}
			else {
				addNodeRec(node.right, insertNode);
			}
		}
	}
	
	public void addNode(String data) {
		TreeNode newNode = new TreeNode(data);
		if (root == null) {
			root = newNode;
			System.out.println(newNode.address);
			size++;
		}
		else {
			addNodeRec(root, newNode);
		}
	}
	
	private void printIPsHelper(TreeNode node) {
		if (node == null) {
			return;
		}
		System.out.println(node.address);
		printIPsHelper(node.left);
		printIPsHelper(node);
		printIPsHelper(node.right);
	}
	
	public void printIPs(){
		printIPsHelper(root);
	}
	
	public int size() {
		return this.size;
	}
	
	class TreeNode implements Comparable<String>{
		private String address;
		private TreeNode right;
		private TreeNode left;
		private ArrayList<String[]> traffic;
		
		//Data is going to be split on creation
		public TreeNode(String data) {
			right = null;
			left = null;
			traffic = new ArrayList<String[]>();
			String[] newData = data.split(",");
			traffic.add(newData);
			address = newData[2];
		}
		
		public void addTraffic(String[] traffic) {
			this.traffic.add(traffic);
		}

		//Compares IP Adresses to see if they match or are greater
		@Override
		public int compareTo(String address2) {			
			
			//Clean the strings
			address = address.replace("\"", "");
			address2 = address2.replace("\"", "");
						
			String[] addr1 = address.split("\\.");
			String[] addr2 = address2.split("\\.");			
			
			int value1 = 0, value2 = 0;
			for (int i = 0; i < addr1.length; i++) {
				value1 += Integer.valueOf(addr1[i]);
				value2 += Integer.valueOf(addr2[i]);
			}
			
			return value1 - value2;
		}
	}
}