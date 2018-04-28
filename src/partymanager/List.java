package partymanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class List {
    private final HashMap<String,Person> Attendees;
    public List()
    {
      Attendees = new HashMap<>();
    }
    
     public void addPartyMember(Person person)
    {
        Attendees.put(person.getName(),person);
    }
     public Person getAttendee(String name)
     {
         Person p;
         if(Attendees.containsKey(name))
            p = Attendees.get(name);
         else
             p = null;
         return p;
     }
     public void deletepartyMember(String name)
     {
         Person p = Attendees.get(name);
         Attendees.remove(name, p);
     }
     public void clear()
     {
         Attendees.clear();
     }
     public boolean contains(String name)
     {
         boolean exists = Attendees.containsKey(name);
         return exists;
     }

    public HashMap<String, Person> getAttendees() {
        return Attendees;
    }
    public ArrayList<String> getNames()
    {
        ArrayList<String> names = new ArrayList<>();
        for(String name : Attendees.keySet())
        {
            names.add(name);
        }
        return names;
    }
     public void setAttendees(HashMap<String,Person> X)
     {
         
         for(String name : X.keySet())
         {
             Attendees.put(name, X.get(name));
         }
     }

}
