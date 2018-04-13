package partymanager;

public class Party {
	private String date,place,hour,admin;
	private int minPrice,maxPrice;
	
	public Party() {
			
	}

    public String getDate() {
        return date;
    }

    public String getPlace() {
        return place;
    }

    public String getHour() {
        return hour;
    }

    public String getAdmin() {
        return admin;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }
        
        
}
