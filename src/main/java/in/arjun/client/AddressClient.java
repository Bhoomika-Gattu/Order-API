package in.arjun.client;

import in.arjun.model.request.Address;
import in.arjun.model.response.AddressResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(url = "http://localhost:9092/",name = "ADDRESS-CLIENT")
public interface AddressClient {

    @PostMapping("/save")
    AddressResponse saveAddress(Address address);
}
