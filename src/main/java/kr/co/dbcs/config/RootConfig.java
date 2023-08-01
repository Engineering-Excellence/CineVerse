package kr.co.dbcs.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.AllArgsConstructor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableAspectJAutoProxy
@AllArgsConstructor(onConstructor_ = @Autowired)
@PropertySource("classpath:db.properties")
@MapperScan(basePackages = "kr.co.dbcs.mapper")
@ComponentScan(basePackages = {"kr.co.dbcs.model", "kr.co.dbcs.service", "kr.co.dbcs.util", "kr.co.dbcs.aop"})
public class RootConfig {
    // applicationContext.xml을 대신하는 java class

    private final Environment env;

    @Bean
    public DataSource dataSource() {
        // DBCP 설정

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(env.getProperty("oracle.jdbc.driverClassName"));
        dataSource.setJdbcUrl(env.getProperty("oracle.jdbc.url"));
        dataSource.setUsername(env.getProperty("oracle.jdbc.username"));
        dataSource.setPassword(env.getProperty("oracle.jdbc.password"));

        // HikariCP 상세 속성 설정
        dataSource.setMinimumIdle(10); // 최소 유휴 연결 수
        dataSource.setMaximumPoolSize(20); // 최대 풀 크기
        dataSource.setIdleTimeout(30000); // 유휴 연결 유지 시간(밀리 초); 30000 밀리 초 (=30초)
        dataSource.setConnectionTimeout(30000); // 연결 시간 제한(밀리 초); 30000 밀리 초 (=30초)

        // HikariCP의 keepAlive 설정
        dataSource.setConnectionTestQuery("SELECT 1 FROM DUAL");
        dataSource.setKeepaliveTime(60000); // KeepAlive 쿼리 실행 주기(밀리 초); 60000 밀리 초 (60초) 예시

        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(@Autowired DataSource dataSource) throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);

        // SQL Mapping을 xml 파일로 할 경우 필요
//        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));

        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager(@Autowired DataSource dataSource) {

        return new DataSourceTransactionManager(dataSource);
    }
}
