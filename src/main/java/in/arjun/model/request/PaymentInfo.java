package in.arjun.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentInfo {

    private String paymentId;
    private String orderId;
    private String status;
    private Double amount;
}
