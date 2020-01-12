package game.gui;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import game.Jogo;

public class Janela extends JFrame {

	private static final long serialVersionUID = -2896409662357437255L;
	
	private Jogo jogo;
	
	public Janela(Jogo jogo, String titulo, int width, int height) {
		super(titulo);
		this.jogo = jogo;
		setResizable(false);
		getContentPane().setPreferredSize(new Dimension(width, height));
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		addWindowListener(new Listener());
	}
	
	class Listener extends WindowAdapter {

		@Override
		public void windowClosing(WindowEvent e) {
			jogo.close();
		}
		
	}
	
}
