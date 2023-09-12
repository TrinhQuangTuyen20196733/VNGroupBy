package VNGroupBy.com.vn.Service;

import VNGroupBy.com.vn.DTO.request.CartItemReq;
import VNGroupBy.com.vn.DTO.response.MessagesResponse;

public interface CartItemService {
    MessagesResponse add(CartItemReq cartItemReq);

    MessagesResponse getMe();

    MessagesResponse delete(Long id);
}
