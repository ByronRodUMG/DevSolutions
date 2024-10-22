package umg.edu.devsolutions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import umg.edu.devsolutions.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
