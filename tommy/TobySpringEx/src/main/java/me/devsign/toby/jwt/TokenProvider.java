package me.devsign.toby.jwt;

import lombok.Value;
import org.springframework.beans.factory.InitializingBean;

import java.security.Key;

public class TokenProvider implements InitializingBean {
    private static final String AUTHORITIES_KEY = "auth";
//    private final String secret;
//    private final long tokenValidityInMilliseconds;

    private Key key;

//    public TokenProvider(@Value("${jwt}") String secret,
//                         @Value("${jwt}") long tokenValidityInMilliseconds) {
//        this.secret = secret;
//        this.tokenValidityInMilliseconds = tokenValidityInMilliseconds;
//    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
