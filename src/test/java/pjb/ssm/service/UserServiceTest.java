package pjb.ssm.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pjb.ssm.BaseTest;
import pjb.ssm.domain.User;
import pjb.ssm.exception.*;
import pjb.ssm.service.serviceImpl.UserServiceImpl;

/**
 * Created by dell1 on 2017/7/8.
 */
public class UserServiceTest extends BaseTest{
    @Autowired
    private UserServiceImpl userService;
    //此处直接使用UserService的实现类，主要是方便抛出异常，然后异常出现时候可以针对性的处理

    @Test
    public void testAdd() throws Exception{
        User user = new User();
        user.setLoginId("20150810401");
        user.setName("意识流1");
        user.setPwd("123456");
        user.setSex("不知道");
        user.setDuty("老大");
        user.setCellNumber("15820638007");
        user.setAge(10);
            userService.add(user);
    }
    @Test
    public void testFindUser() throws Exception {
        User user = new User();
        user.setLoginId("20150810401");
        User result = null; //受影响的行数默认为0
        try {
            result = userService.findUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("查找用户失败");
        }
        if (null!=result)
            System.out.println("查找用户成功\n"+result.toString());
    }
}
