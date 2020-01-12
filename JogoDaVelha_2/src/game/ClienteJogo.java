package game;

import java.io.IOException;
import java.net.Socket;

import game.net.Conexao;
import game.packets.ClientePlayPacket;
import game.packets.FimDoJogoPacket;
import game.packets.UpdatePacket;

public class ClienteJogo extends Jogo {

	private Socket socket;
	
	private Conexao conexao;
	
	public ClienteJogo() {
		super(Jogo.JOGADOR_2);
		
		try {
			socket = new Socket("localhost", Jogo.PORT);
			conexao = new Conexao(this, socket);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void inputReceived(int x, int y) {
		if (minhaVez()) {
			conexao.sendPacket(new ClientePlayPacket(x, y));
		}
	}
	
	@Override
	public void packetReceiveid(Object object) {
		
		if (object instanceof UpdatePacket) {
			UpdatePacket packet = (UpdatePacket) object;
			
			fields = packet.getFields();
			jogadorAtual = packet.getJogadorAtual();
		} else if (object instanceof FimDoJogoPacket) {
			FimDoJogoPacket packet = (FimDoJogoPacket) object;
			mostraGanhador(packet.getGanhador());
		}
		
		jogoJanela.repaint();
	}
	
	@Override
	public void close() {
		try {
			conexao.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
