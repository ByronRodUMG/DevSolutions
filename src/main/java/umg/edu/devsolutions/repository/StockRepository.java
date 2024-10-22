package umg.edu.devsolutions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import umg.edu.devsolutions.entity.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
}