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
@MapperScan(basePackages = BusDataSourceConfig.PACKAGE,sqlSessionFactoryRef = "busSqlSessionFactory")
public class BusDataSourceConfig {

  static final String PACKAGE = "dong.app.bus.dao";
  static final String MAPPER_LOCATION = "classpath:mapper/bus/*.xml";

  @Value("${bus.datasource.url}")
  private String url;

  @Value("${bus.datasource.username}")
  private String user;

  @Value("${bus.datasource.password}")
  private String password;

  @Value("${bus.datasource.driverClassName}")
  private String driverClass;

  @Bean(name = "busDataSource")
  public DataSource userDataSource() {
    DruidDataSource dataSource = new DruidDataSource();
    dataSource.setDriverClassName(driverClass);
    dataSource.setUrl(url);
    dataSource.setUsername(user);
    dataSource.setPassword(password);
    return dataSource;
  }

  @Bean(name = "busTransactionManager")
  public DataSourceTransactionManager userTransactionManager() {
    return new DataSourceTransactionManager(userDataSource());
  }
  
  @Bean(name = "busSqlSessionFactory")
  public SqlSessionFactory userSqlSessionFactory(@Qualifier("busDataSource") DataSource uqierpDataSource)
          throws Exception {
    final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
    sessionFactory.setDataSource(uqierpDataSource);
    sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
            .getResources(BusDataSourceConfig.MAPPER_LOCATION));
    return sessionFactory.getObject();
  }

}
