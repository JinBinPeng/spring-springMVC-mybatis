package pjb.ssm.dao;

import org.apache.ibatis.annotations.Param;
import pjb.ssm.domain.UserActionLog;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dell1 on 2017/7/10.
 */
public interface ActionLogDao extends Dao<UserActionLog>{
    int add(UserActionLog userActionLog);

    /**
     * 分页查询
     * @param offset    起始位置
     * @param limit     每页数量
     * @return
     */
    List<UserActionLog> findAll(@Param("offset") int offset, @Param("limit") int limit);

    int getAllCount();
}
