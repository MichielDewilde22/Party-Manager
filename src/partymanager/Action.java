package partymanager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import static java.util.Collections.shuffle;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * This class defines the global actions an admin can execute
 * including: saving,importing,exporting list and passwords and dividing the names
 * @author Andreas Durt, Michiel Dewilde
 */
public class Action {
    
    public static ArrayList<String> give;
    public static ArrayList<String> get;
    public Person giver;
    public Person getter;
    
    /**
     * Creates an action object with two empty arraylists
     */
    public Action()
    {
        give = new ArrayList<>();
        get = new ArrayList<>();
        
    }
    /*public boolean export(String path)
    {
        try {
            FileOutputStream fileout = new FileOutputStream(path);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Action.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    /**
     * This method saves the given hashmap to a savefile in the src directory
     * @param X the hashmap that will be saved to a the savefile
     */
    public void saveFile(HashMap X)
    {
        File file = new File("SaveFile.ser");
        try {
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(X);
            out.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Action.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Action.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    /**
     * Import a serialized file that contains a hashmap.
     * @param file the serialize file of the hashmap: key = String, Value = Person
     */
    public void ImportFile(File file)
    {
        PartyManager.party.clear();
        HashMap<String,Person> map = PartyManager.party.getAttendees();
        //File file = new File(filename);
        try {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            map = (HashMap<String,Person>) in.readObject();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Action.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Action.class.getName()).log(Level.SEVERE, null, ex);
        }
        PartyManager.party.setAttendees(map);
    }
    /**
     * Export the current hashmap to a chosen directory
     * @param Path the path to the directory where the file needs to be saved
     * @param X the hashmap tha will be exported
     */
    public void ExportFile(String Path,HashMap X)
    {
        File file = new File(Path);
         try {
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(X);
            out.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Action.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Action.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    /**
     * Export a list of users and passwords tot a .txt file in the src directory
     */
    public void exportPassword() 
    {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("sdrowssap.txt"));
            Set<String> keys = PartyManager.party.getAttendees().keySet();
            for(String x : keys)
            {
                //String s = x+": "+ PartyManager.party.getAttendee(x).getPin()+"\n";
                writer.write(x+": "+ PartyManager.party.getAttendee(x).getPin());
                writer.newLine();
            }
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(Action.class.getName()).log(Level.SEVERE, null, ex);
    }
        
    }
    /**
     * Divid names among the persons of the party randomly.
     * @param list the list of attendees
     * @return the divided list of attendees
     */
    public static List divide(List list) {
        List temporary = new List();
        temporary = list;
        boolean busy = true;
        Random rand = new Random();
        int count = 0;
        int i = 0;
        
        give = list.getNames();
        get = list.getNames();
        
        do {
            Person giver = temporary.getAttendee(give.get(i));
            Person getter = temporary.getAttendee(get.get(i));
            if (giver.getName().equals(getter.getName()) || (getter.getChosen().equals(giver.getName()) && !PartyManager.partyDetails.getEachOther()) || (giver.onBlacklistP(getter.getName()) && PartyManager.partyDetails.blacklistEnabled())) {
                shuffle(get);
                temporary = list;
                i=0;
            }
            else
            {
                giver.setChosen(getter.getName());
                getter.setIschosen(true);
                i++;
            }
            if (i>=give.size())
                busy = false;
            count++;
            if(count>10000000)
                break;
        } while (busy);
        
        if (!busy) {
            return temporary;
        }
        else {
            return null;
        }
    }
    
}
