package onlineOrderingPlatform.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import onlineOrderingPlatform.model.CartItem;
import onlineOrderingPlatform.model.Order;
import onlineOrderingPlatform.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepo;
	
	public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

	public Optional<Order> getOrderById(Long orderId) {
        return orderRepo.findById(orderId);
    }

	public Order saveOrder(Order order) {
	    double totalAmount = calculateTotalAmount(order);
	    order.setTotalAmount(totalAmount);
	    return orderRepo.save(order);
	}

	private double calculateTotalAmount(Order order) {
		double totalAmount = 0.0;

	    for (CartItem cartItem : order.getCartItems()) {
	        totalAmount += cartItem.getPrice();
	    }

	    return totalAmount;
	}

    public void deleteOrder(Long orderId) {
        orderRepo.deleteById(orderId);
    }

//    public boolean markOrderForSelfPickup(long orderId) {
//        Optional<Order> optionalOrder = orderRepo.findById(orderId);
//
//        if (optionalOrder.isPresent()) {
//            Order order = optionalOrder.get();
//            // Implement logic to mark the order for self-pickup
//            // For example, set a flag or status in the Order entity
//            order.setMarkedForSelfPickup(true);
//            orderRepo.save(order);
//            return true;
//        } else {
//            return false;
//        }
//    }

}
