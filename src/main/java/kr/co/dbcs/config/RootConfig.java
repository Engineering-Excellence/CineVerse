package kr.co.dbcs.config;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.sql.DataSource;

@Configuration
@EnableAspectJAutoProxy
@EnableTransactionManagement
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@PropertySource("classpath:db.properties")
@MapperScan(basePackages = "kr.co.dbcs.mapper")
@ComponentScan(basePackages = {"kr.co.dbcs.model", "kr.co.dbcs.service", "kr.co.dbcs.aop" })
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
        dataSource.setMinIdle(3); // 최소 유휴 연결 수
        dataSource.setMaxTotal(5); // 최대 풀 크기
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

        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public SqlSession sqlSession(@Autowired SqlSessionFactory sqlSessionFactory) {

        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public PlatformTransactionManager transactionManager(@Autowired DataSource dataSource) {

        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");
        resolver.setMaxUploadSize(1024 * 1024 * 5); // 업로드 용량제한: 5MiB
        return resolver;
    }
}
