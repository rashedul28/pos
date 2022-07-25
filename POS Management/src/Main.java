import java.io.*;
import java.util.*;
import POS.Classes.*;
import POS.Interfaces.*;

public class Main {
	public static Shop shop = new Shop();
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		while(true){
			System.out.println("Choices: 1. Login  2. Registration  3. Exit");
			System.out.print("Enter Choice: ");
			String choice = scanner.nextLine();

			switch(choice){
				case "1":
					System.out.print("Enter Username: ");
					String username = scanner.nextLine();
					System.out.print("Enter Password: ");
					String password = scanner.nextLine();

					shop.logIn(username, password);

					if(shop.id == null){
						System.out.println("Shop Not Found!");
					} else {
						enterPOS();
					}

					break;
				case "2":
					System.out.print("Enter Shop Name: ");
					String name = scanner.nextLine();
					System.out.print("Enter Shop Address: ");
					String address = scanner.nextLine();
					System.out.print("Enter Username: ");
					username = scanner.nextLine();
					System.out.print("Enter Password: ");
					password = scanner.nextLine();

					shop.Registration(name, address, username, password);
									
					if(shop.id != null){
						enterPOS();
					}		

					break;
				case "3":
					System.out.println("Goodbye!!!");
					System.exit(0);
					break;
				default:
					System.out.println("Choice doesn't match or choice required single Integer!!!");
					;
			}
		}
	}

	public static void enterPOS(){
		Scanner scanner = new Scanner(System.in);
		Product product = new Product();
		String c;

		while(true){
			System.out.println("                  " + shop.name);
			System.out.println("-----------------------------------------------");
			System.out.println("Menu: \n 1. Add Product     2. Sell");
			System.out.println(" 3. Product List    4. Exit");
			
			System.out.print("Enter Choice: ");
			String choice = scanner.nextLine();
			
			switch(choice){
				case "1":
					System.out.print("Product Code: ");
					String code = scanner.nextLine();
					System.out.print("Product Name: ");
					String name = scanner.nextLine();
					System.out.print("Product Price: ");
					double price = 0;
					int stock;

					try {
						price = scanner.nextDouble();
						c = scanner.nextLine();
					} catch (InputMismatchException e) {
						c = scanner.nextLine();
						System.out.println("Price can't be character!");
						break;
					}
					
					System.out.print("Stock: ");

					try {
						stock = scanner.nextInt();
						c = scanner.nextLine();
					} catch (InputMismatchException e) {
						c = scanner.nextLine();
						System.out.println("Stock can't be character!");
						break;
					}
							
					if(code == ""){
						System.out.println("Product code not found!!!");
					}

					product.addProduct(code, name, price, stock, shop.id);

				    break;
				case "2":
					Invoice invoice = new Invoice();
					boolean breakMe = false;

					while(true && !breakMe){
						System.out.println("                    Sell");
						System.out.println("-----------------------------------------------");
						System.out.println("1. Add product to invoice        2.Generate Invoice");
						System.out.println("3. Back");
						System.out.print("Enter choice: ");
						choice = scanner.nextLine();
						
						switch(choice){
							case "1":
								System.out.print("Enter product code: ");
								code = scanner.nextLine();
								System.out.print("Enter product quantity: ");

								int quantity;

								try {
									quantity = scanner.nextInt();
									c = scanner.nextLine();
								} catch (InputMismatchException e) {
									c = scanner.nextLine();
									System.out.println("Quantity can't be character!");
									break;
								}

								product.searchProduct(code, quantity, shop.id);

								if(product.id == null){
									System.out.println("Product not found!");
									continue;
								}
								if(product.stock < quantity){
									System.out.println("Out of stock!");
									continue;
								}

								// Add product to invoice
								invoice.addProduct(new InvoiceProduct(product, quantity));

								break;
							case "2":
								System.out.print("Customer mobile number: ");
								String customerMobile = scanner.nextLine();

								invoice.setCustomerMobile(customerMobile);
								invoice.generateInvoice(shop);

								break;
							case "3":
								invoice.clearInvoice();
								breakMe = true;
								break;
							default: 
								System.out.println("Invalid choice!!!");
						}
					}
					break;

				case "3":
					// Print product list
					product.showAllProduct(shop.id);
					break;
				case "4":
					System.out.println("Goodbye!!!");
					System.exit(0);
					break;
				default:
				System.out.println("Choice doesn't match or choice required single Integer!!!");
				;
			}
		}
	}
}