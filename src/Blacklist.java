package partymanager;
import java.util.ArrayList;

public class Blacklist { 
private ArrayList<Object> Blacklist;
//private Boolean BLkind;   				//true = person, false = group 
private String owner;
	public Blacklist(String name) 
	{
		owner = name;
		Blacklist = new ArrayList<Object>();
	}
	public void addEntry(Object O) {
		
		
		if(O instanceof Person)
		{
			Person p = (Person)O;
			System.out.println(p.getName() + "Has been added to the blacklist of "+owner+".");
			Blacklist.add(O);
		}
		else if(O instanceof Group)
		{
			Group g = (Group)O;
			System.out.println(g.getName() + "Has been added to the  blacklist of the group: "+owner+".");
			Blacklist.add(O);
		}
		else
			System.out.println("error");
		
	}
	public void removeEntry(Object O)
	{
		int x = Blacklist.indexOf(O);
		if(O instanceof Person)
		{
			Person p = (Person)Blacklist.get(x);
			Blacklist.remove(x);
			System.out.println(p.getName() + "has been removed from the person blacklist of "+owner+".");
		}
		else if(O instanceof Group)
		{
			Group g = (Group)Blacklist.get(x);
			Blacklist.remove(x);
			System.out.println(g.getName() + "has been removed from the person blacklist of the group "+owner+".");
		}
		else
			System.out.println("error");
		
	}	
}
