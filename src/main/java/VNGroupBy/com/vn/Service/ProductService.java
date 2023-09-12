package VNGroupBy.com.vn.Service;

import VNGroupBy.com.vn.DTO.request.ApiParameter;
import VNGroupBy.com.vn.DTO.request.ProductReq;
import VNGroupBy.com.vn.DTO.response.MessagesResponse;

public interface ProductService {
    MessagesResponse save(ProductReq productReq) ;

    MessagesResponse getItems(ApiParameter apiParameter);

    MessagesResponse deleteById(long id);

    MessagesResponse getById(long id);
}
