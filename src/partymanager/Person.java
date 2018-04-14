package partymanager;
import java.util.ArrayList;
public class Person {
	private String name;
	private ArrayList<String> wishlist;
	private ArrayList<String> blacklistP;
	private Boolean role;
	
	public Person(String name, Boolean role) {
		this.name = name;
		this.role = role;
		wishlist = new ArrayList<String>();
		blacklistP = new ArrayList<String>();
		
	}
    public void AddWhishlistItem(String item)
    {
        wishlist.add(item);
    }
    
    public void RemoveWhishlistItem(String item)
    {
        int index = wishlist.indexOf(item);
        wishlist.remove(index);
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setWhishlist(ArrayList<String> whishlist) {
        this.wishlist = whishlist;
    }

    public void setBlacklistP(ArrayList<String> blacklistP) {
        this.blacklistP = blacklistP;
    }

    public void setRole(Boolean role) {
        this.role = role;
    }
    
    public String getName() {
        return name;
    }

    public ArrayList<String> getWhishlist() {
        return wishlist;
    }

    public ArrayList<String> getBlacklistP() {
        return blacklistP;
    }
    
    
}
