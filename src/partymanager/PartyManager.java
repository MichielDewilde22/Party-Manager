package partymanager;

import java.util.ArrayList;
import java.util.HashMap;

public class PartyManager {
    public static List party = new List();
    public static Party partyDetails = new Party();
    public static ArrayList<Group> groups = new ArrayList<>();
    
	public static void main(String args[])
        {
            chooseParty choose = new chooseParty();
            choose.setVisible(true);
        }
   
	
}
