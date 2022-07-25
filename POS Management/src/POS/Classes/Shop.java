package POS.Classes;

import java.io.*;
import java.util.*;

public class Shop extends Authentication{
	public String file = "../Data/shops.txt";

	public String id;
	public String name;
	public String address;
	public String username;

	public void logIn(String username, String password){
		int linenumber;
		String line;

		try{
			BufferedReader br = new BufferedReader(new FileReader(file));
			FileWriter writer = new FileWriter(file, true);
			String shop = "";
			linenumber = 0;
			String[] shopArray = new String[10];

			while ((line = br.readLine()) != null) {
				shopArray = line.split(",");
				if(linenumber == 0){
					linenumber++;
					continue;
				}
				if(shopArray[3].equals(username) && shopArray[4].equals(password)){
					shop = line;
					break;
				}
				linenumber++;
			}

			if(shop != "") {
				this.id = shopArray[0];
				this.name = shopArray[1];
				this.address = shopArray[2];
				this.username = shopArray[3];
			}
		}catch(IOException io){
			System.out.println(io);
		}	
	}
	
	public void Registration(String name, String address, String username, String password){
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

					if(lineArray[3].equals(username)){
						System.out.println("Username already exists!!!");
						return;
					}
				}
				i++;
			}

			int nextId = 1;
			if(lastLine != "") {
				lineArray = lastLine.split(",");
				nextId = Integer.parseInt(lineArray[0]) + 1;				
			}

			String newLine = nextId + "," + name+","+address+","+username+","+password+"\n";
			writer.write(newLine);

			this.id = Integer.toString(nextId);
			this.name = name;
			this.address = address;
			this.username = username;

			System.out.println("Shop created");
			writer.close();
		}catch(IOException io){
			System.out.println("IOException occured");
		}	
	}
}