package POS.Classes;

abstract class Authentication {
	public abstract void logIn(String username, String passward);
	public abstract void Registration(String name, String address, String username, String password);
}