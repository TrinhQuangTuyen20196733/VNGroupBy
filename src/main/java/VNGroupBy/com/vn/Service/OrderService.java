package VNGroupBy.com.vn.Service;

import VNGroupBy.com.vn.DTO.request.OrderReq;
import VNGroupBy.com.vn.DTO.response.MessagesResponse;

import java.util.List;

public interface OrderService {
   void Save(OrderReq orderReqs);

   MessagesResponse GetAllOfUser();

   MessagesResponse cancel(Long orderId);

    MessagesResponse GetAllOfShop();

    MessagesResponse approve(Long id);

    MessagesResponse findShipper(Long id);

    MessagesResponse GetAllOfShipper();

    MessagesResponse GetAll();

    MessagesResponse pickOrder(Long id);

    MessagesResponse receiveOrder(Long id);

    MessagesResponse DoneOrder(Long id);

    MessagesResponse ShipperCancel(Long id);

    MessagesResponse cancelShipperWatting(Long id);

    MessagesResponse SendBackOrder(Long id);

    MessagesResponse ApproveSendBack(Long id);
}
