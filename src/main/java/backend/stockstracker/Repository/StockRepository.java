package backend.stockstracker.Repository;

import backend.stockstracker.Models.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    Stock save(Stock stock);

    Stock findBySymbol(String symbol);

    void deleteById(long id);
}
