package main;

public class TreeNode 
{

	String productName;
   int orderCount;
   TreeNode left, right, parent;

    TreeNode(String productName) 
    {
      this.productName = productName;
      this.orderCount = 1;
      this.left = null;
      this.right = null;
      this.parent = null;
    }

}
