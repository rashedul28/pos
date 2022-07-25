package POS.Classes;

import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class Invoice{
	InvoiceProduct[] products = new InvoiceProduct[100];

	public int numberOfProducts;
	public String customerMobile;

	public Invoice(){
		this.numberOfProducts = 0;
	}

	public void addProduct(InvoiceProduct product){
		this.products[this.numberOfProducts] = product;
		this.numberOfProducts++;
	}

	public void generateInvoice(Shop shop){
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm:ss");

		System.out.println("\n\n\n\n-----------------------------------------------");
		System.out.println("|                   INVOICE                   |");
		System.out.println( " " + shop.name);
		System.out.println( " " + shop.address);
		System.out.println(" "+ dateFormat.format(new Date()));
		System.out.println("-----------------------------------------------");
		System.out.println(" Customer Info:                                ");
		System.out.println(" Mobile Number: " + this.customerMobile);
		System.out.println("-----------------------------------------------");
		System.out.println("|Total Product: "+ this.numberOfProducts +"                             |");
		System.out.println("|                                             |");
		
		int totalAmount = 0;
		
		System.out.println("|Code - Name    -      Quantity    -     Price|");
		for(int i = 0; i < this.numberOfProducts; i++){
			System.out.println("|" + this.products[i].code + "   " + this.products[i].name + "   " + this.products[i].quantity + "   " + this.products[i].quantity * this.products[i].price);
			totalAmount += this.products[i].quantity * this.products[i].price;
		}
		System.out.println("-----------------------------------------------");
		System.out.println("|Total Amount:       "+ totalAmount);
		System.out.println("-----------------------------------------------\n\n");
		System.out.println("|                  THANK YOU!!!               |");
		System.out.println("-----------------------------------------------\n\n\n\n");

		this.clearInvoice();
	}

	public void clearInvoice(){
		this.products = new InvoiceProduct[100];
		this.numberOfProducts = 0;
		this.customerMobile = "";
	}

	public void setCustomerMobile(String number){
		this.customerMobile = number;
	}
}