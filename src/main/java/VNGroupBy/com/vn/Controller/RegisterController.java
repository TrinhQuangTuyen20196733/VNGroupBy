package VNGroupBy.com.vn.Controller;

import VNGroupBy.com.vn.DTO.ConfirmOTP;
import VNGroupBy.com.vn.DTO.RegisterDTO;
import VNGroupBy.com.vn.Service.EmailService;
import VNGroupBy.com.vn.Service.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    OTPService otpService;
    @Autowired
    EmailService emailService;

    @PostMapping ("/generateOtp")
    public ResponseEntity<String> generateOTP(@RequestBody RegisterDTO registerDTO) {

        String userName = registerDTO.getUserName();
        int otp = otpService.generateOTP(userName);
        //Generate The Template to send OTP

        String message = "Your OTP verified number of VNGRoupBy is: " + otp;
        try {
            emailService.sendOtpMessage(registerDTO.getEmail(), "OTP VNGroupBy verified code", message);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }


        return ResponseEntity.status(HttpStatus.OK).body("Sent");
    }

    @PostMapping("/validateOtp")
    public ResponseEntity<String> validateOtp(@RequestBody  ConfirmOTP confirmOTP) {

        final String SUCCESS = "Entered Otp is valid";
        final String FAIL = "Entered Otp is NOT valid. Please Retry!";
        String username = confirmOTP.getUserName();
        int otpnum = confirmOTP.getOtpNum();
        //Validate the Otp
        if (otpnum >= 0) {

            int serverOtp = otpService.getOtp(username);
            if (serverOtp > 0) {
                if (otpnum == serverOtp) {
                    otpService.clearOTP(username);

                    return ResponseEntity.status(HttpStatus.OK).body(SUCCESS);
                } else {
                    return ResponseEntity.status(HttpStatus.OK).body(FAIL);
                }
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(FAIL);
            }
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(FAIL);
        }
    }
}
