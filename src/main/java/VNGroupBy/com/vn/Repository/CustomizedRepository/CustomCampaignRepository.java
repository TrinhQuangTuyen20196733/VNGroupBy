package VNGroupBy.com.vn.Repository.CustomizedRepository;

import VNGroupBy.com.vn.DTO.request.ApiParameter;
import VNGroupBy.com.vn.Entity.Campaign;
import VNGroupBy.com.vn.Entity.Product;

import java.util.List;

public interface CustomCampaignRepository {
    List<Campaign> getByFilter(ApiParameter apiParameter);
}
