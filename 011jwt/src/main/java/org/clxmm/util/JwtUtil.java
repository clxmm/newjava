package org.clxmm.util;

import io.jsonwebtoken.*;
import org.clxmm.entity.Member;

import javax.print.attribute.standard.MediaSize;
import java.security.Signature;
import java.util.Date;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/2/8 4:31 下午
 */
public class JwtUtil {
    private static final long EXPIRE = 1000* 30;
    private static final String APP_SECRET = "123456";

    public static String genJwt(String id, String nickName, String avatar) {


        JwtBuilder builder = Jwts.builder();

        // 第一部分 jwt 头
        builder.setHeaderParam("alg", "HS256");  // 签名算法
        builder.setHeaderParam("type", "JWT");  // 令牌类型

        // 第二部分： 有效载荷
        // 默认字段
        builder.setId("1");
        builder.setSubject("guli-service");
        builder.setIssuedAt(new Date());
        builder.setExpiration(new Date(System.currentTimeMillis() + EXPIRE));
        // 私有字段
        builder.claim("myId",id);
        builder.claim("nickName",nickName);
        builder.claim("avatar",avatar);


        // 第三部分  签名哈希
        builder.signWith(SignatureAlgorithm.HS256,APP_SECRET);


        String token = builder.compact();

        return  token;

    }

    public static Claims checkToken (String jwt) {
        JwtParser parser = Jwts.parser();

        Jws<Claims> claimsJws = parser.setSigningKey(APP_SECRET).parseClaimsJws(jwt);

        Claims body = claimsJws.getBody();

        return body;

    }


    public static void main(String[] args) {
        Member member = new Member();

        member.setId("1");
        member.setNickName("test");
        member.setAvatar("头像");

        String jwt = genJwt(member.getId(), member.getNickName(), member.getAvatar());
        System.out.println(jwt);


        Claims claims = checkToken(
        "eyJhbGciOiJIUzI1NiIsInR5cGUiOiJKV1QifQ.eyJqdGkiOiIxIiwic3ViIjoiZ3VsaS1zZXJ2aWNlIiwiaWF0IjoxNjEyNzc1Mjc3LCJleHAiOjE2MTI3NzUzMDcsIm15SWQiOiIxIiwibmlja05hbWUiOiJ0ZXN0IiwiYXZhdGFyIjoi5aS05YOPIn0.LUCrINdohvetWb7rOZB2mDPR7KyCTxQXwmDQeDAilq0"
        );
        Object id = claims.get("myId");
        String nickName = (String) claims.get("nickName");
        Object avatar = claims.get("avatar");
        System.out.println(id+"" +nickName+""+avatar);


    }


}
