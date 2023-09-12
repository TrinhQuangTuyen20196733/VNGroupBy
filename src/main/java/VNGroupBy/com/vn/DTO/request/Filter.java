package VNGroupBy.com.vn.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.PublicKey;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Filter {
    public Date created;
    public  String author;
    public  boolean ascending = false;
    public  String categoryCode;
    public String orderBy;
}
