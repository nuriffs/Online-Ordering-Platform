package onlineOrderingPlatform.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import onlineOrderingPlatform.OrderStatus;

@Entity
@Table(name = "user_order")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderIdGenerator")
	@SequenceGenerator(name="orderIdGenerator", sequenceName="orderIdGenerator", allocationSize = 1)
    private long orderId;

    private double totalAmount;
    
   
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<CartItem> cartItems;


    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    
	public Order() {
		super();
	}

	public Order(double totalAmount, User user, List<CartItem> cartItems, OrderStatus orderStatus) {
		super();
		this.totalAmount = totalAmount;
		this.user = user;
		this.cartItems = cartItems;
		this.orderStatus = orderStatus;
	}

	public long getOrderId() {
		return orderId;
	}


	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}


	public double getTotalAmount() {
		return totalAmount;
	}


	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public List<CartItem> getCartItems() {
		return cartItems;
	}


	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}


	public OrderStatus getOrderStatus() {
		return orderStatus;
	}


	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
    
    
}
