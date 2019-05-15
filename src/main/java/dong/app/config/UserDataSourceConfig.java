package dong.app.config;


import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = UserDataSourceConfig.PACKAGE,sqlSessionFactoryRef = "userSqlSessionFactory")
public class UserDataSourceConfig {

  static final String PACKAGE = "dong.app.user.dao";
  static final String MAPPER_LOCATION = "classpath:mapper/user/*.xml";

  @Value("${user.datasource.url}")
  private String url;

  @Value("${user.datasource.username}")
  private String user;

  @Value("${user.datasource.password}")
  private String password;

  @Value("${user.datasource.driverClassName}")
  private String driverClass;

  @Bean(name = "userDataSource")
  @Primary
  public DataSource userDataSource() {
    DruidDataSource dataSource = new DruidDataSource();
    dataSource.setDriverClassName(driverClass);
    dataSource.setUrl(url);
    dataSource.setUsername(user);
    dataSource.setPassword(password);
    return dataSource;
  }

  @Bean(name = "userTransactionManager")
  @Primary
  public DataSourceTransactionManager userTransactionManager() {
    return new DataSourceTransactionManager(userDataSource());
  }

  @Bean(name = "userSqlSessionFactory")
  @Primary
  public SqlSessionFactory userSqlSessionFactory(@Qualifier("userDataSource") DataSource uqierpDataSource)
          throws Exception {
    final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
    sessionFactory.setDataSource(uqierpDataSource);
    sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
            .getResources(UserDataSourceConfig.MAPPER_LOCATION));
    return sessionFactory.getObject();
  }

}
