package pjb.ssm.domain;

/**
 * Created by dell1 on 2017/7/11.
 */
public class ResponseList<T> {
    public final static int OK = 1, FAILED = 0, EMPUTY = -1;
    public final static String OK_STR = "成功", FAILED_STR = "失败", EMPUTY_STR = "数据为空";

    private int code; // 状态码,0成功;1空数据;-1请求失败
    private String msg;
    private Object data;
    private int pageNum,pageSize,totalNum;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }
}
