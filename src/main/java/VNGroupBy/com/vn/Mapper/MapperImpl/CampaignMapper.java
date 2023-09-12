package VNGroupBy.com.vn.Mapper.MapperImpl;

import VNGroupBy.com.vn.DTO.request.CampaignReq;
import VNGroupBy.com.vn.Entity.Campaign;
import VNGroupBy.com.vn.Entity.PriceLevel;
import VNGroupBy.com.vn.Mapper.Mapper;
import VNGroupBy.com.vn.Repository.ProductRepositoty;
import VNGroupBy.com.vn.Security.UserDetails;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CampaignMapper implements Mapper<Campaign, CampaignReq> {
    @Autowired
    ProductRepositoty productRepositoty;
    @Override
    public Campaign toEntity(CampaignReq dto) {
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<CampaignReq, Campaign> typeMap = modelMapper.createTypeMap(CampaignReq.class, Campaign.class);
        var campaign = modelMapper.map(dto, Campaign.class);
        var authentication =  SecurityContextHolder.getContext().getAuthentication();
        var user = (UserDetails)authentication.getPrincipal();
        var userId = user.getId();
        var product = productRepositoty.findByNameAndUserId(dto.productName,userId).get();
        List<PriceLevel> priceLevels = dto.levels.stream().map((item)->{
            return new PriceLevel(item.getQuantity(), item.getPrice());
        }).collect(Collectors.toList());
        campaign.setProduct(product);
        campaign.setLevels(priceLevels);
        Date timenow = new Date();
        if (timenow.before(dto.endTime) && timenow.after(dto.startTime)) {

            campaign.setActive(true);
        }
        return campaign;
    }

    @Override
    public CampaignReq toDTO(Campaign entity) {
        CampaignReq campaignReq = new CampaignReq();
        campaignReq.name = entity.getName();
        campaignReq.productName=entity.getProduct().getName();
        campaignReq.currentPrice = entity.getCurrentPrice();
        campaignReq.deposit = entity.getDeposit();
        campaignReq.startTime =entity.getStartTime();
        campaignReq.endTime=entity.getEndTime();
        campaignReq.inStock= entity.getInStock();
        campaignReq.levels = entity.getLevels();
        campaignReq.id = entity.getId();
        campaignReq.productImage = entity.getProduct().getImageUrl();
        campaignReq.productBrand= entity.getProduct().getBrand();
        campaignReq.soldQuantity=entity.getSoldQuantity();
        campaignReq.productCategory = entity.getProduct().getCategory().getName();
        return campaignReq;
    }

    @Override
    public List<CampaignReq> toDTOList(List<Campaign> entityList) {
      return   entityList.stream().map(item->toDTO(item)).collect(Collectors.toList());

    }

    @Override
    public List<Campaign> toEntityList(List<CampaignReq> dtoList) {
        return null;
    }
}
