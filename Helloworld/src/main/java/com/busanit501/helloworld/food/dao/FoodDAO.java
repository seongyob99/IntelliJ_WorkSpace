package com.busanit501.helloworld.food.dao;

import com.busanit501.helloworld.food.dto.FoodVO;
import com.busanit501.helloworld.jdbcex.dao.ConnectionUtil;
import lombok.Cleanup;

import java.sql.*;

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
