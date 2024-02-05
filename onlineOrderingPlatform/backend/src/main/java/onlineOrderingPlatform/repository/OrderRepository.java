package onlineOrderingPlatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import onlineOrderingPlatform.model.Order;
import onlineOrderingPlatform.model.User;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	Order findCartByUser(User user);

}
