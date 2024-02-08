package onlineOrderingPlatform.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cartIdGenerator")
	@SequenceGenerator(name="cartIdGenerator", sequenceName="cartIdGenerator", allocationSize = 1)
    private Long cartId;
    
    @OneToOne
    @JoinColumn(name = "userId")
    private User user;
    
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(name = "CART_CARTITEM", 
    		   joinColumns = {@JoinColumn(name = "cartId", referencedColumnName = "cartId")}, 
    		   inverseJoinColumns = {@JoinColumn(name = "cartItemId", referencedColumnName = "cartItemId")})
    private List<CartItem> cartItems;
    
	public Cart() {
		super();
	}

	public Cart(User user, List<CartItem> cartItems) {
		super();
		this.user = user;
		this.cartItems = cartItems;
	}

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}