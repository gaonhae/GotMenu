package org.TUK.gotMenu.service;

import lombok.RequiredArgsConstructor;
import org.TUK.gotMenu.SessionCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Service
public class SecurityService
{
    @Autowired
    final HttpServletRequest request;

    public boolean isLogin()
    {
        if(request.getSession().getAttribute(SessionCode.USER_NO.toString()).equals(null))
            return false;
        else
            return true;

    }

    // 현재 세션에 저장되어 있는 유저 번호를 반환
    public Integer getUserNo()
    {
        return (Integer)request.getSession().getAttribute(SessionCode.USER_NO.toString());
    }

    // 인수로 받은 번호와 현재 유저 번호가 같다면 true
    public boolean isSameUser(Integer userNo)
    {
        Integer sNo = getUserNo();
        if(sNo == null) return false;
        return (sNo == userNo);
    }

}
