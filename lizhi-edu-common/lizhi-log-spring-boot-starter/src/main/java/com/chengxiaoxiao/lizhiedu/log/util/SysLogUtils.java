package com.chengxiaoxiao.lizhiedu.log.util;

import cn.hutool.core.util.URLUtil;
import cn.hutool.http.HttpUtil;
import com.chengxiaoxiao.lizhiedu.common.core.util.IpUtil;
import com.chengxiaoxiao.lizhiedu.log.annotation.SysLog;
import com.chengxiaoxiao.lizhiedu.log.entity.LogTypeEnum;
import com.chengxiaoxiao.lizhiedu.log.entity.SysLogModel;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 系统日志工具类
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2020/10/29
 */
@UtilityClass
public class SysLogUtils {

    /**
     * 获取正常的日志信息
     *
     * @param sysLog          日志注解对象
     * @param applicationName 当前应用名称
     * @return {SysLogModel}
     */
    @SneakyThrows
    public SysLogModel getSysLog(SysLog sysLog, String applicationName) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects
                .requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String header = request.getHeader("user-agent");

        SysLogModel sysLogVo = new SysLogModel();
        if (sysLog != null) {
            sysLogVo.setTitle(sysLog.value());
            sysLogVo.setOperateType(sysLog.operateType().getType());
        }
        sysLogVo.setCreateBy(getUsername());
        sysLogVo.setType(LogTypeEnum.NORMAL.getType());
        sysLogVo.setIp(IpUtil.getIpAddr(request));
        sysLogVo.setRequestUri(URLUtil.getPath(request.getRequestURI()));
        sysLogVo.setMethod(request.getMethod());
        sysLogVo.setUserAgent(header);
        sysLogVo.setParams(HttpUtil.toParams(request.getParameterMap()));
        UserAgent userAgent = UserAgent.parseUserAgentString(header);
        sysLogVo.setBrowser(userAgent.getBrowser().toString());
        sysLogVo.setOperateSystem(userAgent.getOperatingSystem().toString());
        sysLogVo.setCreateTime(LocalDateTime.now());
        sysLogVo.setModuleName(applicationName);
        return sysLogVo;
    }

    /**
     * 获取用户名称
     *
     * @return username
     */
    private String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }
        return authentication.getName();
    }

    /**
     * 获取HTTP日志信息
     *
     * @param applicationName 应用名称
     * @return 返回HTTP日志信息
     */
    public static SysLogModel getHttpLog(String applicationName) {
        return getSysLog(null, applicationName);
    }
}
