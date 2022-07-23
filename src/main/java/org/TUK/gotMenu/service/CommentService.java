package org.TUK.gotMenu.service;

import lombok.RequiredArgsConstructor;
import org.TUK.gotMenu.entity.Comment;
import org.TUK.gotMenu.form.CommentForm;
import org.TUK.gotMenu.repository.CommentRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public String read(int menuNo, int pageNo)
    {
        JSONObject objects = new JSONObject();

        Pageable pageable = PageRequest.of(pageNo, 5);
        Page<Comment> page = commentRepository.findByMenuNo(menuNo, pageable);

        // 댓글 페이지를 넘길 수 있는 페이지 버튼 설정.
        int btnStart = pageNo - 2;
        int btnEnd = pageNo + 3;
        if(btnStart < 0) btnStart = 0;
        if(page.getTotalPages() < btnEnd) btnEnd = page.getTotalPages();


        // JSON 배열에 댓글에 대한 정보를 담아준다.
        JSONArray array = new JSONArray();
        for(Comment c : page)
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


        objects.put("btnStart", btnStart);
        objects.put("btnEnd", btnEnd);
        objects.put("pageNo", pageNo);
        objects.put("array", array);

        return objects.toString();
    }

}
