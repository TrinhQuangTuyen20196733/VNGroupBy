package VNGroupBy.com.vn.Service;

import VNGroupBy.com.vn.DTO.request.ShippingAddressReq;
import VNGroupBy.com.vn.DTO.response.MessagesResponse;

public interface ShippingAddressService {

    MessagesResponse save(ShippingAddressReq shippingAddressReq);

    MessagesResponse getMe();
}
