package com.example.shibo.registration;

import com.example.shibo.appuser.AppUser;
import com.example.shibo.appuser.AppUserRole;
import com.example.shibo.appuser.AppUserService;
import com.example.shibo.email.EmailSender;
import com.example.shibo.registration.token.ConfirmationToken;
import com.example.shibo.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final AppUserService appUserService;
    private final EmailValidator emailValidator;
    private final PasswordValidator passwordValidator;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailSender emailSender;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if(!isValidEmail) {
            throw new IllegalStateException("email not valid");
        }
        boolean isValidPassword = passwordValidator.test(request.getPassword());
        if(!isValidPassword) {
            throw new IllegalStateException("password not valid");
        }
        String token =  appUserService.signUp(new AppUser(request.getUsername(), request.getEmail(), request.getPassword(), AppUserRole.USER));
        String link = "http://localhost:8080/api/v1/registration/confirm?token="+token;
        emailSender.send(request.getEmail(), buildEmail(request.getUsername(), link));
        return token;
    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService.getToken(token).orElseThrow(() -> new IllegalStateException("token not found"));
        if(confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if(expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        appUserService.enableUser(confirmationToken.getAppUser().getEmail());
        return "confirmed";
    }

    private String buildEmail(String name, String link) {

        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "  <head>\n" +
                "    <meta charset=\"UTF-8\" />\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
                "    <style>\n" +
                "      body {\n" +
                "        font-family: sans-serif;\n" +
                "        color: #153243;\n" +
                "        background-color: #f4f9e9;\n" +
                "      }\n" +
                "      a {\n" +
                "        text-decoration: none;\n" +
                "      }\n" +
                "      .container {\n" +
                "        margin: 0 4rem;\n" +
                "      }\n" +
                "      .d-flex {\n" +
                "        display: flex;\n" +
                "      }\n" +
                "      .justify-content-around {\n" +
                "        justify-content: space-around;\n" +
                "      }\n" +
                "      .align-items-center {\n" +
                "        align-items: center;\n" +
                "      }\n" +
                "      .justify-content-center {\n" +
                "        justify-content: center;\n" +
                "      }\n" +
                "      .display-5 {\n" +
                "        font-size: 3rem;\n" +
                "      }\n" +
                "    </style>\n" +
                "  </head>\n" +
                "  <body>\n" +
                "    <div class=\"container\">\n" +
                "      <h1 class=\"display-5\" style=\"margin-top: 2rem\">Confirm your email</h1>\n" +
                "      <h4 style=\"margin-bottom: 0; margin-top: 2rem\">\n" +
                "        Hello "+name+" , you based Shibo enjoyer,\n" +
                "      </h4>\n" +
                "      <p class=\"h4\">\n" +
                "        Thank you for registering :3 . Please click on the below link to\n" +
                "        activate your account:\n" +
                "      </p>\n" +
                "      <div class=\"d-flex justify-content-around\">\n" +
                "        <div class=\"d-flex align-items-center justify-content-center\">\n" +
                "          <a\n" +
                "            style=\"\n" +
                "              background-color: #284b63;\n" +
                "              color: white;\n" +
                "              padding: 5px;\n" +
                "              border-radius: 4px;\n" +
                "              display: block;\n" +
                "              font-size: 20px;\n" +
                "            \"\n" +
                "            href=\""+link+"\"\n" +
                "          >\n" +
                "            activate your account\n" +
                "          </a>\n" +
                "          <img\n" +
                "            style=\"width: 100px\"\n" +
                "            src=\"http://www.anime-evo.net/wp-content/uploads/2015/10/classroom_crisis-18.jpg\"\n" +
                "            alt=\"loli\"\n" +
                "          />\n" +
                "        </div>\n" +
                "        <h1 style=\"font-size: 20rem; margin: 0 0\">:3</h1>\n" +
                "      </div>\n" +
                "      <footer>\n" +
                "        <h3>\n" +
                "          Link will expire in 15 minutes.<br />\n" +
                "          See you in jail\n" +
                "          <a\n" +
                "            href=\"https://www.youtube.com/watch?v=YWcrfp_dXKM\"\n" +
                "            style=\"color: #153243\"\n" +
                "            target=\"_blank\"\n" +
                "            id=\"uwu\"\n" +
                "            >UwU</a\n" +
                "          >\n" +
                "        </h3>\n" +
                "      </footer>\n" +
                "    </div>\n" +
                "  </body>\n" +
                "</html>\n";
    }

}
