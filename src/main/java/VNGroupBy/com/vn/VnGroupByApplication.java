package VNGroupBy.com.vn;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@OpenAPIDefinition(
	info = @Info(
			title = "VN GroupBy API",
			version = "1.0.0",
			description = "Building VN GroupBy e-commerce platform",
			contact = @Contact(
					name = "Trịnh Quang Tuyến",
					email = "tuyen.tq196733@sis.hust.edu.vn"
			)
	)
)
public class VnGroupByApplication {

	public static void main(String[] args) {
		SpringApplication.run(VnGroupByApplication.class, args);
	}

}
