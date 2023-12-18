package com.kevin.applicaton;

import org.itstack.naive.chat.ui.view.chat.IChatEvent;
import org.itstack.naive.chat.ui.view.chat.IChatMethod;
import org.itstack.naive.chat.ui.view.login.ILoginEvent;
import org.itstack.naive.chat.ui.view.login.ILoginMethod;

/**
 * @author wang
 * @create 2023-12-18-21:55
 */
public class UIService {
    private ILoginMethod login;

    private IChatMethod chatMethod;

    public ILoginMethod getLoginEvent() {
        return login;
    }

    public void setLoginEvent(ILoginMethod loginEvent) {
        this.login = loginEvent;
    }

    public IChatMethod getChatMethod() {
        return chatMethod;
    }

    public void setChatMethod(IChatMethod chatEvent) {
        this.chatMethod = chatEvent;
    }
}
