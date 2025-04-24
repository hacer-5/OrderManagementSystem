package main;

public class Main 
{

	public static void main(String[] args) 
	{
		OrderManagementSystem oms = new OrderManagementSystem();

		//Add order

		oms.addOrder(new String[]{"Apple", "Banana", "Cherry"});
        oms.addOrder(new String[]{"Apple", "Banana", "Pomegranate"});

		System.out.println("Tree Structure:");
        oms.printTree();

		  

		// Order cancellation

		oms.cancelOrder(new String[]{"Apple", "Banana", "Cherry"});
        System.out.println("Updated Tree Structure:");
        oms.printTree();

		 // Product set inquiry
         int totalOrders = oms.queryProductSet(new String[]{"Apple", "Banana"});
         System.out.println(" Apple and Banana together " + totalOrders + " times ordered.");

		}


	}


