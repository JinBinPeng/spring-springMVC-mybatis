package pjb.ssm.mvc.controller;

/**
 * 分页查找行为日志，其实druid里面已经包含了行为日志
 * 行为日志的调用接口
 * Created by dell1 on 2017/7/10.
 */

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pjb.ssm.domain.ResponseList;
import pjb.ssm.domain.ResponseObj;
import pjb.ssm.domain.UserActionLog;
import pjb.ssm.service.ActionLogService;
import pjb.ssm.utils.GsonUtils;

import java.util.List;

@Controller
@RequestMapping("/actionLog")
public class ActionLogController {
    @Autowired
    ActionLogService actionLogService;
    /**
     * @param pageNum  页码
     * @param pageSize 每一页的条数
     * @return
     */
    @GetMapping("/findLogList")
    @ResponseBody
    public Object findLog(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize) {
        if (pageNum <= 0) { //错误页码默认跳转到第一页
            pageNum = 1;
        }
        if (pageSize <= 0) {    //错误数据长度默认设置为10条
            pageSize = 10;
        }
        int toalNum; //总页码
        ResponseList<UserActionLog> responseObj = new ResponseList<UserActionLog>();
        try {
            toalNum = actionLogService.getAllCount();   //先把总条数赋值给总页数，作为缓存变量，减少下面算法的查找次数
            toalNum = toalNum % pageSize > 0 ? toalNum / pageSize + 1 : toalNum / pageSize;     //在每页固定条数下能不能分页完成，有余则加一页码
            List<UserActionLog> result = actionLogService.findAll(pageNum, pageSize);
            responseObj.setPageNum(pageNum);
            responseObj.setTotalNum(toalNum);
            responseObj.setPageSize(pageSize);
            if (result == null || result.size() == 0) {
                responseObj.setCode(ResponseObj.EMPUTY);
                responseObj.setMsg("查询结果为空");
                return new GsonUtils().toJson(responseObj);
            }
            responseObj.setCode(ResponseObj.OK);
            responseObj.setMsg("查询成功");
            responseObj.setData(result);
            return new GsonUtils().toJson(responseObj);
        }catch (Exception e){
            e.printStackTrace();
            //查询失败
            responseObj.setCode(ResponseObj.FAILED);
            responseObj.setMsg("查询失败");
            return new GsonUtils().toJson(responseObj);
        }
    }
}
