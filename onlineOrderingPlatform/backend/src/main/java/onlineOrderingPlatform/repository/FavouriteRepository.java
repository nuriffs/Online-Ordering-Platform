package onlineOrderingPlatform.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import onlineOrderingPlatform.model.Favourite;

@Repository
public interface FavouriteRepository extends JpaRepository<Favourite, Long> {
	
    List<Favourite> findByUserUserId(long userId);

	List<Favourite> findByUserUserIdAndMenuMenuId(long userId, long menuId);

	void deleteByUserUserIdAndMenuMenuId(long userId, long menuId);
}