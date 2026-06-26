package com.example.demo.Utility;

import com.example.demo.Beans.User;
import com.example.demo.Repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
public class OauthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private JwtUtility jwtUtility;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        OAuth2User user = token.getPrincipal();
        String name=user.getAttribute("name");
        String email=user.getAttribute("email");
        String profile=user.getAttribute("picture");
        User dbUser = userRepository.findByEmail(email);
        if(dbUser==null){
            dbUser = new User();
            dbUser.setEmail(email);
            dbUser.setUsername(name);
            dbUser.setProfileUrl(profile);
            dbUser.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
            userRepository.save(dbUser);
        }
        String jwt = jwtUtility.generateToken(dbUser);
        response.sendRedirect(
                "http://localhost:3000/login-success?token=" + jwt);
    }
}
