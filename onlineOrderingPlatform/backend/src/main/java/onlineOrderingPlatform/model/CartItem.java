package onlineOrderingPlatform.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;



@Entity
public class CartItem {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cartItemIdGenerator")
	@SequenceGenerator(name="cartItemIdGenerator", sequenceName="cartItemIdGenerator", allocationSize = 1)
	private Long cartItemId;
	
	private Long itemId;
    private String itemName;
    private int quantity;
    private double price;
    
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = true)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;
    
    @ManyToMany(mappedBy = "cartItems")
    private List<Cart> cart;

	public CartItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CartItem(Long itemId, String itemName, int quantity, double price, Order order, Menu menu, List<Cart> cart) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.quantity = quantity;
		this.price = price;
		this.order = order;
		this.menu = menu;
		this.cart = cart;
	}

	public Long getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(Long cartItemId) {
		this.cartItemId = cartItemId;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public List<Cart> getCart() {
		return cart;
	}

	public void setCart(List<Cart> cart) {
		this.cart = cart;
	}

	
}
