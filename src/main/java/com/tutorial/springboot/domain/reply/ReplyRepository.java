package com.tutorial.springboot.domain.reply;

import com.tutorial.springboot.domain.Reply;
import com.tutorial.springboot.dto.ReplySaveRequestDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {

    @Modifying
    @Query(value="INSERT INTO reply(userId, boardId, content, createDate) VALUES(?1, ?2, ?3, now())", nativeQuery = true)
    void mSave(int userId, int boardId, String content); // jdbc는 return 시 업데이트 된 행의 개수를 리턴해준다. (integer)
}
