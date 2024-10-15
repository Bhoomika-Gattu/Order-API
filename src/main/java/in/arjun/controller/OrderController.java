package in.arjun.controller;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import in.arjun.client.AddressClient;
import in.arjun.client.CustomerClient;
import in.arjun.model.entity.OrderData;
import in.arjun.model.entity.OrderItems;
import in.arjun.model.request.*;
import in.arjun.model.response.AddressResponse;
import in.arjun.model.response.CustomerResponse;
import in.arjun.model.response.DashBoardResponse;
import in.arjun.model.response.OrderResponse;
import in.arjun.service.OrderDataService;
import in.arjun.service.OrderItemsService;
import in.arjun.service.TrackingNumberGenerator;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {

    @Autowired
    private CustomerClient customerClient;

    @Autowired
    private AddressClient addressClient;

    private RazorpayClient client;

    @Value("${razorpay.key.id}")
    private String razorPayKey;

    @Value("${razorpay.secret.key}")
    private String razorPaySecret;

    @Autowired
    private OrderDataService orderDataService;

    @Autowired
    private OrderItemsService itemsService;

    @PostMapping("/api/checkout-details")
    public OrderResponse getCheckoutDetails(@RequestBody CheckoutDetails checkoutDetails) throws RazorpayException {

        Customer customer = checkoutDetails.getCustomer();
        CustomerResponse customerResponse = customerClient.saveCustomer(customer);

        Address address = checkoutDetails.getAddress();
        AddressResponse addressResponse = addressClient.saveAddress(address);

        OrderData orderDetails = checkoutDetails.getOrderData();

        JSONObject orderReq=new JSONObject();

        orderReq.put("amount", orderDetails.getTotalPrice() * 100);
        orderReq.put("currency","INR");
//        orderReq.put("receipt",orderDetails.get)

        this.client= new RazorpayClient(razorPayKey,razorPaySecret);

        Order razorPayOrder = client.orders.create(orderReq);

        OrderData orderData=OrderData.builder()
                .orderTrackingNumber(TrackingNumberGenerator.generateTrackingNumber())
                .totalQuantity(orderDetails.getTotalQuantity())
                .totalPrice(orderDetails.getTotalPrice())
                .razorPayPaymentId(razorPayOrder.get("id"))
                .orderStatus(razorPayOrder.get("status"))
                .customerId(customerResponse.getId())
                .addressId(addressResponse.getId())
                .build();
        OrderData savedOrderDetails = orderDataService.saveOrder(orderData);

        List<OrderItems> orderItems = checkoutDetails.getOrderItems();

        for (OrderItems items: orderItems) {

            OrderItems itemsDetails = OrderItems.builder()
                    .imageUrl(items.getImageUrl())
                    .quantity(items.getQuantity())
                    .orderData(savedOrderDetails)
                    .productId(items.getProductId())
                    .price(items.getPrice())
                    .build();
            itemsService.saveOrderItems(itemsDetails);
        }

        return OrderResponse.builder()
                .orderTrackingNumber(savedOrderDetails.getOrderTrackingNumber())
                .razorPayPaymentId(savedOrderDetails.getRazorPayPaymentId())
                .build();
    }

    @PostMapping("/update-payment-status")
    public void updatePaymentStatus(@RequestBody PaymentInfo paymentInfo){
        Optional<OrderData> orderByPaymentOrderId = orderDataService.getOrderByPaymentOrderId(paymentInfo.getOrderId());
        if (orderByPaymentOrderId.isPresent()){
            OrderData orderData = orderByPaymentOrderId.get();
            orderData.setOrderStatus(paymentInfo.getStatus());
            orderDataService.saveOrder(orderData);
        }
    }

    @PostMapping("/find-customer")
    public List<DashBoardResponse> findCustomerByEmailAndPassword(@RequestBody LoginRequest loginRequest){


        CustomerResponse customerByEmailAndPassword = customerClient.findCustomerByEmailAndPassword(loginRequest);
        if (customerByEmailAndPassword != null){
           List<OrderData> orderDataCustomerId = orderDataService.getOrderDataCustomerId(customerByEmailAndPassword.getId());

          return orderDataCustomerId.stream().map(DashBoardResponse::fromOrder).toList();
        }
        return null;
    }

    @PostMapping("/find-customer-by-email/{email}")
    public boolean findCustomerByEmail(@PathVariable String email){
        boolean customerByEmail = customerClient.findCustomerByEmail(email);
        if (customerByEmail)
            return true;
        return false;
    }

}
