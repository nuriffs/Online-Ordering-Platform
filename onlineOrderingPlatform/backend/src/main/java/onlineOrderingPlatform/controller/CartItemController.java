package onlineOrderingPlatform.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import onlineOrderingPlatform.exception.CartItemNotFoundException;
import onlineOrderingPlatform.exception.MenuItemNotFoundException;
import onlineOrderingPlatform.exception.UserNotFoundException;
import onlineOrderingPlatform.model.CartItem;
import onlineOrderingPlatform.service.CartItemService;

@RestController
@RequestMapping("/api/v1/cart")
@CrossOrigin(origins = "http://localhost:3000")
public class CartItemController {
	private CartItemService cartItemService;
	
	// Retrieve list of items in the cart
	@GetMapping("/{userId}")
	public ResponseEntity<?> getCartItemsByUserId(@PathVariable long userId) throws UserNotFoundException, CartItemNotFoundException {
        List<CartItem> cartItems = cartItemService.getCartItemsByUserId(userId);
        
        if (!cartItems.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(cartItems);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Menu items not found");
        }
    }
		
	// Add an item to the cart
	@PostMapping("/{userId}/{cartItemId}")
	public ResponseEntity<String> addToCart(@PathVariable long userId, @PathVariable long cartItemId, @RequestBody CartItem cartItem) throws UserNotFoundException {
	    try {
	        cartItemService.addToCart(userId, cartItemId, cartItem);
	        return ResponseEntity.ok("Item added successfully to cart");
	    } catch (CartItemNotFoundException cnfe) {
	        return ResponseEntity.status(404).body(cnfe.getMessage());
	    } catch (MenuItemNotFoundException minfe) {
	        return ResponseEntity.status(400).body(minfe.getMessage());
	    }
	}


	// Remove an item from the cart
	@DeleteMapping("/{userId}/items/{cartItemId}")
	public ResponseEntity<String> removeFromCart(@PathVariable String userId, @PathVariable Long cartItemId) {
	    if (cartItemService.removeFromCart(userId, cartItemId)) {
	    	return ResponseEntity.status(HttpStatus.OK).body("Item removed successfully from cart");
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found in the cart");
	    }
	}

	// Remove all items in the cart
	
}
