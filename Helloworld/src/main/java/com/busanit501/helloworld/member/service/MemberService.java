package com.busanit501.helloworld.member.service;

import com.busanit501.helloworld.member.dao.MemberDAO;
import com.busanit501.helloworld.member.dto.MemberDTO;
import com.busanit501.helloworld.member.vo.MemberVO;
import com.busanit501.helloworld.jdbcex.util.MapperUtil;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

// 설정1
@Log4j2
public enum MemberService {
    INSTANCE;

    private MemberDAO memberDAO;
    private ModelMapper modelMapper;

    MemberService() {
        memberDAO = new MemberDAO();
        modelMapper = MapperUtil.INSTANCE.get();
    }

    // 로그인 확인용.
    public MemberDTO login(String mid, String mpw) throws SQLException {
        MemberVO memberVO = memberDAO.getMemberWithMpw(mid, mpw);
        if (memberVO == null) {
            throw new SQLException("Invalid credentials: User not found or incorrect password.");
        }
        MemberDTO memberDTO = modelMapper.map(memberVO, MemberDTO.class);
        return memberDTO;
    }

    // register
    public void register(MemberDTO memberDTO) throws SQLException {
        MemberVO memberVO = modelMapper.map(memberDTO, MemberVO.class);
        log.info("memberVO : " + memberVO);
        memberDAO.insert(memberVO);
    }

    // 전체 조회
    public List<MemberDTO> listAll() throws SQLException {
        List<MemberVO> voList = memberDAO.selectAll();
        log.info("voList : " + voList);
        List<MemberDTO> dtoList = voList.stream()
                .map(vo -> modelMapper.map(vo, MemberDTO.class))
                .collect(Collectors.toList());
        return dtoList;
    }

    // 하나 조회
    public MemberDTO get(String mid) throws SQLException {
        log.info("mid : " + mid);
        ///  디비에서 하나 조회 결과 받았음.
        MemberVO memberVO = memberDAO.selectOne(mid);
        if (memberVO == null) {
            throw new SQLException("No member found with mid: " + mid);
        }
        // VO -> DTO 변환 작업.
        MemberDTO memberDTO = modelMapper.map(memberVO, MemberDTO.class);
        return memberDTO;
    }

    // 수정
    public void update(MemberDTO memberDTO) throws SQLException {
        log.info("memberDTO : " + memberDTO);
        MemberVO memberVO = modelMapper.map(memberDTO, MemberVO.class);
        memberDAO.updateOne(memberVO);
    }

    // 삭제
    public void delete(String mid) throws SQLException {
        log.info("Deleting member with mid: " + mid);
        memberDAO.deleteMember(mid);
    }

    public void updateUuid(String mid, String uuid) throws SQLException {
        memberDAO.updateUuid(mid, uuid);
    }

    public MemberDTO getMemberWithUuidService(String uuid) throws SQLException {
        MemberVO memberVO = memberDAO.getMemberWithUuid(uuid);
        MemberDTO memberDTO = modelMapper.map(memberVO, MemberDTO.class);
        return memberDTO;
    }
}
