package VNGroupBy.com.vn.Controller;

import VNGroupBy.com.vn.DTO.request.ApiParameter;
import VNGroupBy.com.vn.DTO.request.CampaignReq;
import VNGroupBy.com.vn.DTO.response.MessagesResponse;
import VNGroupBy.com.vn.Service.CampaignService;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/campaigns")
public class CampaignController {
    @Autowired
    CampaignService campaignService;
    @PostMapping
    public MessagesResponse create (@RequestBody CampaignReq campaignReq) {
return  campaignService.save(campaignReq);
    }
    @PostMapping("/GetItems")
    public  MessagesResponse GetItems(@RequestBody ApiParameter apiParameter){
        return  campaignService.getItems(apiParameter);
    }
    @DeleteMapping("/{id}")
    public MessagesResponse DeleteById(@PathVariable @Positive long id) {
        return  campaignService.delete(id);
    }
    @GetMapping("/{id}")
    public  MessagesResponse getById(@PathVariable @Positive long id) {
        return  campaignService.findById(id);
    }
}
