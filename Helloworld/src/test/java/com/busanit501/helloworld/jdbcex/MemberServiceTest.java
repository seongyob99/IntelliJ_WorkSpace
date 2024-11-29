package com.busanit501.helloworld.jdbcex;

import com.busanit501.helloworld.member.dto.MemberDTO;
import com.busanit501.helloworld.member.service.MemberService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Log4j2
public class MemberServiceTest {
    private MemberService memberService;

    @BeforeEach
    public void ready() {
        memberService = MemberService.INSTANCE;
    }

    // 추가 테스트
    @Test
    public void testInsert() throws SQLException {
        MemberDTO memberDTO = MemberDTO.builder()
                .mid("sampleUser")             // mid 추가
                .mpw("samplePass")             // mpw 추가
                .name("멤버 샘플")
                .dueDate(LocalDate.now())
                .finished(false)               // 완료 여부 추가
                .build();
        memberService.register(memberDTO);
        log.info("데이터 추가 완료: " + memberDTO);
    }

    // 전체 조회 테스트
    @Test
    public void testSelectAll() throws SQLException {
        List<MemberDTO> dtoList = memberService.listAll();
        for (MemberDTO memberDTO : dtoList) {
            log.info(memberDTO);
        }
    }

    // 특정 멤버 조회 테스트
    @Test
    public void testSelectOne() throws SQLException {
        String mid = "sampleUser";            // mid로 조회
        MemberDTO memberDTO = memberService.get(mid);
        log.info("하나 조회: " + memberDTO);
    }

    // 수정 테스트
    @Test
    public void testUpdateOne() throws SQLException {
        MemberDTO memberDTO = MemberDTO.builder()
                .mid("sampleUser")            // mid로 수정
                .mpw("newPass")               // 새 비밀번호 추가
                .name("이름 수정 테스트")
                .dueDate(LocalDate.now())
                .finished(true)               // 완료 여부 수정
                .build();
        memberService.update(memberDTO);
        log.info("데이터 수정 완료: " + memberDTO);
    }

    // 삭제 테스트
    @Test
    public void testDeleteOne() throws SQLException {
        String mid = "sampleUser";            // mid로 삭제
        memberService.delete(mid);
        log.info("데이터 삭제 완료: " + mid);
    }

    // 로그인 테스트
    @Test
    public void loginTest() throws SQLException {
        String mid = "asy";                   // 테스트용 mid
        String mpw = "1234";                  // 테스트용 mpw
        MemberDTO memberDTO = memberService.login(mid, mpw);
        log.info("로그인 테스트 결과: " + memberDTO);
    }

    @Test
    public void updateUuidTest() throws SQLException {
        String uuid = UUID.randomUUID().toString();
        memberService.updateUuid("asy",uuid);
    }

    @Test
    public void getMemberWithUuidSearch() throws SQLException {
      MemberDTO memberDTO = memberService.getMemberWithUuidService("a5e69bb7-23a1-4317-ac55-00dcb86c1a3c");
       log.info("memberDTO : " + memberDTO);
    }

}
