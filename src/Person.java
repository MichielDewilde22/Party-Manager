package partymanager;
import java.util.ArrayList;
public class Person {
	private String name;
	private ArrayList<String> whishlist;
	private ArrayList<String> blacklistP;
	private Boolean role;
	
	public Person(String name, Boolean role) {
		this.name = name;
		this.role = role;
		whishlist = new ArrayList<String>();
		blacklistP = new ArrayList<String>();
		
	}
	public String getName() {
		String nameP = name ;
		return nameP;
	}
}
