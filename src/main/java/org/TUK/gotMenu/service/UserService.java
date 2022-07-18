package org.TUK.gotMenu.service;

import lombok.RequiredArgsConstructor;
import org.TUK.gotMenu.SessionCode;
import org.TUK.gotMenu.entity.User;
import org.TUK.gotMenu.form.UserForm;
import org.TUK.gotMenu.repository.UserRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    public String login(UserForm user)
    {
        User entity = userRepository.findById(user.getId());
        // ID와 패스워드 검사
        if(entity == null) return "ID를 다시 입력해주세요.";
        if(!entity.getPassword().equals(user.getPassword())) return "비밀번호가 틀렸습니다.";

        // ID와 패스워드가 전부 맞았다면, 세션에 필요한 정보들을 담아준다.
        HttpSession session = request.getSession();
        session.setAttribute(SessionCode.USER_NO.toString(), entity.getUserNo());
        session.setAttribute(SessionCode.ID.toString(), entity.getId());
        session.setAttribute(SessionCode.PASSWORD.toString(), entity.getPassword());

        return "success";
    }

    public void logout()
    {
        // ID와 패스워드가 전부 맞았다면, 세션에 필요한 정보들을 담아준다.
        HttpSession session = request.getSession();
        session.setAttribute(SessionCode.USER_NO.toString(), null);
        session.setAttribute(SessionCode.ID.toString(), null);
        session.setAttribute(SessionCode.PASSWORD.toString(), null);

        return;
    }

    // 유저 정보를 JSON에 담아서 반환
    public JSONObject getUserDetail(Integer userNo)
    {
        JSONObject object = new JSONObject();
        User userEntity = userRepository.findByUserNo(userNo);
        if(userEntity == null) return object;

        // JSON 오브젝트에 유저 정보 담아주기
        object.put("userNo", userEntity.getUserNo());
        object.put("id", userEntity.getId());
        object.put("password", userEntity.getPassword());
        object.put("joinDate", userEntity.getJoinDate());
        object.put("userDetail", userEntity.getUserDetail());

        // 세션에 담긴 번호(현재 유저 번호)와 요청 받은 유저 번호가 같다면 true를 보낸다.
        boolean same = (Integer)request.getSession().getAttribute(SessionCode.USER_NO.toString()) == userNo;
        object.put("same", same);

        return object;

    }
    // 유저 정보를 받아 업데이트
    public String update(UserForm user)
    {
        User entity = userRepository.findByUserNo(user.getUserNo());
        entity.setId(user.getId());
        entity.setPassword(user.getPassword());
        entity.setUserDetail(user.getUserDetail());

        userRepository.save(entity);

        return "success";
    }

    public void delete(int userNo)
    {
       User user = userRepository.findByUserNo(userNo);
       userRepository.delete(user);

    }

}
