package onlineOrderingPlatform.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import onlineOrderingPlatform.model.Favourite;

public interface FavouriteRepository extends JpaRepository<Favourite, Long> {
	
    List<Favourite> findByUserUserId(long userId);

	List<Favourite> findByUserUserIdAndMenuFoodId(long userId, long menuId);

	void deleteByUserUserIdAndMenuFoodId(long userId, long menuId);
}