package onlineOrderingPlatform;

public enum OrderStatus {
	PENDING("Pending"),
	PROCESSING("Processing"),
	OUT_FOR_DELIVERY("Out of Delivery"),
	DELIVERED("Delivered"), 
	CANCELLED("Cancelled");
	
	private final String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
