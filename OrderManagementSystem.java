package main;

public class OrderManagementSystem 
{

   private TreeNode root;

    public OrderManagementSystem() 
    {
       root = null; 
    }

  
   //Adding a order
    
    public void addOrder(String[] products) 
    {
       java.util.Arrays.sort(products); 
            for (String product : products) 
            {
               root = addRecursive(root, product, null);
            }

     }

  
     private TreeNode addRecursive(TreeNode current, String product, TreeNode parent) 
     {
        if (current == null) 
        {
            TreeNode newNode = new TreeNode(product);
            newNode.parent = parent;
            return newNode;
        }

       if (product.equals(current.productName)) 
       {
            current.orderCount++;
       } 
       else if (product.compareTo(current.productName) < 0) 
       {
           current.left = addRecursive(current.left, product, current);
       } 
       else 
       {
           current.right = addRecursive(current.right, product, current);
       }

        return current;

    }

  
   //  Order cancellation
   public void cancelOrder(String[] products) 
   {
        for (String product : products) 
          {
            root = cancelRecursive(root, product);
          }

   }

  
   private TreeNode cancelRecursive(TreeNode current, String product) 
   {

       if (current == null) 
       {

         return null;
       }

      if (product.equals(current.productName)) 
      {
         current.orderCount--;
          if (current.orderCount == 0) 
          {
            return deleteNode(current);
          }
      } 
      else if (product.compareTo(current.productName) < 0) 
      {
          current.left = cancelRecursive(current.left, product);
      } 
      else 
      {
        current.right = cancelRecursive(current.right, product);
      }

      return current;

   }

  

     private TreeNode deleteNode(TreeNode node) 
     {

       if (node.left == null && node.right == null) 
       {
           return null;
       }
       if (node.left == null) 
       {
           return node.right;
       }
      if (node.right == null) 
      {
          return node.left;
      }

      TreeNode smallestValue = findSmallestValue(node.right);
      node.productName = smallestValue.productName;
      node.orderCount = smallestValue.orderCount;
      node.right = cancelRecursive(node.right, smallestValue.productName);

      return node;

   }

  

      private TreeNode findSmallestValue(TreeNode root) 
      {

          return root.left == null ? root : findSmallestValue(root.left);

      }

   // Product set inquriy
  
    public int queryProductSet(String[] products) 
    {
        int totalOrders = 0;
        java.util.Arrays.sort(products); 
        String lastProduct = products[products.length - 1];

  
        // Find the nodes containing the last products 
        java.util.List<TreeNode> nodes = new java.util.ArrayList<>();
        findNodes(root, lastProduct, nodes);
        
        // Check the root path and collect the orders
        for (TreeNode node : nodes) 
        {
             if (allProductsInPath(node, products)) 
             {
                totalOrders += node.orderCount;
              }
         }

         return totalOrders;

      }

  

       private void findNodes(TreeNode current, String product, java.util.List<TreeNode> nodes) 
       {

          if (current == null) 
          {
            return;
          }
         if (product.equals(current.productName)) 
         {
           nodes.add(current);

         }
         findNodes(current.left, product, nodes);
         findNodes(current.right, product, nodes);

       }

       private boolean allProductsInPath(TreeNode node, String[] products) 
       {
            java.util.Set<String> productSet = new java.util.HashSet<>(java.util.Arrays.asList(products));
          while (node != null) 
          {
             productSet.remove(node.productName);
             node = node.parent;
          }
          return productSet.isEmpty();
       }
     //Visualizing the tree
      
     public void printTree() 
     {
       printTreeRecursive(root, 0);
     }

     private void printTreeRecursive(TreeNode current, int depth) 
     {
        if (current != null) 
        {
            printTreeRecursive(current.left, depth + 1);
            System.out.println(" ".repeat(depth * 2) + current.productName + " (" + current.orderCount + ")");
            printTreeRecursive(current.right, depth + 1);
         }
     }
     
}   
