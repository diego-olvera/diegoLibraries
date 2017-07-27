package diegoLibraries.net.socket;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import diegoLibraries.typeConverter.FullConverter;



public class UDPSender {
	private String destAddr;
	private int port;
	private InetAddress inetAddr;
	
	public UDPSender(String destAddr, int port) throws UnknownHostException {
		setDestAddr(destAddr);
		setPort(port);
	}
	public String getDestAddr() {
		return destAddr;
	}
	public void setDestAddr(String dest) throws UnknownHostException {
		setInetAddr(InetAddress.getByName(dest));
		this.destAddr = dest;
	}
	protected InetAddress getInedAddr() {
		return inetAddr;
	}
	protected void setInetAddr(InetAddress i){
		inetAddr=i;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	
	public void send(byte[] message) throws IOException{ 
		DatagramSocket socket=new DatagramSocket();
		socket.send(new DatagramPacket(message, message.length,getInedAddr(),getPort()));
		socket.close();
	}
	public void send(String message) throws Exception{
		send(message.getBytes());
	}
	public static void send(String destAddr,int port,byte[] message) throws UnknownHostException,IOException {
		new UDPSender(destAddr, port).send(message);
	}
	public static void send(String destAddr,int port,String message) throws UnknownHostException,IOException {
		send(destAddr,port,message.getBytes());
	}
	public static void main(String[] args) {
		try {
			UDPSender sender=new UDPSender("localhost", 2001);
			int x=90;
			int y=10;
			String string="Hi, Diego, this is a successfull test";
			byte[] buffer=new byte[8+Integer.BYTES+string.length()];
			FullConverter.toBytes(buffer,0,x);
			FullConverter.toBytes(buffer,4,y);
			FullConverter.toBytes(buffer,8,string);
			sender.send(buffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
