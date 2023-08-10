package kr.co.dbcs.config;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class, SecurityConfig.class})
@RequiredArgsConstructor
class DataSourceTest {

    private final ApplicationContext applicationContext;

    @Test
    void dataSourceBeanTest() {
        DataSource dataSource = applicationContext.getBean(DataSource.class);
        assertNotNull(dataSource, "dataSource == null");
    }

    @Test
    void sqlSessionBeanTest() {
        SqlSession sqlSession = applicationContext.getBean(SqlSession.class);
        assertNotNull(sqlSession, "sqlSession == null");
    }

    @Test
    void transactionManagerBeanTest() {
        PlatformTransactionManager platformTransactionManager = applicationContext.getBean(PlatformTransactionManager.class);
        assertNotNull(platformTransactionManager, "platformTransactionManager == null");
    }
}
