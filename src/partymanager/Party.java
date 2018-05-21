package partymanager;
/**
 * This class consists of the partydetails of the used party
 * @author Andreas Durt,Michiel Dewilde
 */
public class Party {
	private String date,place,hour,admin;
	private int minPrice,maxPrice;
        private boolean blacklistEnabled = true;
        private boolean eachother = false;
	/**
         * create an empty party
         */
	public Party() {
			
	}
    /**
     * 
     * @return String: date of the party
     */
    public String getDate() {
        return date;
    }
    /**
     * 
     * @return  String: place of the party
     */
    public String getPlace() {
        return place;
    }
    /**
     * 
     * @return String: the hour the party takse place
     */
    public String getHour() {
        return hour;
    }
    /**
     * Retrieves the name of the admin of the party from the PartyManager.party
     * @return String: name of the admin of the party 
     */
    public String getAdmin() {
        for (String name: PartyManager.party.getNames()) {
            if (PartyManager.party.getAttendee(name).getRole())
                admin = name;
        }
        return admin;
    }
    /**
     * 
     * @return minimu price of a present 
     */
    public int getMinPrice() {
        return minPrice;
    }
    /**
     * 
     * @return maximum price of a present
     */
    public int getMaxPrice() {
        return maxPrice;
    }
    /**
     * 
     * @param date Set the date of the party 
     */
    public void setDate(String date) {
        this.date = date;
    }
    /**
     * 
     * @param place Set the place of the party 
     */
    public void setPlace(String place) {
        this.place = place;
    }
    /**
     * 
     * @param hour set the hour the party takes place 
     */
    public void setHour(String hour) {
        this.hour = hour;
    }
    /**
     * 
     * @param admin Set the admin name
     */
    public void setAdmin(String admin) {
        this.admin = admin;
        System.out.println("Admin set to " + admin);
    }
    /**
     * 
     * @param minPrice set the minimum price 
     */
    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }
    /**
     * 
     * @param maxPrice set the maximum price
     */
    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }
    /**
     * 
     * @param b true enable blacklists for the party, false disable blacklists
     */  
    public void setBlacklistEnabled(boolean b) {
        this.blacklistEnabled = b;
    }
    /**
     * 
     * @param b true set if two people can draw each other, false not able.
     */
    public void setEachOther(boolean b) { 
        this.eachother = b; //If true, 2 persons can draw each other. If false, they cannot draw each other.
    }
    /**
     * 
     * @return True if the people can draw each other, otherwise false 
     */
    public boolean getEachOther() {
        return eachother;
    }
    /**
     * 
     * @return true if blacklists are enabeld otherwise false 
     */  
    public boolean blacklistEnabled() {
        return blacklistEnabled;
    }
        
}
