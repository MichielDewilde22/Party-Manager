package partymanager;

import javax.swing.JOptionPane;

public class Action { //TEST
	public void Save() {
		
	}
	
	public void Export(String path) {
		
	}
	
	public void Import(String path) {
		
	}
	
	public void DrawNames() {
		
	}
	
	public void SendPopUp(String message, String title) {
		JOptionPane.showMessageDialog(null, message, "InfoBox: " + title, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void Exit() {
		this.Save();
	}
}
