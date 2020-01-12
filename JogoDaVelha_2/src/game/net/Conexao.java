package game.net;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

import game.Jogo;

public class Conexao implements Runnable {
	
	private ObjectOutputStream outputStream;
	private ObjectInputStream inputStream;
	
	private boolean running;
	
	private Jogo jogo;
	
	public Conexao(Jogo jogo, Socket socket) {
		this.jogo = jogo;
		
		try {
			outputStream = new ObjectOutputStream(socket.getOutputStream());
			inputStream = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		new Thread(this).start();
	}
	
	@Override
	public void run() {
		running = true;
		
		while (running) {
			try {
				Object object = inputStream.readObject();
				jogo.packetReceiveid(object);
			}catch (EOFException | SocketException e) {
				running = false;
			}
			catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void sendPacket (Object object) {
		try {
			outputStream.reset();
			outputStream.writeObject(object);
			outputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void close() throws IOException {
		running = false;
		
		inputStream.close();
		outputStream.close();
	}

}
