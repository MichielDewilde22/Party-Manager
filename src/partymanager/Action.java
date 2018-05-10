package partymanager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import static java.util.Collections.shuffle;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Action {
    
    public static ArrayList<String> give;
    public static ArrayList<String> get;
    public Person giver;
    public Person getter;
    
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
    
    public static List divide(List list) {
        List temporary = new List();
        temporary = list;
        boolean busy = true;
        Random rand = new Random();
        int count = 0;
        int i = 0;
        
        give = temporary.getNames();
        get = temporary.getNames();
        
        do {
            Person giver = temporary.getAttendee(give.get(i));
            Person getter = temporary.getAttendee(get.get(i));
            if (giver.getName().equals(getter.getName()) || getter.getChosen().equals(giver.getName()) || (giver.onBlacklistP(getter.getName()) && PartyManager.partyDetails.blacklistEnabled())) {
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
        } while (busy);
        
        System.out.println("Personen zijn verdeeld!");
        return temporary;
    }
    
}
