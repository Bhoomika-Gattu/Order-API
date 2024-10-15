package in.arjun.service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import in.arjun.model.entity.OrderData;
import in.arjun.model.request.OrderDetailsRequest;
import in.arjun.model.response.OrderResponse;
import in.arjun.repository.OrderDataRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDataService {

    @Autowired
    private OrderDataRepository orderDataRepository;


    public OrderData saveOrder(OrderData orderDetails) {
        return orderDataRepository.save(orderDetails);
    }

    public Optional<OrderData> getOrderByPaymentOrderId(String orderId){
        return orderDataRepository.findByRazorPayPaymentId(orderId);
    }

    public List<OrderData> getOrderDataCustomerId(Long id){
        return orderDataRepository.findByCustomerId(id);

    }
}
