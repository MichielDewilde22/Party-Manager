package partymanager;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Action {
    public boolean export(String path)
    {
        try {
            FileOutputStream fileout = new FileOutputStream(path);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Action.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
