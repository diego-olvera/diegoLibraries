package diegoLibraries.net.socket;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import diegoLibraries.typeConverter.FullConverter;


public class UDPReceiver {
	
	private int port;
	private byte[] buffer;
	private DatagramSocket socket;
	private DatagramPacket packet;
	
	public UDPReceiver(int port,int bufferSize) throws SocketException {
		buffer=new byte[bufferSize];
		getPort(port);
		socket=new DatagramSocket(getPort());
		packet=new DatagramPacket(buffer, buffer.length);
	}
	public UDPReceiver(int port) throws SocketException {
		this(port,1024);
	}
	public void close() {
		socket.close();
	}
	public int getPort() {
		return port;
	}

	public void getPort(int port) {
		this.port = port;
	}

	public byte[] receive() throws IOException{
		socket.receive(packet);
		byte bytes[]=new byte[packet.getLength()];
		for(int i=0,j=bytes.length;i<j;i++){
			bytes[i]=buffer[i];
		}
		return bytes;
	}
	public void receive(byte[] message) throws IOException{
		int i=0;
		for(byte b:receive()){
			message[i++]=b;
		}
	}
	public String receiveString() throws IOException{
		return new String(receive());
	}
	public static String receiveString(int port,int bufferSize) throws SocketException, IOException {
		UDPReceiver rec=new UDPReceiver(port, bufferSize);
		String s=rec.receiveString();
		rec.close();
		return s;
	}
	public static byte[] receive(int port,int bufferSize) throws SocketException, IOException {
		UDPReceiver rec=new UDPReceiver(port, bufferSize);
		byte[]bytes=rec.receive();
		rec.close();
		return bytes;
	}
	public static void receive(int port,byte[]message) throws SocketException, IOException {
		UDPReceiver rec=new UDPReceiver(port, message.length);
		rec.receive(message);
		rec.close();
	}
	public static void main(String[] args) {
		try {
			UDPReceiver receiver=new UDPReceiver(2001,1024);
			byte buffer[];
			while(true){
				System.out.println("Waiting for message...");
				buffer=receiver.receive();
				System.out.println(FullConverter.toInt(buffer,0));
				System.out.println(FullConverter.toInt(buffer,4));			
				System.out.println(FullConverter.toString(buffer,8));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
