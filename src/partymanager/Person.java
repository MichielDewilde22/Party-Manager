package partymanager;
import java.io.Serializable;
import java.util.ArrayList;
public class Person implements Serializable{
	private String name;
	private ArrayList<String> wishlist;
	private ArrayList<String> blacklistP;
	private Boolean role;
        private Boolean ischosen;
        private String chosen;
	public Person(String name, Boolean role) {
		this.name = name;
		this.role = role;
		wishlist = new ArrayList<>();
		blacklistP = new ArrayList<>();
                chosen = "";
                ischosen = false;
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
    
    public boolean getRole() {
        return role;
    }

    public ArrayList<String> getWhishlist() {
        return wishlist;
    }

    public ArrayList<String> getBlacklistP() {
        return blacklistP;
    }
    public void setChosen(String name) {
        this.chosen=name;
    }
    
    public String getChosen() {
        return chosen;
    }
    public boolean hasChosen() {
        if (!chosen.equals("")&& chosen !=null)
            return true;
        else 
            return false;
    }

    public Boolean isChosen() {
        return ischosen;
    }

    public void setIschosen(Boolean ischosen) {
        this.ischosen = ischosen;
    }
    
}
