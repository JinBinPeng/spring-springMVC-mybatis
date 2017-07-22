package pjb.ssm.service;

import pjb.ssm.domain.UserActionLog;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by dell1 on 2017/7/10.
 */
public interface ActionLogService extends BaseService<UserActionLog>{
    void add(HttpServletRequest request);

    /**
     * 集合查询
     * @param pageNum   页码
     * @param pageSize  每页的查询数量
     * @return
     */
    List<UserActionLog> findAll(int pageNum, int pageSize);

    /**
     * 获取总条数
     * @return  获取总条数
     */
    int getAllCount();
}
