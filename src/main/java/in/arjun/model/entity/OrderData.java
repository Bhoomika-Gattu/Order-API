package in.arjun.model.entity;

import in.arjun.model.request.Address;
import in.arjun.model.request.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class OrderData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderTrackingNumber;
    private String totalQuantity;
    private Double totalPrice;
    private String orderStatus;
    @CreatedDate
    @Column(updatable = false)
    private Date orderCreatedDate;
    @LastModifiedDate
    @Column(insertable = false)
    private Date orderUpdationDate;
    private String razorPayPaymentId;

    private Long customerId;

    private Long addressId;

    @OneToMany(mappedBy = "orderData",cascade = CascadeType.ALL)
    private List<OrderItems> orderItems;
}
