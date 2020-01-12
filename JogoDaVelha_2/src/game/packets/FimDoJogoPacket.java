package game.packets;

import java.io.Serializable;

public class FimDoJogoPacket implements Serializable {

	private static final long serialVersionUID = -7916664625520303179L;
	
	private int ganhador;

	public FimDoJogoPacket(int ganhador) {
		this.ganhador = ganhador;
	}

	public int getGanhador() {
		return ganhador;
	}
	
	
}
