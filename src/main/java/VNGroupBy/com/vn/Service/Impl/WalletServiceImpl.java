package VNGroupBy.com.vn.Service.Impl;

import VNGroupBy.com.vn.DTO.response.MessagesResponse;
import VNGroupBy.com.vn.Entity.MoneyTransfer;
import VNGroupBy.com.vn.Entity.UserEntity;
import VNGroupBy.com.vn.Entity.Wallet;
import VNGroupBy.com.vn.Repository.WalletRepository;
import VNGroupBy.com.vn.Security.UserDetails;
import VNGroupBy.com.vn.Security.UserPrincipal;
import VNGroupBy.com.vn.Service.MoneyTransferService;
import VNGroupBy.com.vn.Service.UserService;
import VNGroupBy.com.vn.Service.WalletService;
import VNGroupBy.com.vn.Utils.Caches.MyCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImpl implements WalletService {
    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private MoneyTransferService moneyTransferService;
    @Autowired
    private UserService userService;
    @Value("${payment_account_id}")
    private  int payment_account_id;


    @Override
    public MessagesResponse ToUp(int money) {
        MessagesResponse ms = new MessagesResponse();
        Wallet paymentWallet = walletRepository.findByUserId(payment_account_id).get();
        UserEntity adminAccount = userService.findById(payment_account_id);
        if (paymentWallet != null) {
      if (money<=paymentWallet.getMoney()) {

          var authentication =  SecurityContextHolder.getContext().getAuthentication();
          var user = (UserDetails)authentication.getPrincipal();
          var userId = user.getId();

          var userWallet = walletRepository.findByUserId(userId).get();
          try {
              var userAccount = userService.findById(userId);
              paymentWallet.subMoney(money);
              userWallet.addMoney(money);
              walletRepository.save(paymentWallet);
              walletRepository.save(userWallet);
              MoneyTransfer moneyTransfer = new MoneyTransfer();
              moneyTransfer.setAmount(money);
              moneyTransfer.setToUser(userAccount);
              moneyTransfer.setFormUser(adminAccount);
              moneyTransferService.save(moneyTransfer);
          }
          catch (Exception e) {
              ms.code = 500;
              ms.message="Nạp tiền không thành công. Vui lòng thử lại!";
          }

      } else {
          ms.code=500;
          ms.message="Tạm thời chưa thể nạp thêm tiền do số dư tài khoản hệ thông không đủ!";
      }
        }
return ms;
    }


    @Override
    public MessagesResponse Withdraw(int money) {
        MessagesResponse ms = new MessagesResponse();
        var authentication =  SecurityContextHolder.getContext().getAuthentication();
        var user = (UserDetails)authentication.getPrincipal();
        var userId = user.getId();
        var userWallet = walletRepository.findByUserId(userId).get();
        UserEntity adminAccount = userService.findById(payment_account_id);
        var userAccount = userService.findById(userId);
        if (userWallet!=null) {

            if (userWallet.getMoney()>=money) {
                Wallet paymentWallet = walletRepository.findByUserId(payment_account_id).get();

                try {
                    userWallet.subMoney(money);
                    paymentWallet.addMoney(money);
                    walletRepository.save(paymentWallet);
                    walletRepository.save(userWallet);
                    MoneyTransfer moneyTransfer = new MoneyTransfer();
                    moneyTransfer.setAmount(money);
                    moneyTransfer.setToUser(adminAccount);
                    moneyTransfer.setFormUser(userAccount);
                    moneyTransferService.save(moneyTransfer);
                }
                catch (Exception e) {
                    ms.code = 500;
                    ms.message="Rút tiền không thành công. Vui lòng thử lại!";
                }
            }
            else {
                ms.code=500;
                ms.message="Số dư khả dụng của quý khách hiện tại không đủ!";
            }
        }



        return ms;
    }

    @Override
    public MessagesResponse getBalance() {
        MessagesResponse ms = new MessagesResponse();
        var authentication =  SecurityContextHolder.getContext().getAuthentication();
        var user = (UserDetails)authentication.getPrincipal();
        var userId = user.getId();
        var userWallet = walletRepository.findByUserId(userId).get();
        if (userWallet != null) {
            ms.code =200;
            ms.data = userWallet.getMoney();
        } else  {
            ms.code=500;
            ms.message="Internal Server Error!";
        }
        return  ms;
    }

    @Override
    public MessagesResponse PlusToUser(int money, long userId) {
        MessagesResponse ms = new MessagesResponse();
        Wallet paymentWallet = walletRepository.findByUserId(payment_account_id).get();
        UserEntity adminAccount = userService.findById(payment_account_id);
        if (paymentWallet != null) {
            if (money<=paymentWallet.getMoney()) {


                var userWallet = walletRepository.findByUserId(userId).get();
                try {
                    var userAccount = userService.findById(userId);
                    paymentWallet.subMoney(money);
                    userWallet.addMoney(money);
                    walletRepository.save(paymentWallet);
                    walletRepository.save(userWallet);
                    MoneyTransfer moneyTransfer = new MoneyTransfer();
                    moneyTransfer.setAmount(money);
                    moneyTransfer.setToUser(userAccount);
                    moneyTransfer.setFormUser(adminAccount);
                    moneyTransferService.save(moneyTransfer);
                }
                catch (Exception e) {
                    ms.code = 500;
                    ms.message="Chuyển tiền không thành công. Vui lòng thử lại!";
                }

            } else {
                ms.code=500;
                ms.message="Tạm thời chưa thể nạp thêm tiền do số dư tài khoản hệ thông không đủ!";
            }
        }
        return ms;
    }
}
