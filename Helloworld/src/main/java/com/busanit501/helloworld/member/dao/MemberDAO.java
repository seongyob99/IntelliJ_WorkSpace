package com.busanit501.helloworld.member.dao;

import com.busanit501.helloworld.jdbcex.dao.ConnectionUtil;
import com.busanit501.helloworld.member.vo.MemberVO;
import lombok.Cleanup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {

    //1. insert
    public void insert(MemberVO memberVO) throws SQLException {
        String sql = "INSERT INTO tbl_member (mid, mpw, name, dueDate, finished) VALUES (?, ?, ?, ?, ?)";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, memberVO.getMid());
        preparedStatement.setString(2, memberVO.getMpw());
        preparedStatement.setString(3, memberVO.getName());
        preparedStatement.setDate(4, memberVO.getDueDate() != null ? Date.valueOf(memberVO.getDueDate()) : null);
        preparedStatement.setBoolean(5, memberVO.isFinished());
        preparedStatement.executeUpdate();
    }

    //2. select all (전체 조회)
    public List<MemberVO> selectAll() throws SQLException {
        String sql = "select * from tbl_member";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
        List<MemberVO> list = new ArrayList<>();
        while (resultSet.next()) {
            MemberVO memberVO = MemberVO.builder()
                    .mid(resultSet.getString("mid"))  // mid
                    .mpw(resultSet.getString("mpw"))  // mpw
                    .name(resultSet.getString("name"))
                    .dueDate(resultSet.getDate("dueDate") != null ? resultSet.getDate("dueDate").toLocalDate() : null)
                    .finished(resultSet.getBoolean("finished"))
                    .uuid(resultSet.getString("uuid"))
                    .build();
            list.add(memberVO);
        }
        return list;
    }

    //3. select one (단일 조회)
    public MemberVO selectOne(String mid) throws SQLException {
        String sql = "select * from tbl_member where mid = ?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, mid); // mid를 기준으로 조회
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        MemberVO memberVO = MemberVO.builder()
                .mid(resultSet.getString("mid"))  // mid
                .mpw(resultSet.getString("mpw"))  // mpw
                .name(resultSet.getString("name"))
                .dueDate(resultSet.getDate("dueDate") != null ? resultSet.getDate("dueDate").toLocalDate() : null)
                .finished(resultSet.getBoolean("finished"))
                .uuid(resultSet.getString("uuid"))
                .build();
        return memberVO;
    }

    //4. update (수정)
    public void updateOne(MemberVO memberVO) throws SQLException {
        String sql = "update tbl_member set mpw=?, name=?, dueDate=?, finished=? where mid=?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, memberVO.getMpw()); // mpw
        preparedStatement.setString(2, memberVO.getName());
        preparedStatement.setDate(3, memberVO.getDueDate() != null ? Date.valueOf(memberVO.getDueDate()) : null);
        preparedStatement.setBoolean(4, memberVO.isFinished());
        preparedStatement.setString(5, memberVO.getMid()); // mid (PK)
        preparedStatement.executeUpdate();
    }

    //5. delete (삭제)
    public void deleteMember(String mid) throws SQLException {
        String sql = "delete from tbl_member where mid = ?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, mid); // mid (PK)
        preparedStatement.executeUpdate();
    }

    //6. 로그인 (mid와 mpw로 회원 조회)
    public MemberVO getMemberWithMpw(String mid, String mpw) throws SQLException {
        String query = "select * from tbl_member where mid=? and mpw=?";
        MemberVO memberVO = null;

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, mid); // mid
        preparedStatement.setString(2, mpw); // mpw
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            memberVO = MemberVO.builder()
                    .mid(resultSet.getString("mid"))  // mid
                    .mpw(resultSet.getString("mpw"))  // mpw
                    .name(resultSet.getString("name"))
                    .dueDate(resultSet.getDate("dueDate") != null ? resultSet.getDate("dueDate").toLocalDate() : null)
                    .finished(resultSet.getBoolean("finished"))
                    .build();
        }

        return memberVO;
    }

    public void updateUuid(String mid, String uuid) throws SQLException {
        String query = "UPDATE tbl_member SET uuid=? WHERE mid=?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, uuid);
        preparedStatement.setString(2, mid);
        preparedStatement.executeUpdate();
    }


    public MemberVO getMemberWithUuid(String uuid) throws SQLException {
        String query = "select * from tbl_member where uuid=?";
        MemberVO memberVO = null;

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, uuid); // mid
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
            memberVO = MemberVO.builder()
                    .mid(resultSet.getString("mid"))  // mid
                    .mpw(resultSet.getString("mpw"))  // mpw
                    .name(resultSet.getString("name"))
                    .dueDate(resultSet.getDate("dueDate") != null ? resultSet.getDate("dueDate").toLocalDate() : null)
                    .finished(resultSet.getBoolean("finished"))
                    .uuid(resultSet.getString("uuid"))
                    .build();

        return memberVO;
    }


}
