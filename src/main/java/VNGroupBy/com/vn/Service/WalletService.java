package VNGroupBy.com.vn.Service;

import VNGroupBy.com.vn.DTO.response.MessagesResponse;

public interface WalletService {
    public MessagesResponse ToUp(int money) ;

   public MessagesResponse Withdraw(int money);

   public  MessagesResponse getBalance();
   public  MessagesResponse PlusToUser(int money, long userId);
}
