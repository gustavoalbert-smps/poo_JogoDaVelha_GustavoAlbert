package game.gui;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import game.Jogo;
import game.res.Recursos;

public class JogoJanela extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6484898688325282155L;

	private Jogo jogo;
	
	public JogoJanela(Jogo jogo) {
		this.jogo = jogo;
		addMouseListener(new Input());
	}

	//renderizando
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		Graphics2D g2D = (Graphics2D) g;
		g2D.setStroke(new BasicStroke(10));
		
		for (int i = Jogo.FIELD_WIDTH; i <= Jogo.FIELD_WIDTH * 2; i += Jogo.FIELD_WIDTH) {
			g2D.drawLine(i, 0, i, Jogo.HEIGHT);
		}
		for (int j = Jogo.FIELD_HEIGHT; j <= Jogo.FIELD_HEIGHT * 2; j += Jogo.FIELD_HEIGHT) {
			g2D.drawLine(0, j, Jogo.WIDTH, j);
		}
		
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y <3; y++) {
				int field = jogo.getFields()[x][y];
				if (field != Jogo.LIVRE) {
					g2D.drawImage(Recursos.letters[field - 1], x * Jogo.FIELD_WIDTH, y * Jogo.FIELD_HEIGHT, Jogo.FIELD_WIDTH, Jogo.FIELD_HEIGHT, null);
				}
			}
		}
	}
	
	class Input extends MouseAdapter {

		@Override
		public void mousePressed(MouseEvent e) {
			
			if (e.getButton() == MouseEvent.BUTTON1) {
				jogo.inputReceived(e.getX()/Jogo.FIELD_WIDTH, e.getY()/Jogo.FIELD_HEIGHT);
			}
		}
		
	}
	
}
