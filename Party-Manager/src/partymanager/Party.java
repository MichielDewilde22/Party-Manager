package partymanager;
import java.util.Scanner;
public class Party {
	private String date,place,hour,admin,currency;
	private int minPrice,maxPrice;
	Scanner scan = new Scanner(System.in);
	
	public Party(String admin) {
		this.admin = admin;
		date 		= "unknown";
		place 		= "unknown";
		hour 		= "unknown";
		currency 	= "unknown";
		minPrice	= 0;
		maxPrice	= 0;
	}
	public void setDate()
	{
		System.out.print("Enter the date when the party is being held: ");
		date = scan.nextLine();
	}
	public void setPlace()
	{
		System.out.print("Enter the adress where the party is being held: ");
		place = scan.nextLine();
	}
	public void setHour()
	{
		System.out.print("Enter the starting hour of the party: ");
		hour = scan.nextLine();
	}
	public void setPrice() 
	{
		System.out.print("Enter the currency that will be used: ");
		currency = scan.nextLine();
		System.out.print("Enter the minimum amount of money, the present should cost: ");
		minPrice = scan.nextInt();
		System.out.print("Enter the minimum amount of money, the present should cost: ");
		maxPrice = scan.nextInt();
	}
	public String getDate()
	{
		return date;
	}
	public String getPlace()
	{
		return place;
	}
	public String getHour()
	{
		return hour;
	}
	public String getCurrency()
	{
		return currency;
	}
	public String getAdmin()
	{
		return admin;
	}
	public int getMinPrice()
	{
		return minPrice;
	}
	public int getMaxPrice()
	{
		return maxPrice;
	}
}
