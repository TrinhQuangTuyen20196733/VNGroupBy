package VNGroupBy.com.vn.Service;

import VNGroupBy.com.vn.DTO.request.ApiParameter;
import VNGroupBy.com.vn.DTO.request.CampaignReq;
import VNGroupBy.com.vn.DTO.response.MessagesResponse;

public interface CampaignService {
    MessagesResponse save(CampaignReq campaignReq);

    MessagesResponse getItems(ApiParameter apiParameter);

    MessagesResponse delete(long id);

    MessagesResponse findById(long id);
}
