package diegoLibraries.mail;

public class MailReceiver {
	
	private String address;
	private String type;
	
	public static final String TYPE_CC="Cc";
	public static final String TYPE_BCC="Bcc";
	public static final String TYPE_TO="To";
	
	public MailReceiver(String address) {
		this(address,TYPE_CC);
	}
	public MailReceiver(String address, String type) {
		setAddress(address);
		setType(type);
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getType() {
		return type;
	}
	public void setType(String t) {
		switch(t) {
			case TYPE_CC:case TYPE_BCC:
				type=t;
				break;
			default:
				type=TYPE_CC;
		}
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MailReceiver other = (MailReceiver) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Recipient [address=" + address + ", type=" + type + "]";
	}
	
	
}
