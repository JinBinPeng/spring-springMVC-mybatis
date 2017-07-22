package pjb.ssm.service;

import pjb.ssm.domain.User;

import java.util.List;

/**
 * 创建一个UserService继承BaseService，并指定具体的实体类型
 * 不同对象的业务体系不同，BaseService并不能完全替代不同对象的具体行为表现
 * Created by dell1 on 2017/7/8.
 */
public interface UserService extends BaseService<User>{
    void add(User user) throws Exception;
    User findUser(User user) throws Exception;
    void updateLoginSession(String sessionId, String loginId);
}
