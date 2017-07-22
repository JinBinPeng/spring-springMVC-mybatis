package pjb.ssm.utils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by dell1 on 2017/7/11.
 */
public class PublicUtil {
    public static void main(String[] args) {
        System.out.println("本机的ip=" + PublicUtil.getIp());
    }

    public static String getPorjectPath() {
        String nowpath = "";
        nowpath = System.getProperty("user.dir") + "/";

        return nowpath;
    }

    /**
     * 获取本机ip
     *
     * @return
     */
    public static String getIp() {
        String ip = "";
        try {
            InetAddress inet = InetAddress.getLocalHost();
            ip = inet.getHostAddress();
            //System.out.println("本机的ip=" + ip);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return ip;
    }

    public static boolean isJsonRequest(HttpServletRequest request) {
        return request.getContentType().contains("application/json");
    }
}
