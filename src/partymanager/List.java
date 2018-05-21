package partymanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
/**
 * This class creates a hashmap of Persons with their name as keys
 * @author Andreas Durt,Michiel Dewilde
 */
public class List {
    private final HashMap<String,Person> Attendees;
    /**
     * creates a new hasmap &lt;String,Person&gt;
     */
    public List()
    {
      Attendees = new HashMap<>();
    }
    /**
     * Adds a person to the hashmap.
     * @param person add a person to the hasmap with his name as the key.
     */
     public void addPartyMember(Person person)
    {
        Attendees.put(person.getName(),person);
    }
     /**
      * Retrieves a person from the hashmap with a given name.
      * @param name Name of the needed person.
      * @return Person object corresponding to the name.
      *         Null if the perosn does not exist.
      */
     public Person getAttendee(String name)
     {
         Person p;
         if(Attendees.containsKey(name))
            p = Attendees.get(name);
         else
             p = null;
         return p;
     }
     /**
      * Delete a person from the party.
      * @param name Name of the person that needs to be deleted.
      */
     public void deletepartyMember(String name)
     {
         Person p = Attendees.get(name);
         Attendees.remove(name, p);
     }
     /**
      * Clear the hashmap.
      */
     public void clear()
     {
         Attendees.clear();
     }
     /**
      * Check if a person is part of the group
      * @param name name of the person that is being checked.
      * @return True if hasmap contains the name as a key, otherwise false
      */
     public boolean contains(String name)
     {
         boolean exists = Attendees.containsKey(name);
         return exists;
     }
     /**
      * Returns a Hashmap&lt;String,Person&gt;  
      * @return Hashmap&lt;String,Person&gt; 
      */
    public HashMap<String, Person> getAttendees() {
        return Attendees;
    }
    /**
     * Retrieves an ArrayList consisting of the keys from the hashmap
     * @return an ArrayList&lt;String&gt; with the keys of the hashmap
     */
    public ArrayList<String> getNames()
    {
        ArrayList<String> names = new ArrayList<>();
        for(String name : Attendees.keySet())
        {
            names.add(name);
        }
        return names;
    }
    /**
     * Sets the Hashmap of the List object
     * @param X Hashmap&lt;String,Person&gt; that will be set as the new hashmap
     */
     public void setAttendees(HashMap<String,Person> X)
     {
         
         for(String name : X.keySet())
         {
             Attendees.put(name, X.get(name));
         }
     }

}
