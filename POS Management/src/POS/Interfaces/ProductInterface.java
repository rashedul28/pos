package POS.Interfaces;

public interface ProductInterface {
	public void showAllProduct(String shopId);
	public void addProduct(String code, String name, double price, int stock, String shopId);
	public void searchProduct(String code, int quantity, String shopId);
}