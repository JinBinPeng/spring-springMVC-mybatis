package pjb.ssm;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by dell1 on 2017/7/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)//用于配置spring中测试的环境
@ContextConfiguration({"classpath:spring/spring-*.xml"})//用于指定配置文件所在的位置
public class BaseTest {
}
