package VNGroupBy.com.vn.Service;

import VNGroupBy.com.vn.DTO.request.ApiParameter;
import VNGroupBy.com.vn.DTO.response.MessagesResponse;
import VNGroupBy.com.vn.Entity.MoneyTransfer;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MoneyTransferService {
   void save(MoneyTransfer moneyTransfer);

    List<MoneyTransfer> getMe( ApiParameter apiParameter);
}
