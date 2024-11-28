package com.busanit501.helloworld.food.dao;

import com.busanit501.helloworld.food.dto.FoodVO;
import com.busanit501.helloworld.jdbcex.dao.ConnectionUtil;
import com.busanit501.helloworld.jdbcex.dto.TodoVO;
import lombok.Cleanup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FoodDAO {

    // Food 데이터 삽입
    public void insert(FoodVO foodVO) throws SQLException {
        String sql = "INSERT INTO tbl_food (title, dueDate, finished) " +
                "VALUES (?, ?, ?)";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, foodVO.getTitle());
        preparedStatement.setDate(2, Date.valueOf(foodVO.getDueDate()));
        preparedStatement.setBoolean(3, foodVO.isFinished());
        preparedStatement.executeUpdate();
    }

    //select DB에서 전체 조회
    public List<FoodVO> selectAll() throws SQLException {
        String sql = "select * from tbl_food" ;
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
        //넘어온 데이터를 임시로 보관할 리스트 인스턴스 만들고
        //반복문 통해서, 넘어온 각행을 리스트에 요소로 하나씩 담기
        List<FoodVO> list = new ArrayList<>();
        while (resultSet.next()) {
            FoodVO foodVO = FoodVO.builder()
                    .fno(resultSet.getLong("fno"))
                    .title(resultSet.getString("title"))
                    .dueDate(resultSet.getDate("dueDate").toLocalDate())
                    .finished(resultSet.getBoolean("finished"))
                    .build();
            list.add(foodVO);
        }
        return list;


    }

    public FoodVO selectOne(Long fno) throws SQLException {
        String sql = "select * from tbl_food where fno = ?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, fno);
        // 하나만 받아온 상태,
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
        // 임시 FoodVO , 인스턴스 만들어서, 한행의 각 컬럼 4개를 담기.
        //0행에서 1행으로 조회
        resultSet.next();
        FoodVO foodVO = FoodVO.builder()
                //setTno
                .fno(resultSet.getLong("fno"))
                // setTitle
                .title(resultSet.getString("title"))
                //setDueDate
                .dueDate(resultSet.getDate("dueDate").toLocalDate())
                //setFinished
                .finished(resultSet.getBoolean("finished"))
                .build();

        return foodVO;
    }

    public void updateOne(FoodVO foodVO) throws SQLException {
        String sql = " update tbl_food set title=?, dueDate=?, finished=?" +
                " where fno=?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, foodVO.getTitle());
        preparedStatement.setDate(2, Date.valueOf(foodVO.getDueDate()));
        preparedStatement.setBoolean(3,foodVO.isFinished());
        preparedStatement.setLong(4,foodVO.getFno());
        preparedStatement.executeUpdate();

    }

    public void deleteFood(Long fno) throws SQLException {
        String sql = "delete from tbl_food where fno = ?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, fno);
        preparedStatement.executeUpdate();
    }
//----------------------------------------------------------------------------------------------------------------------
    // 현재 시간 조회 (방법 1)
    public String getTime() {
        String now = null;
        try (Connection connection = ConnectionUtil.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT NOW()");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                now = resultSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return now;
    }

    // 현재 시간 조회 (방법 2 - @Cleanup 사용)
    public String getTime2() throws SQLException {
        String now = null;
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement("SELECT NOW()");
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            now = resultSet.getString(1);
        }
        return now;
    }




}
