package VNGroupBy.com.vn.Service.Impl;
import VNGroupBy.com.vn.DTO.response.MessagesResponse;
import VNGroupBy.com.vn.Repository.ShipFeeRepository;
import VNGroupBy.com.vn.Service.ShipFeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShipFeeServiceImpl implements ShipFeeService {
    @Autowired
    ShipFeeRepository shipFeeRepository;

    @Override
    public MessagesResponse getAll() {
        MessagesResponse ms = new MessagesResponse();
        try {
            ms.data = shipFeeRepository.findAll();
        } catch (Exception e) {
            ms.code = 404;
            ms.message = "Internal Server Error!";
        }
        return ms;
    }
}
