package pjb.ssm.mvc.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import pjb.ssm.domain.ResponseObj;
import pjb.ssm.domain.User;
import pjb.ssm.service.UserService;
import pjb.ssm.utils.GsonUtils;
import pjb.ssm.utils.PublicUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 用户请求相关控制器
 * Created by dell1 on 2017/7/8.
 */
@Controller//标明本类是控制器
@RequestMapping("/userAction")//外层地址
public class UserController {
    @Autowired
    private UserService userService;    //自动载入Service对象
    private ResponseObj responseObj;    //返回json数据的实体

    /**
     * @param request   http请求
     * @param user  发起请求后，spring接收到请求，然后封装的bean数据
     * @throws Exception
     */
    @PostMapping("/reg")
    @ResponseBody
    public Object reg(HttpServletRequest request, User user, HttpSession session) throws Exception {
        //Object result;
        JSONObject jsonObject=new JSONObject();
        responseObj = new ResponseObj<User>();
        if (null == user) {
            responseObj.setCode(ResponseObj.FAILED);
            responseObj.setMsg("用户信息不能为空！");
            //result = new GsonUtils().toJson(responseObj);
            /*java对象转化为json对象*/
            jsonObject= (JSONObject) JSON.toJSON(responseObj);
            //return result;
            return jsonObject;
        }
        if (StringUtils.isEmpty(user.getLoginId()) || StringUtils.isEmpty(user.getPwd())) {
            responseObj.setCode(ResponseObj.FAILED);
            responseObj.setMsg("用户名或密码不能为空！");
            //result = new GsonUtils().toJson(responseObj);
            jsonObject= (JSONObject) JSON.toJSON(responseObj);
            //return result;
            return jsonObject;
        }
        if (null != userService.findUser(user)) {
            responseObj.setCode(ResponseObj.FAILED);
            responseObj.setMsg("用户已经存在！");
            //result = new GsonUtils().toJson(responseObj);
            jsonObject= (JSONObject) JSON.toJSON(responseObj);
            //return result;
            return jsonObject;
        }
        try {
            userService.add(user);
        } catch (Exception e) {
            e.printStackTrace();
            responseObj.setCode(ResponseObj.FAILED);
            responseObj.setMsg("其他错误！");
            //result = new GsonUtils().toJson(responseObj);
            jsonObject= (JSONObject) JSON.toJSON(responseObj);
            //return result;
            return jsonObject;
        }
        userService.updateLoginSession(request.getSession().getId(), user.getLoginId());
        responseObj.setCode(ResponseObj.OK);
        responseObj.setMsg("注册成功");
        user.setPwd(session.getId());   //单独设置密码为sessionId 误导黑客，前端访问服务器的时候必须有这个信息才能操作
        user.setNextUrl(request.getContextPath() + "/mvc/home");    //单独控制地址
        responseObj.setData(user);
        session.setAttribute("userInfo", user);
        //result = new GsonUtils().toJson(responseObj);
        jsonObject= (JSONObject) JSON.toJSON(responseObj);
        return jsonObject;
    }
    /*@PostMapping("/reg")
    @ResponseBody
    public Object reg(HttpServletRequest request, User user, HttpSession session) throws Exception {
        Object result;
        //responseObj = new ResponseObj<User>();
        if (PublicUtil.isJsonRequest(request)) {    //确认是否json提交
            user = new GsonUtils().jsonRequest2Bean(request.getInputStream(), User.class);  //转换为指定类型的对象
            return user.toString();
        }
        if (null == user) {
            responseObj = new ResponseObj<User>();
            responseObj.setCode(ResponseObj.FAILED);
            responseObj.setMsg("用户信息不能为空！");
            result = new GsonUtils().toJson(responseObj);
            return result;
        }
        if (StringUtils.isEmpty(user.getLoginId()) || StringUtils.isEmpty(user.getPwd())) {
            responseObj = new ResponseObj<User>();
            responseObj.setCode(ResponseObj.FAILED);
            responseObj.setMsg("用户名或密码不能为空！");
            result = new GsonUtils().toJson(responseObj);
            return result;
        }
        if (null != userService.findUser(user)) {
            responseObj = new ResponseObj<User>();
            responseObj.setCode(ResponseObj.FAILED);
            responseObj.setMsg("用户已经存在！");
            result = new GsonUtils().toJson(responseObj);
            return result;
        }
        try {
            userService.add(user);
        } catch (Exception e) {
            e.printStackTrace();
            responseObj = new ResponseObj<User>();
            responseObj.setCode(ResponseObj.FAILED);
            responseObj.setMsg("其他错误！");
            result= new GsonUtils().toJson(responseObj);
            return result;
        }
        userService.updateLoginSession(request.getSession().getId(), user.getLoginId());
        responseObj = new ResponseObj<User>();
        responseObj.setCode(ResponseObj.OK);
        responseObj.setMsg("注册成功");
        user.setPwd(session.getId());   //单独设置密码为sessionId 误导黑客，前端访问服务器的时候必须有这个信息才能操作
        user.setNextUrl(request.getContextPath() + "/mvc/register");    //单独控制地址
        responseObj.setData(user);
        session.setAttribute("userInfo", user);
        result= new GsonUtils().toJson(responseObj);
        return result;
    }*/
    /*public ModelAndView reg(HttpServletRequest req, User user)  {
        ModelAndView mav = new ModelAndView();  //创建一个jsp页面对象
        mav.setViewName("home");    //设置JSP文件名
        if (null == user) {
            mav.addObject("message", "用户信息不能为空！");  //加入提示信息，在jsp中我们直接使用${对象名称}就能获取对应的内容
            return mav; //返回页面
        }
        if (StringUtils.isEmpty(user.getName()) || StringUtils.isEmpty(user.getPwd())) {
            mav.addObject("message", "用户名或密码不能为空！");
            return mav;
        }
        if (null != userService.findUser(user)) {
            mav.addObject("message", "用户已经存在！");
            return mav;
        }
        try {
            userService.add(user);
        } catch (Exception e) {
            e.printStackTrace();
            mav.addObject("message", "错误：用户其他信息错误");
            return mav;
        }
        mav.addObject("code", 110);
        mav.addObject("message", "恭喜。注册成功");
        req.getSession().setAttribute("user", user);
        return mav;
    }*/

