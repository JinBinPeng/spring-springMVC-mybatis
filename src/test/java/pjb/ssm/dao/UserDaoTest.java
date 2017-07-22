package pjb.ssm.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pjb.ssm.domain.User;
import pjb.ssm.BaseTest;

import java.io.Serializable;

/**
 * Created by dell1 on 2017/7/8.
 */
public class UserDaoTest extends BaseTest{
    @Autowired
    private UserDao userDao;

    @Test
    public void testAdd() {
        User user = new User();
        user.setLoginId("2015081040");
        user.setName("意识流");
        user.setPwd("123456");
        user.setSex("不知道");
        int result = 0; //受影响的行数默认为0
        try {
            result = userDao.add(user);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("添加用户失败");
        }
        if (result>0)
            System.out.println("添加用户成功");
    }

    /*@Test
    public void testFindOneId() throws Exception {
        User user = new User();
        user.setLoginId("2015081040");
        User result = null; //受影响的行数默认为0
        try {
            result = userDao.findOneById(user.getLoginId());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("查找用户失败");
        }
        if (null!=result)
            System.out.println("查找用户成功\n"+result.toString());
    }

    @Test
    public void testDelete() {
        User user = new User();
        user.setLoginId("2015081040");
        int result = 0; //受影响的行数默认为0
        try {
            result = userDao.delete(user);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("删除用户失败");
        }
        if (result>0)
            System.out.println("删除用户成功");
    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setLoginId("2015081040");
        user.setName("pjb");
        user.setPwd("123456");
        user.setSex("男");
        int result = 0; //受影响的行数默认为0
        try {
            result = userDao.update(user);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("更新用户信息用户失败");
        }
        if (result>0)
            System.out.println("更新用户信息用户成功");

    }*/
}
