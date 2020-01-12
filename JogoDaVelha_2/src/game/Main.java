package game;

import javax.swing.JOptionPane;

public class Main {
	public static void main(String[] args) {
		int choice = Integer.parseInt(JOptionPane.showInputDialog("1 para servidor | 2 para cliente"));
	
		if (choice == 1) {
			new ServerJogo();
		}else if (choice == 2) {
			new ClienteJogo();
		}
	}
}