    /**
     * 登录接口
     * @param request
     * @param user
     * @return
     */
    /*@RequestMapping(value = "/login"
            , method = RequestMethod.POST
            , produces = "application/json; charset=utf-8")
    @ResponseBody*/
    /*public Object login(HttpServletRequest request, HttpServletResponse response, User user, HttpSession session) throws Exception {
        Object result;
        if (null == user) {
            responseObj = new ResponseObj<User>();
            responseObj.setCode(ResponseObj.EMPUTY);
            responseObj.setMsg("登录信息不能为空");
            result = new GsonUtils().toJson(responseObj);
            return result; //返回json
        }
        if (StringUtils.isEmpty(user.getLoginId()) || StringUtils.isEmpty(user.getPwd())) {
            responseObj = new ResponseObj<User>();
            responseObj.setCode(ResponseObj.FAILED);
            responseObj.setMsg("用户名或密码不能为空");
            result = new GsonUtils().toJson(responseObj);
            return result;
        }
        //查找用户
        User user1 = userService.findUser(user);
        if (null == user1) {
            responseObj = new ResponseObj<User>();
            responseObj.setCode(ResponseObj.EMPUTY);
            responseObj.setMsg("未找到该用户");
            result = new GsonUtils().toJson(responseObj);
        } else {
            if (user.getPwd().equals(user1.getPwd())) {
                user1.setPwd(session.getId());
                user1.setNextUrl(request.getContextPath() + "/mvc/home");
                responseObj = new ResponseObj<User>();
                responseObj.setCode(ResponseObj.OK);
                responseObj.setMsg(ResponseObj.OK_STR);
                responseObj.setData(user1);
                session.setAttribute("userInfo", user);
                result = new GsonUtils().toJson(responseObj);
            } else {
                responseObj = new ResponseObj<User>();
                responseObj.setCode(ResponseObj.FAILED);
                responseObj.setMsg("用户密码错误");
                result = new GsonUtils().toJson(responseObj);
            }
        }
        return result;
    }*/
    @PostMapping("/login")
    @ResponseBody
    public Object login(HttpServletRequest request, User user,HttpSession session) throws Exception{
        //Object result;
        JSONObject jsonObject=new JSONObject();
        responseObj = new ResponseObj<User>();
        if (PublicUtil.isJsonRequest(request)) {    //确认是否json提交
            user = new GsonUtils().jsonRequest2Bean(request.getInputStream(), User.class);  //转换为指定类型的对象
            return user.toString();
        }
        if (null == user) {
            responseObj.setCode(ResponseObj.EMPUTY);
            responseObj.setMsg("登录信息不能为空");
            /*result = new GsonUtils().toJson(responseObj);   //通过gson把java bean转换为json
            return result; //返回json*/
            jsonObject= (JSONObject) JSON.toJSON(responseObj);
            return jsonObject;
        }
        if (StringUtils.isEmpty(user.getLoginId()) || StringUtils.isEmpty(user.getPwd())) {
            responseObj.setCode(ResponseObj.FAILED);
            responseObj.setMsg("用户名或密码不能为空");
            jsonObject= (JSONObject) JSON.toJSON(responseObj);
            return jsonObject;
        }
        //查找用户
        User user1 = userService.findUser(user);
        if (null == user1) {
            responseObj.setCode(ResponseObj.EMPUTY);
            responseObj.setMsg("未找到该用户");
            jsonObject= (JSONObject) JSON.toJSON(responseObj);
            return jsonObject;
        } else {
            if (user.getPwd().equals(user1.getPwd())) {
                user1.setPwd(session.getId());
                user1.setNextUrl(request.getContextPath() + "/mvc/home");
                responseObj.setCode(ResponseObj.OK);    //登录成功，状态为1
                responseObj.setMsg(ResponseObj.OK_STR);
                responseObj.setData(user1); //登陆成功后返回用户信息
                userService.updateLoginSession(request.getSession().getId(), user.getLoginId());
                session.setAttribute("userInfo", user1);
                jsonObject= (JSONObject) JSON.toJSON(responseObj);
                return jsonObject;
            } else {
                responseObj.setCode(ResponseObj.FAILED);
                responseObj.setMsg("用户密码错误");
                jsonObject= (JSONObject) JSON.toJSON(responseObj);
                return jsonObject;
            }
        }
    }
    /*public ModelAndView login(HttpServletRequest req, User user) throws Exception{
        ModelAndView mav = new ModelAndView("home");
        String result;
        if (null == user) {
            responseObj = new ResponseObj<User>();
            responseObj.setCode(ResponseObj.EMPUTY);
            responseObj.setMsg("登录信息不能为空");
            result = GsonUtils.gson.toJson(responseObj);    //转换的json数据
            mav.addObject("result", result);
            return mav; //返回页面
        }
        if (StringUtils.isEmpty(user.getLoginId()) || StringUtils.isEmpty(user.getPwd())) {
            responseObj = new ResponseObj<User>();
            responseObj.setCode(ResponseObj.FAILED);
            responseObj.setMsg("用户名或密码不能为空");
            result = GsonUtils.gson.toJson(responseObj);
            mav.addObject("result", result);
            return mav;
        }
        //查找用户
        User user1 = userService.findUser(user);
        if (null == user1) {
            responseObj = new ResponseObj<User>();
            responseObj.setCode(ResponseObj.EMPUTY);
            responseObj.setMsg("未找到该用户");
            result = GsonUtils.gson.toJson(responseObj);
        } else {
            if (user.getPwd().equals(user1.getPwd())) {
                responseObj = new ResponseObj<User>();
                responseObj.setCode(ResponseObj.OK);
                responseObj.setMsg(ResponseObj.OK_STR);
                result = GsonUtils.gson.toJson(responseObj);
            } else {
                responseObj = new ResponseObj<User>();
                responseObj.setCode(ResponseObj.FAILED);
                responseObj.setMsg("用户密码错误");
                result = GsonUtils.gson.toJson(responseObj);
            }
        }
        mav.addObject("result", result);
        return mav;
    }*/
    @PostMapping("/uploadHeadPic")
    @ResponseBody
    public Object uploadHeadPic(@RequestParam(required = false) MultipartFile file, HttpSession session) {
        JSONObject jsonObject=new JSONObject();
        responseObj = new ResponseObj();
        if (null == file || file.isEmpty()) {
            responseObj.setCode(ResponseObj.FAILED);
            responseObj.setMsg("文件不能为空");
            return new GsonUtils().toJson(responseObj);
        }
        responseObj.setCode(ResponseObj.OK);
        responseObj.setMsg("文件长度为：" + file.getSize());
        jsonObject= (JSONObject) JSON.toJSON(responseObj);
        return jsonObject;
    }
}

