package backend.stockstracker.Repository;

import backend.stockstracker.Models.Alert;
import backend.stockstracker.Models.AlertStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {

    //@Query(value = "select * from alert where alert_status = 'UP', nativeQuerry = true)
    List<Alert> findAllByAlertStatus(AlertStatus alertStatus);

    @Modifying
    @Transactional
    @Query("UPDATE Alert a SET a.alertStatus = :status WHERE a.id = :id")
    void updateAlert(@Param("alertStatus") AlertStatus alertStatus, @Param("id") Long id);

    List<Alert> findAllByUserId(long userId);
}
