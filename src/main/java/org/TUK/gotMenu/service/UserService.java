package org.TUK.gotMenu.service;

import lombok.RequiredArgsConstructor;
import org.TUK.gotMenu.form.UserForm;
import org.TUK.gotMenu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Service
public class UserService
{
    @Autowired
    HttpServletRequest request;
    @Autowired
    UserRepository userRepository;

    public void create(UserForm user)
    {
        // 가입일을 정해진 포멧으로 저장해줘야 함.
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String nowStr = now.format(formatter);
        user.setJoinDate(nowStr);

        userRepository.save(user.toEntity());

        return;
    }


}
