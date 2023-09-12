package VNGroupBy.com.vn.Service;

import VNGroupBy.com.vn.DTO.response.MessagesResponse;
import VNGroupBy.com.vn.Entity.Shop;

public interface ShopService {
    MessagesResponse save(Shop shop);

    MessagesResponse getInfo();
}
