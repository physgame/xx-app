package dong.app;

import dong.app.bus.dao.DemoDao;
import dong.app.bus.domain.Demo;
import dong.app.user.dao.UserDao;
import dong.app.user.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class XxAppApplicationTests {

  @Resource
  private UserDao userDao;
  @Resource
  private DemoDao demoDao;

  @Test
  public void getUserById() {

    User u = userDao.getById((long) 1);
    System.out.println(u.toString());

  }

  @Test
  public void getDemoById() {

    Demo d = demoDao.getById((long) 1);
    System.out.println(d.toString());

  }

  @Test
  public void getDemoAndUser() {

    Demo d = demoDao.getById((long) 1);
    System.out.println("----------------------------------------");
    System.out.println(d.toString());
    System.out.println("----------------------------------------");
    System.out.println("----------------------------------------");

    User u = userDao.getById((long) 1);
    System.out.println("----------------------------------------");
    System.out.println(u.toString());
    System.out.println("----------------------------------------");
    System.out.println("----------------------------------------");


  }

}
