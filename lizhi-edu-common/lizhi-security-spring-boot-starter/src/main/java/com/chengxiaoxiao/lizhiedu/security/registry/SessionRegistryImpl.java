package com.chengxiaoxiao.lizhiedu.security.registry;

import org.springframework.context.ApplicationListener;
import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;

import java.util.List;

/**
 * 用户上线下线监控，操作删除
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2020/6/19 17:12
 */
public class SessionRegistryImpl implements SessionRegistry, ApplicationListener<SessionDestroyedEvent> {
    @Override
    public void onApplicationEvent(SessionDestroyedEvent sessionDestroyedEvent) {
        removeSessionInformation(sessionDestroyedEvent.getId());
    }

    @Override
    public List<Object> getAllPrincipals() {
        return null;
    }

    @Override
    public List<SessionInformation> getAllSessions(Object o, boolean b) {
        return null;
    }

    @Override
    public SessionInformation getSessionInformation(String s) {
        return null;
    }

    @Override
    public void refreshLastRequest(String s) {

    }

    @Override
    public void registerNewSession(String s, Object o) {

    }

    @Override
    public void removeSessionInformation(String s) {

    }
}
