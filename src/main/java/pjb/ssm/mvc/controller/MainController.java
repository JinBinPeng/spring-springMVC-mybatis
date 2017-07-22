package pjb.ssm.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by dell1 on 2017/7/8.
 */
@Controller
@RequestMapping("/mvc")
public class MainController {
    /**
     * 登陆页面
     * @return
     */
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    /**
     * 后台主页
     *
     * @return
     */
    @GetMapping("/home")
    public String home() {
        return "home";
    }
    /*
    *日志主页
    */
    @GetMapping("/listActionLog")
    public String listActionLog() {
        return "list_action_log";
    }
}
