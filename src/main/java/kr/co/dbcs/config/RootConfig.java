package kr.co.dbcs.config;

import lombok.AllArgsConstructor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
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
@EnableAspectJAutoProxy
@EnableTransactionManagement
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
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(env.getProperty("oracle.jdbc.driverClassName"));
        dataSource.setUrl(env.getProperty("oracle.jdbc.url"));
        dataSource.setUsername(env.getProperty("oracle.jdbc.username"));
        dataSource.setPassword(env.getProperty("oracle.jdbc.password"));

        // DBCP 상세 속성 설정
        dataSource.setMinIdle(10); // 최소 유휴 연결 수
        dataSource.setMaxTotal(20); // 최대 풀 크기
        dataSource.setMaxIdle(30); // 유휴 연결 유지 시간(밀리 초); 30000 밀리 초 (=30초)
        dataSource.setValidationQuery("SELECT 1 FROM DUAL");
        dataSource.setValidationQueryTimeout(10); // 검증 쿼리 타임아웃 (초)
        dataSource.setTestOnBorrow(true); // 대여 전에 검증 쿼리 수행
        dataSource.setTestOnReturn(true); // 반환 후에 검증 쿼리 수행
        dataSource.setTestWhileIdle(true); // 유휴 상태일 때 검증 쿼리 수행
        dataSource.setTimeBetweenEvictionRunsMillis(60000); // 검사 간격 (밀리 초); 60000 밀리 초 (=1분)
        dataSource.setNumTestsPerEvictionRun(3); // 각 검사마다 실행되는 검증 쿼리 수
        dataSource.setMinEvictableIdleTimeMillis(1800000); // 제거 될 수 있는 최소 유휴 시간 확인 (밀리 초); 1800000 밀리 초 (=30분)

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
