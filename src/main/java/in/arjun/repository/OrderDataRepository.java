package in.arjun.repository;

import in.arjun.model.entity.OrderData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderDataRepository extends JpaRepository<OrderData,Long> {

    Optional<OrderData> findByRazorPayPaymentId(String orderId);
    List<OrderData> findByCustomerId(Long id);
}
