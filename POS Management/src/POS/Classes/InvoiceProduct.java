package POS.Classes;

import java.io.*;

public class InvoiceProduct extends Product {
	public int quantity;

	public InvoiceProduct(Product product, int quantity){
		this.id = product.id;
		this.code = product.code;
		this.name = product.name;
		this.price = product.price;
		this.quantity = quantity;
	}
}