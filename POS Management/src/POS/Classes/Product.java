package POS.Classes;

import java.io.*;
import java.util.*;
import POS.Interfaces.ProductInterface;

public class Product implements ProductInterface{
	String file = "../Data/products.txt";

	public double price;
	public String id, code, name;
	public int stock;
	public int linenumber;
	public String line;

	public void addProduct(String code, String name, double price, int stock, String shopId){
		String line = "";
		String lastLine = "";

		try{
			BufferedReader br = new BufferedReader(new FileReader(file));
			FileWriter writer = new FileWriter(file, true);

			int i = 1;
			String[] lineArray = new String[10];
			while ((line = br.readLine()) != null) {
				if(i != 1){
					lastLine = line;

					lineArray = line.split(",");

					if(lineArray[1].equals(code)){
						System.out.println("Product code already exists!!!");
						return;
					}
				}
				i++;
			}

			int nextId = 1;
			if(lastLine != ""){
				String[] lastLineArray = lastLine.split(",");
				nextId = Integer.parseInt(lastLineArray[0]) + 1;				
			}

			String newLine = nextId + "," + code +","+ name +","+ price +","+ stock + "," + shopId +"\n";
			writer.write(newLine);
			System.out.println("Sucessfully Added Product!");

			writer.close();
		}catch(IOException io){
			System.out.println("IOException occured");
		}
	}

	public void showAllProduct(String shopId){
		String line = "";
		try{
			BufferedReader br = new BufferedReader(new FileReader(file));

			int i = 1;
			System.out.println("\n\n\n-----------------------------------------------");
			System.out.println(" ID, Code, Name, Price, Stock, Shop ID");
			System.out.println("-----------------------------------------------");

			String product[] = new String [10];
			while ((line = br.readLine()) != null) {
				if(i != 1){
					product = line.split(",");
					if(product[5].equals(shopId))
						System.out.println(line);
				}
				i++;
			}
			System.out.println("-----------------------------------------------\n\n\n");

		}catch(IOException io){
			System.out.println("IOException occured");
		}
	}

	public void searchProduct(String code, int quantity, String shopId){
		this.id = null;

		try{
			BufferedReader br = new BufferedReader(new FileReader(file));
			FileWriter writer = new FileWriter(file, true);
			
			String product = "";
			linenumber = 0;
			String[] productArray = new String[10];
			
			while ((line = br.readLine()) != null) {
				productArray = line.split(",");
				if(linenumber == 0){
					linenumber++;
					continue;
				}
				if(productArray[1].equals(code) && productArray[5].equals(shopId)){
					product = line;
					break;
				}
				linenumber++;
			}

			if(product != "") {
				this.id = productArray[0];
				this.code = productArray[1];
				this.name = productArray[2];
				this.price = Double.parseDouble(productArray[3]);
				this.stock = Integer.parseInt(productArray[4]);
			}
		}catch(IOException io){
			System.out.println(io);
		}
	}
}