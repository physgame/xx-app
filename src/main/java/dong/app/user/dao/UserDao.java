package dong.app.user.dao;

import dong.app.user.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {

  User getById(Long id);

}
