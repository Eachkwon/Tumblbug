package com.example.tumblbug.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.tumblbug.security.UserDetailsImpl;

import java.util.Date;

public final class JwtTokenUtils {

    private static final int SEC = 1;
    private static final int MINUTE = 60 * SEC;
    private static final int HOUR = 60 * MINUTE;
    private static final int DAY = 24 * HOUR;

    // JWT 토큰의 유효기간: 1일 (단위: seconds)
    private static final int JWT_TOKEN_VALID_SEC = DAY;
    // JWT 토큰의 유효기간: 1일 (단위: milliseconds)
    private static final int JWT_TOKEN_VALID_MILLI_SEC = JWT_TOKEN_VALID_SEC * 1000;
    private static final int REFRESH_TOKEN_VALID_MILLI_SEC = JWT_TOKEN_VALID_MILLI_SEC*7;

    public static final String CLAIM_EXPIRED_DATE = "EXPIRED_DATE";
    public static final String CLAIM_USER_NAME = "USER_NAME";
    public static final String JWT_SECRET = "soone";

    public static String generateJwtToken(UserDetailsImpl userDetails) {
        String token = null;
        try {
            token = JWT.create()
                    .withClaim(CLAIM_USER_NAME, userDetails.getUsername())
                    .withClaim("NAME", userDetails.getName())
                    // 토큰 만료 일시 = 현재 시간 + 토큰 유효기간)
                    .withClaim(CLAIM_EXPIRED_DATE, new Date(System.currentTimeMillis() + JWT_TOKEN_VALID_MILLI_SEC))
                    .sign(generateAlgorithm());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return token;
    }

//    // JWT Refresh Token 생성
//    public static String generateRefreshToken() {
//        String token = null;
//        Date now = new Date();
//        try {
//            token = JWT.create()
//                    .withIssuedAt(now)
//                    .withExpiresAt(new Date(now.getTime()+REFRESH_TOKEN_VALID_MILLI_SEC))
//                    .sign(generateAlgorithm());
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//        return token;
//    }

    private static Algorithm generateAlgorithm() {
        return Algorithm.HMAC256(JWT_SECRET);
    }
}
