package VNGroupBy.com.vn.Controller;

import VNGroupBy.com.vn.DTO.request.ConfirmOTP;
import VNGroupBy.com.vn.DTO.response.MessagesResponse;
import VNGroupBy.com.vn.DTO.request.RegisterReq;
import VNGroupBy.com.vn.Entity.UserEntity;
import VNGroupBy.com.vn.Service.Impl.EmailService;
import VNGroupBy.com.vn.Service.Impl.OTPService;
import VNGroupBy.com.vn.Service.UserService;
import VNGroupBy.com.vn.Utils.Caches.MyCache;
import VNGroupBy.com.vn.Utils.Caches.TextMessage;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    UserService userService;
    @Autowired
    OTPService otpService;
    @Autowired
    EmailService emailService;
    @Autowired
    MyCache myCache;

    @PostMapping ("/generateOtp")
    public MessagesResponse generateOTP(@RequestBody @Valid RegisterReq registerReq) {
        MessagesResponse ms = new MessagesResponse();
        ms.message="Sent";
        String email = registerReq.getEmail();
        int otp = otpService.generateOTP(email);
        //Generate The Template to send OTP

        String message = "Your OTP verified number of VNGRoupBy is: " + otp;
        try {
            if (myCache.getFromCache(registerReq.getEmail())!=null) {
                ms.code = 404;
                ms.message = "Your email has been used!";
            } else {
                myCache.saveToCache(registerReq.getEmail(),registerReq);
            }
            emailService.sendOtpMessage(registerReq.getEmail(), "OTP VNGroupBy verified code", message);
        } catch (Exception e) {
            ms.code=HttpStatus.NOT_ACCEPTABLE.value();
            ms.message=e.getMessage();

        }


        return ms;
    }

    @PostMapping("/validateOtp")
    public MessagesResponse validateOtp(@RequestBody @Valid ConfirmOTP confirmOTP) {

        final String SUCCESS = "Register Successfully!";
        final String FAIL = "Entered Otp is NOT valid. Please Retry!";
        MessagesResponse ms = new MessagesResponse();
        ms.message=SUCCESS;
        String email = confirmOTP.getEmail();
        RegisterReq registerReq = (RegisterReq) myCache.getFromCache(email);
        myCache.deleteFromCache(email);
        int otpnum = confirmOTP.getOtpNum();
        //Validate the Otp
        if (otpnum >= 0) {

            int serverOtp = otpService.getOtp(email);
            if (serverOtp > 0) {
                if (otpnum == serverOtp) {
                    otpService.clearOTP(email);
                    //Save Account

                    try {
                        UserEntity user = userService.create(registerReq);
                        myCache.updateCache(email, user);
                    }
                    catch (Exception e ) {
                        ms.code=HttpStatus.INTERNAL_SERVER_ERROR.value();
                        ms.message = TextMessage.CannotRegisterAccount;
                    }


                } else {

                   ms.code=HttpStatus.UNAUTHORIZED.value();
                   ms.message=FAIL;
                }
            } else {
                ms.code=HttpStatus.UNAUTHORIZED.value();
                ms.message=FAIL;
            }
        } else {
            ms.code=HttpStatus.UNAUTHORIZED.value();
            ms.message=FAIL;
        }
        return ms;
    };

}
