package org.TUK.gotMenu.service;

import lombok.RequiredArgsConstructor;
import org.TUK.gotMenu.SessionCode;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Service
public class SecurityService
{
    HttpServletRequest request;

    public boolean isLogin()
    {
        if(request.getSession().getAttribute(SessionCode.USER_NO.toString()).equals(null))
            return false;
        else
            return true;

    }

    public long getUserNo()
    {
        return (long)request.getSession().getAttribute(SessionCode.USER_NO.toString());
    }
}
