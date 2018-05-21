package partymanager;

import java.util.ArrayList;
/**
 * This class defines the group in which persons can be added.
 * @author Michiel Dewilde,Andreas Durt
 */
public class Group {
private String name;
private ArrayList<String> members = new ArrayList<>();	
    /**
     * Creates a group objec
     * @param name the name of the group
     */
    public Group(String name)
    {
        this.name = name;
    }
    /**
     * Add a memeber to the group
     * @param name name of the new member
     */
    public void addMember(String name)
    {
        members.add(name);
    }
    /**
     * Remove a member from the group
     * @param name name of the person that needs to be remove
     * @return true if member was in group, false if member
     *         was not part of the group
     */
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
    /**
     * Retrieve the name of the group
     * @return name of the group
     */
    public String getName() {
        return name;
    }
    /**
     * Retrieve a list of the names of the members
     * @return an ArrayList of the names of the members
     */
    public ArrayList<String> getMembers() {
        return members;
    }
    /**
     * give the group a new name
     * @param name new name of the group
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Set a new list of members
     * @param members ne ArrayList of members
     */
    public void setMembers(ArrayList<String> members) {
        this.members = members;
    }
    /**
     * Add members of the group to each others blacklist
     */
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
    /**
     * Checks if a name is part of the group
     * @param name name of member
     * @return true if the name is part of the group
     *         false if name is not part of the group 
     */
    public boolean containsMember(String name)
    {
        return members.contains(name);
    }
}
