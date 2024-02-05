package onlineOrderingPlatform.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import onlineOrderingPlatform.exception.CartItemNotFoundException;
import onlineOrderingPlatform.model.CartItem;
import onlineOrderingPlatform.model.Menu;
import onlineOrderingPlatform.model.Order;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
	
	CartItem findByCartItemId(long cartItemId) throws CartItemNotFoundException;
	
    List<CartItem> findByOrderUserUserId(long userId);

	CartItem findByMenuAndOrder(Menu menu, Order cart);
    
}
