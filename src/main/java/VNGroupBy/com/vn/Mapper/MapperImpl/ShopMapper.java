package VNGroupBy.com.vn.Mapper.MapperImpl;

import VNGroupBy.com.vn.DTO.request.RegisterReq;
import VNGroupBy.com.vn.DTO.request.ShippingAddressReq;
import VNGroupBy.com.vn.DTO.response.ShopRes;
import VNGroupBy.com.vn.Entity.ShippingAddress;
import VNGroupBy.com.vn.Entity.Shop;
import VNGroupBy.com.vn.Entity.UserEntity;
import VNGroupBy.com.vn.Mapper.Mapper;
import VNGroupBy.com.vn.Service.UploadService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ShopMapper implements Mapper<Shop, ShopRes> {

    @Override
    public Shop toEntity(ShopRes dto) {
        return null;
    }

    @Override
    public ShopRes toDTO(Shop entity) {
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<Shop, ShopRes> typeMap =  modelMapper.createTypeMap(Shop.class,ShopRes.class);
        return modelMapper.map(entity, ShopRes.class);
    }

    @Override
    public List<ShopRes> toDTOList(List<Shop> entityList) {
        return null;
    }

    @Override
    public List<Shop> toEntityList(List<ShopRes> dtoList) {
        return null;
    }
}
