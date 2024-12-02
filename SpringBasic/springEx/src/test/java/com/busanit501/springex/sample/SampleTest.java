package com.busanit501.springex.sample;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Log4j2
@ExtendWith(SpringExtension.class) //JUnit5 테스트 설정
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class SampleTest {

    @Autowired // 시스템에 등록된 빈 연결
    private SampleService sampleService;

    @Autowired
    private DataSource dataSource;

    @Test
    public void testService1() {
        log.info("testService1 : " + sampleService);
        Assertions.assertNotNull(sampleService);
    }

    @Test
    public void testConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        log.info("testConnection : " + connection);
        Assertions.assertNotNull(connection);
        connection.close();
    }
}
