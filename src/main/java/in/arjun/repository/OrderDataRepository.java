package in.arjun.repository;

import in.arjun.model.entity.OrderData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderDataRepository extends JpaRepository<OrderData,Long> {

    Optional<OrderData> findByRazorPayPaymentId(String orderId);
    List<OrderData> findByCustomerId(Long id);

    @Query(value = "SELECT * FROM order_data WHERE DATE(delivery_date) = :localDate", nativeQuery = true)
    List<OrderData> findByDeliveryDate(@Param("localDate") LocalDate localDate);

}
