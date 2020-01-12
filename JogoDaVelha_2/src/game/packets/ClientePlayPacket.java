package game.packets;

import java.io.Serializable;

public class ClientePlayPacket implements Serializable {
	
	private static final long serialVersionUID = 878099152374442967L;
	
	private int x;
	private int y;
	public ClientePlayPacket(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
}
