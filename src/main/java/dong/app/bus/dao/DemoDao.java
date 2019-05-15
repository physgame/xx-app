package dong.app.bus.dao;

import dong.app.bus.domain.Demo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DemoDao {

  Demo getById(Long id);

}
