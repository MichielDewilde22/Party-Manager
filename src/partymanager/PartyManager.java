package partymanager;

import java.util.HashMap;

public class PartyManager {
    public static List list = new List();
    public static Party party = new Party();
    
	public static void main(String args[])
        {
            chooseParty choose = new chooseParty();
            choose.setVisible(true);
        }
   
	
}
