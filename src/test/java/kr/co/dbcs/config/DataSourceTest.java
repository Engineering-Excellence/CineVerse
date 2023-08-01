package kr.co.dbcs.config;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = RootConfig.class)
class DataSourceTest {

    @Autowired
    DataSource dataSource;

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Test
    void dataSourceNotNullTest() {
        
        assertNotNull(dataSource, "dataSource == null");
    }

    @Test
    void dbConnectionTest() {

        try (Connection connection = dataSource.getConnection()) {
            assertNotNull(connection, "connection == null");
        } catch (SQLException e) {
            throw new RuntimeException("DB Connection 실패", e);
        }
    }

    @Test
    void ConnectionTestQueryUsingMyBatis() {

        try (SqlSession session = sqlSessionFactory.openSession()) {
            Connection connection = session.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT 1 FROM DUAL");
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                assertTrue(resultSet.next(), "resultSet 결과 없음");
                int result = resultSet.getInt(1);
                assertEquals(1, result, "result != 1");
            } catch (SQLException e) {
                throw new RuntimeException("Connection Test Query 실행 중 오류 발생", e);
            }
        }
    }
}
