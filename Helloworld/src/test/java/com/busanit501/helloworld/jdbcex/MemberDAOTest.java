package com.busanit501.helloworld.jdbcex;

import com.busanit501.helloworld.member.dao.MemberDAO;
import com.busanit501.helloworld.member.vo.MemberVO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Log4j2
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
                .mid("exampleId")                // 아이디 추가
                .mpw("examplePassword")          // 비밀번호 추가
                .name("예시이름")
                .dueDate(LocalDate.now())
                .finished(false)
                .build();

        memberDAO.insert(memberVO); // 데이터 삽입
        System.out.println("데이터 삽입 완료: " + memberVO);
    }

    // 전체 리스트 조회 테스트
    @Test
    public void testList() throws SQLException {
        List<MemberVO> list = memberDAO.selectAll();
        list.forEach(vo -> System.out.println(vo));
    }

    // 특정 멤버 조회 테스트
    @Test
    public void getOneTest() throws SQLException {
        String mid = "exampleId"; // mid로 변경
        MemberVO memberVO = memberDAO.selectOne(mid);
        System.out.println(memberVO);
    }

    // 데이터 삭제 테스트
    @Test
    public void deleteTest() throws SQLException {
        String mid = "exampleId"; // mid로 변경
        memberDAO.deleteMember(mid);
        System.out.println("데이터 삭제 완료: " + mid);
    }

    // 데이터 업데이트 테스트
    @Test
    public void updateTest() throws SQLException {
        MemberVO memberVO = MemberVO.builder()
                .mid("exampleId")               // mid 추가
                .name("이름 수정 테스트 중")
                .mpw("newPassword")             // mpw 추가
                .finished(true)
                .dueDate(LocalDate.now())
                .build();
        memberDAO.updateOne(memberVO);
        System.out.println("데이터 업데이트 완료: " + memberVO);
    }

    // 로그인용 테스트
    @Test
    public void getMemberWithMpwTest() throws SQLException {
        String mid = "exampleId";   // 테스트용 아이디
        String mpw = "examplePassword"; // 테스트용 비밀번호
        MemberVO memberVO = memberDAO.getMemberWithMpw(mid, mpw);
        log.info("memberVO 조회 확인: " + memberVO);
    }

    @Test
    public void updateUuidTest() throws SQLException {
        String uuid = UUID.randomUUID().toString();
        log.info("uuid: " + uuid);
        memberDAO.updateUuid("asy",uuid);
    }

    @Test
    public void getMemberWithUuidTest() throws SQLException {
        MemberVO memberVO = memberDAO.getMemberWithUuid("d707bfc0-ab81-48dd-9d17-0a640f9931e7");
        log.info("memberVO : " + memberVO);
    }
}
