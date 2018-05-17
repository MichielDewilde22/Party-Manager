package partymanager;

import java.util.ArrayList;

public class Group {
private String name;
private ArrayList<String> members = new ArrayList<>();	

    public Group(String name)
    {
        this.name = name;
    }
    public void addMember(String name)
    {
        members.add(name);
    }
    public boolean removeMember(String name)
    {
        if(members.contains(name))
        {
            members.remove(name);
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public String getName() {
        return name;
    }

    public ArrayList<String> getMembers() {
        return members;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMembers(ArrayList<String> members) {
        this.members = members;
    }

    public void groupToBlacklistP() {
        ArrayList<String> temp = members;
        for (int i=0; i<members.size(); i++) {
            for(String n : temp)
            {
                if(!PartyManager.party.getAttendee(members.get(i)).getName().equalsIgnoreCase(n))
                {
                    if(!PartyManager.party.getAttendee(members.get(i)).getBlacklistP().contains(n))
                        PartyManager.party.getAttendee(members.get(i)).AddBlacklistName(n);
                }
            }
            
        }
    }
    
    public boolean containsMember(String name)
    {
        if(members.contains(name))
            return true;
        else
            return false;
    }
}
