package com.chengxiaoxiao.lizhiedu.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

/**
 * Jwt工具类
 *
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/1/31 8:32 下午
 * @Description:
 */
@Data
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.tokenHeader}")
    public String tokenHeader;

    @Value("${jwt.tokenPrefix}")
    private String tokenPrefix;

    @Value("${jwt.expiration}")
    private Integer expiration;


    /**
     * 生成Jwt Token
     *
     * @param id          用户Id
     * @param subject     用户名
     * @param authorities 用户所拥有的的权限
     * @return token
     */
    public String createJWT(String id, String subject, String authorities) {
        // 登陆成功生成JWT
        String token = Jwts.builder()
                // 用户ID
                .setId(id)
                // 主题-暂存用户名
                .setSubject(subject)
                // 签发时间
                .setIssuedAt(new Date())
                // 签发者
                .setIssuer("chengxiaoxiao")
                // 自定义属性 放入用户拥有权限
                .claim("authorities", authorities)
                // 失效时间
                .setExpiration(new Date(System.currentTimeMillis() + (expiration * 1000)))
                // 签名算法和密钥
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
        return token;
    }

    /**
     * 解析JWT
     *
     * @param jwtStr
     * @return
     */
    public Claims parseJWT(String jwtStr) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(jwtStr)
                .getBody();
    }

}
