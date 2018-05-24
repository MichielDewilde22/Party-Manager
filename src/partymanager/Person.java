package partymanager;
import java.io.Serializable;
import java.security.SecureRandom;
import java.util.ArrayList;
/**
 * This class defines the attributes of a person who is part of the party
 * @author Andreas Durt, Michiel Dewilde
 */
public class Person implements Serializable{
	private String name;
        private String pincode;
	private ArrayList<String> wishlist;
	private ArrayList<String> blacklistP;
        private ArrayList<String> groups;
	private Boolean role;
        private Boolean ischosen;
        private Boolean pinChanged;
        private String chosen;
    /**
     * Creates a new person with a name and a role
     * @param name name of the person
     * @param role role of the person true = admine flase = normal partymember
     */    
    public Person(String name, Boolean role) {
        this.name = name;
        this.role = role;
        generatePin();
        wishlist = new ArrayList<>();
        blacklistP = new ArrayList<>();
        groups = new ArrayList<>();
        chosen = "";
        ischosen = false;
        pinChanged = false;
    }
    /**
     * Generates a random pin consisting of four numbers between 0000 and 9999
     */
    private void generatePin() {
        SecureRandom random = new SecureRandom();
        int num = random.nextInt(10000);
        pincode = String.format("%04d", num);
        System.out.println("Random pincode for " + name + ": " + pincode);
    }
    /**
     * Checks if the given pincode is the correct one
     * @param pin given pincode for the person
     * @return true = correct pin, false = wrong pin
     */
    public boolean checkPin(String pin) {
        boolean b = false;
        if (this.pincode.equals(pin))
            b = true;
        return b;
    }
    /**
     * Changes the pincode of a person
     * @param pincode the new pincode of the person
     */
    public void changePin(String pincode) {
        this.pincode = pincode;
        System.out.println("Pincode for " + name + " changed to: " + pincode);
        pinChanged = true;
    }
    /**
     * 
     * @param groups set a new grouparray 
     */
    public void setGroups(ArrayList<String> groups) {
        this.groups = groups;
    }
    /**
     * 
     * @return return the grouparray of the person
     */
    public ArrayList<String> getGroups() {
        return groups;
    }
    /**
     * Add a item to a persons wishlits
     * @param item the item that needs to be added to the wishlist
     */
    public void AddWhishlistItem(String item)
    {
        wishlist.add(item);
    }
    /**
     * Remove an item for the wishlist
     * @param item that needs to be removed
     */
    public void RemoveWhishlistItem(String item)
    {
        int index = wishlist.indexOf(item);
        wishlist.remove(index);
    }
    /**
     * 
     * @param name Set a name for the person
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Add a name to the persons blacklist
     * @param name name that needs to be added to the blacklist
     */
    public void AddBlacklistName(String name)
    {
        blacklistP.add(name);
    }
    /**
     * Add a list of names to a blacklist, names that already ar e on the blacklist will be ignored
     * @param names list of names that need to be added to the blacklist 
     */
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
    /**
     * Remove a name from the blacklist
     * @param name name that need to be removed
     */
    public void RemoveBlacklistName(String name)
    {
        int index = blacklistP.indexOf(name);
        blacklistP.remove(index);
    }
    /**
     * 
     * @param whishlist set a whischlist for the person
     */
    public void setWhishlist(ArrayList<String> whishlist) {
        this.wishlist = whishlist;
    }
    /**
     * 
     * @param blacklistP set a blacklist to a person
     */
    public void setBlacklistP(ArrayList<String> blacklistP) {
        this.blacklistP = blacklistP;
    }
    /**
     * 
     * @param blacklistP add a list of names to the blacklist, double names are allowed
     */
    public void addBlacklistP(ArrayList<String> blacklistP) {
        this.blacklistP.addAll(blacklistP);
    }
    /**
     * Check if a name is on the blacklist
     * @param name name of a person
     * @return true if the name is ont the blacklist otherwise false
     */
    public boolean onBlacklistP(String name) {
        boolean b = false;
        for(String n: blacklistP) {
            if (n.equals(name))
                b = true;
        }
        return b;
    }
    /**
     * 
     * @param role set the role of a person
     */
    public void setRole(Boolean role) {
        this.role = role;
    }
    /**
     * 
     * @return retrieve the name of the person
     */
    public String getName() {
        return name;
    }
    /**
     * 
     * @return retrieve the role of the person
     */
    public boolean getRole() {
        return role;
    }
    /**
     * 
     * @return retrieve the wishlist of the person
     */
    public ArrayList<String> getWhishlist() {
        return wishlist;
    }
    /**
     * 
     * @return retrieve the blacklist of the person
     */
    public ArrayList<String> getBlacklistP() {
        return blacklistP;
    }
    /**
     * Set the name of the person that the person has to buy a present for
     * @param name name of the chosen person 
     */
    public void setChosen(String name) {
        this.chosen=name;
    }
    /**
     * 
     * @return Retrieve the name of the person for who they need to buy a present
     */
    public String getChosen() {
        return chosen;
    }
    /**
     * Clear the name of the chosen person
     */
    public void resetChosen() {
        chosen = "";
    }
    /**
     * Looks if the person already has got a person who he need to buy a present for
     * @return true if the person already has a chosen name otherwise false
     */
    public boolean hasChosen() {
        if (!chosen.equals("")&& chosen !=null)
            return true;
        else 
            return false;
    }
    /**
     * Checks if the person already has been chosen by another person
     * @return true if the person has been chosen, otherwise false
     */
    public Boolean isChosen() {
        return ischosen;
    }
    /**
     * Set if the person has been chosen or not
     * @param ischosen true if the person is chosen otherwise false 
     */
    public void setIschosen(Boolean ischosen) {
        this.ischosen = ischosen;
    }
    /**
     * check if the pincode has changed after  the creation of the object
     * @return true if it has changed, otherwise false
     */
    public boolean getPinChanged() {
        return pinChanged;
    }
    /**
     * Set if the person has changed his pincode
     * @param b true if the pincode is being change, otherwise false
     */
    public void setPinChanged(boolean b) {
        this.pinChanged = b;
    }
    /**
     * 
     * @return the pincode of a person
     */
    public String getPin() {
        return pincode;
    }
            
}
