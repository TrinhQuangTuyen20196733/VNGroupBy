package VNGroupBy.com.vn.Repository.CustomizedRepository;

import VNGroupBy.com.vn.DTO.request.ApiParameter;
import VNGroupBy.com.vn.Entity.CartItem;
import VNGroupBy.com.vn.Entity.Product;

import java.util.List;

public interface CustomCartItemRepository {
    List<CartItem> getByFilter(ApiParameter apiParameter);
}
