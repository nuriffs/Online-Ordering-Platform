package onlineOrderingPlatform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import onlineOrderingPlatform.exception.CartItemNotFoundException;
import onlineOrderingPlatform.exception.MenuItemNotFoundException;
import onlineOrderingPlatform.exception.UserNotFoundException;
import onlineOrderingPlatform.model.CartItem;
import onlineOrderingPlatform.model.Menu;
import onlineOrderingPlatform.model.Order;
import onlineOrderingPlatform.model.User;
import onlineOrderingPlatform.repository.CartItemRepository;
import onlineOrderingPlatform.repository.MenuRepository;
import onlineOrderingPlatform.repository.OrderRepository;
import onlineOrderingPlatform.repository.UserRepository;

@Service
public class CartItemService {
	@Autowired
	private CartItemRepository cartItemRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
    private MenuRepository menuRepo;

    @Autowired
    private OrderRepository orderRepo;

    public List<CartItem> getCartItemsByUserId(long userId) throws UserNotFoundException, CartItemNotFoundException {
        User user = userRepo.findByUserId(userId);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }

        // Assuming an order represents the cart for the user
        Order cart = orderRepo.findCartByUser(user);
        if (cart == null) {
            throw new CartItemNotFoundException("Cart not found");
        }

        return cart.getCartItems();
    }

    @Transactional
    public void addToCart(long userId, long cartItemId, CartItem cartItem) throws UserNotFoundException, MenuItemNotFoundException, CartItemNotFoundException {
        User user = userRepo.findByUserId(userId);
        if (user == null) {
        	throw new UserNotFoundException("User with ID " + userId + " not found");
        }

        if (!isValidMenuItem(cartItem.getMenu().getFoodId())) {
            throw new MenuItemNotFoundException("Menu item not found");
        }

        // Assuming an order represents the cart for the user
        Order cart = orderRepo.findCartByUser(user);
        if (cart == null) {
            throw new CartItemNotFoundException("Cart not found");
        }

        CartItem existingCartItem = findCartItemByMenuAndOrder(cartItem.getMenu(), cart);
        if (existingCartItem != null) {
            // Update quantity
            existingCartItem.setQuantity(existingCartItem.getQuantity() + cartItem.getQuantity());
            cartItemRepo.save(existingCartItem);
        } else {
            // Add the new item to the cart
            cartItem.setOrder(cart);
            cartItemRepo.save(cartItem);
        }
    }

    private CartItem findCartItemByMenuAndOrder(Menu menu, Order cart) {
        return cartItemRepo.findByMenuAndOrder(menu, cart);
    }


	private boolean isValidMenuItem(long foodId) {
        return menuRepo.findById(foodId).isPresent();
    }

	public boolean removeFromCart(String userId, Long cartItemId) {
		// TODO Auto-generated method stub
		return false;
	}
}
