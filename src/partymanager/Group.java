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

 
}
