package diegoLibraries.mail;

import diegoLibraries.interfaces.CloneObject;

public class MailUser implements CloneObject<MailUser>{
	private String name;
	private String password;
	private String host;
	private int port;
	
	public MailUser(String name, String password, String host,int port) {
		setName(name);
		setPassword(password);
		setHost(host);
		setPort(port);
	}
	public MailUser(MailUser us){
		this(us.getName(),us.getPassword(),us.getHost(),us.getPort());
	}
	public MailUser clone(){
		return new MailUser(this);
	}
	public int getPort() {
		return port;
	}

	public void setPort(int puerto) {
		this.port = puerto;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		String name=getName();
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		MailUser other = (MailUser) obj;
		String name=getName(),othersName=other.getName();
		if (name == null) {
			if (othersName != null)
				return false;
		} else if (!name.equals(othersName))
			return false;
		return true;
	}
}
