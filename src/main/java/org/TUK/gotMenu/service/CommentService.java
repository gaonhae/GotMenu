package org.TUK.gotMenu.service;

import lombok.RequiredArgsConstructor;
import org.TUK.gotMenu.entity.Comment;
import org.TUK.gotMenu.form.CommentForm;
import org.TUK.gotMenu.repository.CommentRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService
{
    @Autowired
    final CommentRepository commentRepository;
    @Autowired
    final SecurityService securityService;

    public String create(CommentForm comment)
    {
        Comment entity = comment.toEntity();

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String nowStr = now.format(formatter);
        entity.setResistDate(nowStr);

        try {
            commentRepository.save(entity);
            return "success";
        }
        catch (RuntimeException e) {
            return "DB 오류";
        }
    }

    public String read(int menuNo)
    {

        JSONObject objects = new JSONObject();
        List<Comment> commentList = commentRepository.findByMenuNo(menuNo);

        // 배열에 정보를 담아준다.
        JSONArray array = new JSONArray();
        for(Comment c : commentList)
        {
            JSONObject cJson = new JSONObject();
            cJson.put("commentNo", c.getCommentNo());
            cJson.put("writerNo", c.getUser().getUserNo());
            cJson.put("writerId", c.getUser().getId());
            cJson.put("content", c.getContent());
            cJson.put("resistDate", c.getResistDate());
            cJson.put("isSameUser", securityService.isSameUser(c.getUser().getUserNo()));

            array.put(cJson);
        }


        objects.put("length", commentList.size());
        objects.put("array", array);

        return objects.toString();
    }

}
