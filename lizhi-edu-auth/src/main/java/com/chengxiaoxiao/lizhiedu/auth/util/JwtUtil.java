package com.chengxiaoxiao.lizhiedu.auth.util;

import com.chengxiaoxiao.lizhiedu.security.config.SecurityProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Jwt工具类
 *
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/1/31 8:32 下午
 * @Description:
 */
@Data
@Component
public class JwtUtil {

    @Resource
    SecurityProperties securityProperties;

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
                .setExpiration(new Date(System.currentTimeMillis() + (securityProperties.getJwt().getExpiration() * 1000)))
                // 签名算法和密钥
                .signWith(SignatureAlgorithm.HS512, securityProperties.getJwt().getSecret())
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
                .setSigningKey(securityProperties.getJwt().getSecret())
                .parseClaimsJws(jwtStr)
                .getBody();
    }

}
