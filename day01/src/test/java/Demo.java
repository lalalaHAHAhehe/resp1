import com.show.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class Demo {

    private SqlSession session;
    @Before
    public void init() throws IOException {
        InputStream is = Resources.getResourceAsStream("sqlConfig.xml");
        SqlSessionFactoryBuilder factoryBuilder=new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = factoryBuilder.build(is);
        session = factory.openSession();
    }

    @After
    public void destroy(){
        session.commit();
        session.close();
    }

    @Test
    public void findAll(){
        List<User> findAll = session.selectList("one.findAll");
        for (User user : findAll) {
            System.out.println(user);
        }
    }

    @Test
    public void findUserById(){
        User o = session.selectOne("one.findUserById", 48);
        System.out.println(o);
    }

    @Test
    public void insert(){
        User user = new User();
        user.setUsername("1234");
        user.setBirthday(new Date());
        user.setSex("1");
        user.setAddress("1234");
        session.insert("one.insert",user);
    }

    @Test
    public void update(){
        session.update("one.update","666");
    }

    @Test
    public void delete(){
        session.delete("one.delete",51);
    }
}
