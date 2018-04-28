package partymanager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Action {
    
    public Action()
    {}
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
    
}
