package diegoLibraries.test.repository.springJdbc;

import diegoLibraries.repository.LongId;

public class Ticket extends LongId {
	public static final int NORMAL_TYPE=1;
	public static final int PREMIUM_TYPE=2;
	
	public static float NORMAL_PRICE=10;
	public static float PREMIUM_PRICE=20;
	
	private float price;
	private int type;
		
	public Ticket() {
	}

	public Ticket(float price, int type) {
		this(1,price,type);
	}
	public Ticket(int type) {
		this(1,getPriceByType(type),type);
	}
	public Ticket(long id,int type) {
		this(id,getPriceByType(type),type);
	}
	public Ticket(long id,float price, int type) {
		super(id);
		setPrice(price);
		setType(type);
	}
	
	public static float getPriceByType(int type){
		switch (type) {
			case NORMAL_TYPE:return NORMAL_PRICE;
			case PREMIUM_TYPE:return PREMIUM_PRICE;
			default:return 0;
		}
	}
	public float getPrice() {
		return price;
	}


	public void setPrice(float price) {
		this.price = price;
	}


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Ticket [price=" + price + ", type=" + type + "]";
	}
	
	

	

}
