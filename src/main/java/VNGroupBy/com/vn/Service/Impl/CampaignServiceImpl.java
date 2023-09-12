package VNGroupBy.com.vn.Service.Impl;

import VNGroupBy.com.vn.DTO.request.ApiParameter;
import VNGroupBy.com.vn.DTO.request.CampaignReq;
import VNGroupBy.com.vn.DTO.response.MessagesResponse;
import VNGroupBy.com.vn.Mapper.MapperImpl.CampaignMapper;
import VNGroupBy.com.vn.Repository.CampaignRepository;
import VNGroupBy.com.vn.Repository.Impl.CampaignRepositoryImpl;
import VNGroupBy.com.vn.Service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CampaignServiceImpl implements CampaignService {
    final  CampaignRepositoryImpl customCampaignRepository;
    final  CampaignMapper campaignMapper;
    final  CampaignRepository campaignRepository;

    public CampaignServiceImpl(CampaignRepositoryImpl customCampaignRepository, CampaignMapper campaignMapper, CampaignRepository campaignRepository) {
        this.customCampaignRepository = customCampaignRepository;
        this.campaignMapper = campaignMapper;
        this.campaignRepository = campaignRepository;
    }

    @Override
    public MessagesResponse save(CampaignReq campaignReq) {
        MessagesResponse ms = new MessagesResponse();
        try {
            var campaign = campaignMapper.toEntity(campaignReq);
            campaignRepository.save(campaign);

        }
        catch (Exception e) {
            ms.code=500;
            ms.message="Thêm chiến dịch không thành công, vui lòng thử lại!";
        }

        return ms;
    }

    @Override
    public MessagesResponse getItems(ApiParameter apiParameter) {
        MessagesResponse ms = new MessagesResponse();

        try {
            var campaign = customCampaignRepository.getByFilter(apiParameter);
            ms.data = campaignMapper.toDTOList(campaign);
        } catch (Exception e) {
            ms.code = 404;
            ms.message = "Item Not Found!";
        }
        return ms;
    }

    @Override
    public MessagesResponse delete(long id) {
        MessagesResponse ms = new MessagesResponse();
        try {
            campaignRepository.deleteById(id);
        }
        catch (Exception ex) {
            ms.code = 500;
            ms.message="Không thể thực hiện thao tác xóa chiến dịch!";
        }
        return  ms;
    }

    @Override
    public MessagesResponse findById(long id) {
        MessagesResponse ms  = new MessagesResponse();
        try {
            var compaign =campaignRepository.findById(id).get();
            ms.data= campaignMapper.toDTO(compaign);
        }
        catch (Exception e) {
            ms.code=404;
            ms.message="Item Not Found!";
        }
        return  ms;
    }
}
