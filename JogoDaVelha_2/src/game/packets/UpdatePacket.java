package game.packets;

import java.io.Serializable;

public class UpdatePacket implements Serializable {

	private static final long serialVersionUID = -4043686984608719773L;
	
	private int[][] fields;
	
	private int jogadorAtual;

	public UpdatePacket(int[][] fields, int jogadorAtual) {
		this.fields = fields;
		this.jogadorAtual = jogadorAtual;
	}

	public int[][] getFields() {
		return fields;
	}

	public int getJogadorAtual() {
		return jogadorAtual;
	}
	
	
	
}
