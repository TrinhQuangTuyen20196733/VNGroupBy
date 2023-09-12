package VNGroupBy.com.vn.Mapper.MapperImpl;

import VNGroupBy.com.vn.DTO.request.ShippingAddressReq;
import VNGroupBy.com.vn.DTO.response.ShopRes;
import VNGroupBy.com.vn.Entity.ShippingAddress;
import VNGroupBy.com.vn.Entity.Shop;
import VNGroupBy.com.vn.Mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShippingAddressMapper implements Mapper<ShippingAddress, ShippingAddressReq> {
    @Override
    public ShippingAddress toEntity(ShippingAddressReq dto) {
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<ShippingAddressReq, ShippingAddressReq> typeMap =  modelMapper.createTypeMap(ShippingAddressReq.class,ShippingAddressReq.class);
        return modelMapper.map(dto,ShippingAddress.class);
    }

    @Override
    public ShippingAddressReq toDTO(ShippingAddress entity) {
        return null;
    }

    @Override
    public List<ShippingAddressReq> toDTOList(List<ShippingAddress> entityList) {
        return null;
    }

    @Override
    public List<ShippingAddress> toEntityList(List<ShippingAddressReq> dtoList) {
        return null;
    }
}
