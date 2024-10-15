package in.arjun.client;

import in.arjun.model.request.Customer;
import in.arjun.model.request.LoginRequest;
import in.arjun.model.response.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(url = "http://localhost:9091/",name = "CUSTOMER-CLIENT")
public interface CustomerClient {

    @PostMapping("/save")
    public CustomerResponse saveCustomer(Customer customer);

    @PostMapping("/find-customer")
    public CustomerResponse findCustomerByEmailAndPassword(LoginRequest loginRequest);

    @PostMapping("/find-email/{email}")
    public boolean findCustomerByEmail(@PathVariable("email") String email);
}
