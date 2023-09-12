package VNGroupBy.com.vn.Service.Impl;

import VNGroupBy.com.vn.DTO.request.ApiParameter;
import VNGroupBy.com.vn.Entity.MoneyTransfer;
import VNGroupBy.com.vn.Repository.MoneyTransferRepository;
import VNGroupBy.com.vn.Security.UserDetails;
import VNGroupBy.com.vn.Service.MoneyTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class MoneyTransferServiceImpl implements MoneyTransferService {
    @Autowired
    MoneyTransferRepository moneyTransferRepository;

    @Override
    public void save(MoneyTransfer moneyTransfer) {
        moneyTransferRepository.save(moneyTransfer);
    }

    @Override
    public List<MoneyTransfer> getMe( ApiParameter apiParameter) {
        var authentication =  SecurityContextHolder.getContext().getAuthentication();
        var user = (UserDetails)authentication.getPrincipal();
        var userId = user.getId();
        int page = apiParameter.page;
        int limit = apiParameter.limit;
        if (page!=0 && limit!=0) {
            var lstOptional = moneyTransferRepository.getMe(userId,limit, (page-1)*limit);
            if (lstOptional.isPresent()) {
                var lst = lstOptional.get();
                return  lst;
            }
        }

        return null;
    }
}
