package partymanager;
import java.util.ArrayList;

public class Blacklist { 
private ArrayList<Person> BlacklistP;
private ArrayList<Group> BlacklistG;
//private Boolean BLkind;   				//true = person, false = group 
private String owner;
	public Blacklist(String name) {
		owner = name;
		BlacklistP = new ArrayList<Person>();
		BlacklistG = new ArrayList<Group>();
		//BLkind = true;
	}
	public void addEntryP(Person person) {
		
		BlacklistP.add(person);
		System.out.println(person.getName() + "Has been added to the person blacklist of "+owner+"."); 
		
	}
	public void removeEntryP(Person person)
	{
		int p = BlacklistP.indexOf(person);
		Person x = BlacklistP.get(p);
		BlacklistP.remove(p);
		System.out.println(x.getName() + "has been removed from the person blacklist of "+owner+".");
	}	
	public void addEntryG(Group group) {
		BlacklistG.add(group);
		System.out.println(group.getName() + "Has been added to the group blacklist of "+owner+".");
	}
	public void removeEntryG(Group group) {
		int g = BlacklistG.indexOf(group);
		Group y = BlacklistG.get(g);
		BlacklistG.remove(g);
		System.out.println(y.getName() + "Has been removed from the group blacklist of "+owner+".");
	}
	
}
