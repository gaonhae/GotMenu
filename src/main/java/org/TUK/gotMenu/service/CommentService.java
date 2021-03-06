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


        // JSONArray에 댓글에 대한 정보를 담아준다.
        JSONArray array = new JSONArray();
        for(Comment c : page)
        {
            JSONObject cJson = new JSONObject();
            cJson.put("commentNo", c.getCommentNo());
            cJson.put("writerNo", c.getWriter().getUserNo());
            cJson.put("writerId", c.getWriter().getId());
            cJson.put("content", c.getContent());
            cJson.put("resistDate", c.getResistDate());
            cJson.put("isSameUser", securityService.isSameUser(c.getWriter().getUserNo()));

            array.put(cJson);
        }

        // 버튼 정보 / 댓글 리스트를 JSON에 넣어주기
        objects.put("btnStart", btnStart);
        objects.put("btnEnd", btnEnd);
        objects.put("pageNo", pageNo);
        objects.put("array", array);

        return objects.toString();
    }

    public String update(int commentNo, String content)
    {
        Comment comment = commentRepository.findByCommentNo(commentNo);
        comment.setContent(content);

        try
        {
            commentRepository.save(comment);
            return "success";
        }
        catch(RuntimeException e)
        {
            return "수정에 실패했습니다.";
        }
    }

    public String delete(int commentNo)
    {
        Comment comment = commentRepository.findByCommentNo(commentNo);
        try
        {
            commentRepository.delete(comment);
            return "success";
        }
        catch(RuntimeException e)
        {
            return "댓글 삭제 실패";
        }
    }

}
