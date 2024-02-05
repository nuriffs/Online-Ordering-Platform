package onlineOrderingPlatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import onlineOrderingPlatform.model.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long>{

}
