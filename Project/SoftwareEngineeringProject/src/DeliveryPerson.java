
public class DeliveryPerson extends Docket {
	
	private boolean delivered;

	public boolean isDelivered() {
		return delivered;
	}

	public void setDelivered(boolean delivered) {
		this.delivered = delivered;
	}

	public DeliveryPerson(long area,int docketnum,boolean delivered ) {
		super(area,docketnum);
		this.delivered = delivered;
		
	}
	

}
