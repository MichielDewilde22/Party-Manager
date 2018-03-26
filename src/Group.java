package partymanager;

import java.util.ArrayList;

public class Group {
	private String name;
	private ArrayList<Person> list;
	private Blacklist blacklist;

	public void Group(String name) {
		this.name = name;
		list = new ArrayList<Person>();
		blacklist = new Blacklist(name);
	}
	
	public String getName() {
		String nameG = name;
		return nameG;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void AddMember(Person p) {
		list.add(p);
		System.out.println(p.getName() + "Has been added to the group "+name+".");
	}
	
	public void RemoveMember(Person p) {
		list.remove(p);
		System.out.println(p.getName() + "Has been removed from the group "+name+".");
	}
	
	public void AddToBlacklist(Group group) {
		blacklist.addEntry(group);
		System.out.println(group.getName() + "Has been added to the blacklist of group "+name+".");
	}
	
	public void RemoveFromBlacklist(Group group) {
		blacklist.removeEntry(group);
		System.out.println(group.getName() + "Has been removed from the blacklist of group "+name+".");
	}
}
