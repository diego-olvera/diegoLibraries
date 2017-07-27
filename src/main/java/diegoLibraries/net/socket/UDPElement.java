package diegoLibraries.net.socket;

import diegoLibraries.stream.Printer;

public class UDPElement extends Thread{
	protected Printer printer;
	protected UDPSender sender;
	protected UDPReceiver receiver;
	private int unicId;
	
	public UDPElement(Printer printer,int idUnico,UDPSender emisor,UDPReceiver receptor) {
		setPrinter(printer);
		setUnicId(idUnico);
		setSender(emisor);
		setReceiver(receptor);
	}

	protected void setSender(UDPSender emisor) {
		this.sender = emisor;
	}

	protected void setReceiver(UDPReceiver receptor) {
		this.receiver = receptor;
	}

	public int getUnicId() {
		return unicId; 
	}

	public void setUnicId(int id) {
		this.unicId = id;
	}

	public void setPrinter(Printer p) {
		printer=p;
	}
	public Printer getPrinter(){
		return printer;
	}

}
