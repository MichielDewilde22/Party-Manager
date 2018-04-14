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

    public void setName(String name) {
        this.name = name;
    }

    public void setWhishlist(ArrayList<String> whishlist) {
        this.whishlist = whishlist;
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
        return whishlist;
    }

    public ArrayList<String> getBlacklistP() {
        return blacklistP;
    }
    
    
}
