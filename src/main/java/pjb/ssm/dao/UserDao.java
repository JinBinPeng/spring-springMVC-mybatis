package pjb.ssm.dao;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import pjb.ssm.domain.User;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dell1 on 2017/7/8.
 */
public interface UserDao extends Dao<User>{
    int add(User user);

    int delete(User user);

    int update(User user);

    User findOneById(Serializable Id);

    void updateLoginSession(@Param("sessionId") String sessionId, @Param("loginId")  String loginId);

}
