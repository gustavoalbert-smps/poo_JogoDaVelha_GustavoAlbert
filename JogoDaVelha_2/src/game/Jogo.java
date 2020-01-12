package game;

import javax.swing.JOptionPane;

import game.gui.Janela;
import game.gui.JogoJanela;

public abstract class Jogo {
	
	public static final int PORT = 55582;
	
	public static final int WIDTH = 600, HEIGHT = 600;
	public static final int FIELD_WIDTH =  WIDTH /3, FIELD_HEIGHT = HEIGHT /3;
	
	public static final int LIVRE = 0, JOGADOR_1 = 1, JOGADOR_2 = 2;
	
	protected int[][] fields;
	
	private Janela janela;
	protected JogoJanela jogoJanela;
	
	protected int jogadorAtual;
	
	protected int esseJogador;
	
	public Jogo(int esseJogador) {
		this.esseJogador = esseJogador;
		janela = new Janela(this, "Jogo da Velha", WIDTH, HEIGHT);
		jogoJanela = new JogoJanela(this);
		fields = new int[3][3];
		janela.add(jogoJanela);
		janela.setVisible(true);
		jogadorAtual = Jogo.JOGADOR_1;
	}
	
	protected void mostraGanhador(int ganhador) {
		if (ganhador == Jogo.LIVRE) {
			JOptionPane.showMessageDialog(null, "Deu Velha!");
		}else {
			JOptionPane.showMessageDialog(null, "O jogador " + ganhador +" venceu!");
		}
	}
	
	protected boolean minhaVez() {
		if (esseJogador == jogadorAtual) {
			return true;
		}
		return false;
	}
	
	public abstract void inputReceived(int x, int y);
	public abstract void packetReceiveid(Object object);
	
	public abstract void close();
	
	public int[][] getFields() {
		return fields;
	}
}
