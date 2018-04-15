package partymanager;

import java.util.HashMap;

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
     
     public boolean contains(String name)
     {
         boolean exists = Attendees.containsKey(name);
         return exists;
     }

}
