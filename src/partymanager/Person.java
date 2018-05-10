package partymanager;
import java.io.Serializable;
import java.security.SecureRandom;
import java.util.ArrayList;
public class Person implements Serializable{
	private String name;
        private String pincode;
	private ArrayList<String> wishlist;
	private ArrayList<String> blacklistP;
        private ArrayList<String> groups;
	private Boolean role;
        private Boolean ischosen;
        private String chosen;
        
    public Person(String name, Boolean role) {
        this.name = name;
        this.role = role;
        generatePin();
        wishlist = new ArrayList<>();
        blacklistP = new ArrayList<>();
        groups = new ArrayList<>();
        chosen = "";
        ischosen = false;
    }
    
    private void generatePin() {
        SecureRandom random = new SecureRandom();
        int num = random.nextInt(10000);
        System.out.println("Random pincode for " + name + ": " + num);
        pincode = String.format("%04d", num);
    }
    
    public boolean checkPin(String pin) {
        boolean b = false;
        if (this.pincode.equals(pin))
            b = true;
        return b;
    }
    
    public void changePin(String pincode) {
        this.pincode = pincode;
        System.out.println("Pincode for " + name + " changed to: " + pincode);
    }

    public void setGroups(ArrayList<String> groups) {
        this.groups = groups;
    }

    public ArrayList<String> getGroups() {
        return groups;
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
    
    public void AddBlacklistName(String name)
    {
        blacklistP.add(name);
    }
    public void AddBlackListBulk(ArrayList<String> names)
    {
        for(int i=0;i<names.size();i++)
        {
            if(!blacklistP.contains(names.get(i)))
            {
                blacklistP.add(names.get(i));
            }
        }
    }
    public void RemoveBlacklistName(String name)
    {
        int index = blacklistP.indexOf(name);
        blacklistP.remove(index);
    }

    public void setWhishlist(ArrayList<String> whishlist) {
        this.wishlist = whishlist;
    }

    public void setBlacklistP(ArrayList<String> blacklistP) {
        this.blacklistP = blacklistP;
    }
    
    public boolean onBlacklistP(String name) {
        boolean b = false;
        for(String n: blacklistP) {
            if (n.equals(name))
                b = true;
        }
        return b;
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
        
    public void resetChosen() {
        chosen = "";
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
