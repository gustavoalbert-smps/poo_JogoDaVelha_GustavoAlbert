package game;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import game.net.Conexao;
import game.packets.ClientePlayPacket;
import game.packets.FimDoJogoPacket;
import game.packets.UpdatePacket;

public class ServerJogo extends Jogo {
	
	private ServerSocket serverSocket;
	private Socket socket;
	
	private Conexao conexao;
	
	public ServerJogo() {
		super(Jogo.JOGADOR_1);
		
		try {
			serverSocket = new ServerSocket(Jogo.PORT);
			socket = serverSocket.accept();
			conexao = new Conexao(this, socket);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void inputReceived(int x, int y) {
		if (minhaVez()) {
			updateField(x, y);
		}
	}
	
	@Override
	public void packetReceiveid(Object object) {
		if (object instanceof ClientePlayPacket) {
			ClientePlayPacket packet = (ClientePlayPacket) object;
			
			updateField(packet.getX(), packet.getY());
		}
	}
	
	private void updateField(int x, int y) {
		
		if (fields[x][y] == Jogo.LIVRE) {
			fields[x][y] = jogadorAtual;
			if (jogadorAtual == Jogo.JOGADOR_1) {
				jogadorAtual = Jogo.JOGADOR_2;
			}else if (jogadorAtual == Jogo.JOGADOR_2) {
				jogadorAtual = Jogo.JOGADOR_1;
			}
			
			conexao.sendPacket(new UpdatePacket(fields, jogadorAtual));
			jogoJanela.repaint();
			
			int ganhador = checaVitoria();
			
			if (ganhador != Jogo.LIVRE) {
				fimDoJogo(ganhador);
			}else {
				int contaVazia = 0;
				
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						if (fields[i][j] == Jogo.LIVRE) {
							contaVazia++;
						}
					}
				}
				if (contaVazia == 9) {
					fimDoJogo(ganhador);
				}
			}
		}
	}
	
	private void fimDoJogo(int ganhador) {
		mostraGanhador(ganhador);
		conexao.sendPacket(new FimDoJogoPacket(ganhador));
	}
	
	private int checaVitoria() {
		for (int jogador = Jogo.JOGADOR_1; jogador <= Jogo.JOGADOR_2; jogador++) {
			for (int y = 0; y < 3; y++) {
				int contaJogador = 0;
				
				for (int x = 0; x < 3; x++) {
					if (fields[x][y] ==  jogador) {
						contaJogador++;
					}
				}
				if (contaJogador == 3) {
					return jogador;
				}
			}
			for (int x = 0; x < 3; x++) {
				int contaJogador = 0;
				
				for (int y = 0; y < 3; y++) {
					if (fields[x][y] == jogador) {
						contaJogador++;
					}
				}
				if (contaJogador == 3) {
					return jogador;
				}
			}
			int contaJogador = 0;
			for (int coordenada = 0; coordenada < 3; coordenada ++) {
				if (fields[coordenada][coordenada] == jogador) {
					contaJogador++;
				}
			}
			if (contaJogador == 3) {
				return jogador;
			}
			
			contaJogador = 0;
			
			for (int coordenada = 0; coordenada <3; coordenada++) {
				if (fields[2 - coordenada][coordenada] == jogador) {
					contaJogador++;
				}
			}
			
			if (contaJogador == 3) {
				return jogador;
			}
		}
		
		return Jogo.LIVRE;
	}
	
	@Override
	public void close() {
		try {
			conexao.close();
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
