package com.busanit501.helloworld.jdbcex;


import com.busanit501.helloworld.member.dao.MemberDAO;
import com.busanit501.helloworld.member.vo.MemberVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MemberDAOTest {
    private MemberDAO memberDAO;

    @BeforeEach
    public void ready() {
        memberDAO = new MemberDAO();
    }

    // 데이터 삽입 테스트
    @Test
    public void insertTest() throws Exception {
        MemberVO memberVO = MemberVO.builder()
                .name("예시이름")
                .dueDate(LocalDate.now())
                .finished(false)
                .build();

        memberDAO.insert(memberVO); // 데이터 삽입
        System.out.println("데이터 삽입 완료: " + memberVO);
    }

    @Test
    public void testList() throws SQLException {
        List<MemberVO> list = memberDAO.selectAll();
        list.forEach(vo -> System.out.println(vo));
    }

    @Test
    public void getOneTest() throws SQLException {
        Long mno = 5L;
        MemberVO memberVO = memberDAO.selectOne(mno);
        System.out.println(memberVO);
    }


    @Test
    public void deleteTest() throws SQLException {
        Long mno = 2L;
        memberDAO.deleteMember(mno);
    }

    @Test
    public void updateTest() throws SQLException {
        MemberVO memberVO = MemberVO.builder()
                .mno(6L)
                .name("이름 수정 테스트 중")
                .finished(true)
                .dueDate(LocalDate.now())
                .build();
        memberDAO.updateOne(memberVO);
    }
}

