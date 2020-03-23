package top.bowentu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import top.bowentu.common.constant.InformMessage;
import top.bowentu.common.utils.RedisPool;
import top.bowentu.pojo.Blog;
import top.bowentu.pojo.User;
import top.bowentu.service.IBlogService;
import top.bowentu.service.ILoginService;
import top.bowentu.common.utils.SessionUtil;
import top.bowentu.service.IUserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    private ILoginService loginService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private IBlogService blogService;
    @Autowired
    private IUserService userService;

    @GetMapping("/login")
    public ModelAndView login(@RequestParam(value = "page",defaultValue = "0") Integer page) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login");
        mv.addObject("page", page);
        return mv;
    }

    @PostMapping("/checkLogin")
    public ModelAndView checkLogin(@RequestParam(value = "username") String username,
                              @RequestParam(value = "password") String password){
        ModelAndView mv = new ModelAndView();
        User user = userService.findByUserName(username);
        if(loginService.checkLogin(password, user)) {
            SessionUtil.setUserSession(request, user);
            mv.setViewName("redirect:/home");
        }else{
            mv.setViewName("login");
            mv.addObject("page",0);
            mv.addObject("msg", InformMessage.WRONG_USERNAME_OR_PASSWORD);
        }
        return mv;
    }

    @PostMapping("/register")
    public ModelAndView register(String username, String password, String confirmPassword){
        ModelAndView mv = new ModelAndView();
        String msg = loginService.checkRegister(username, password, confirmPassword);
        if(InformMessage.REGISTER_SUCCESS.equals(msg)){
            User user = userService.findByUserName(username);
            SessionUtil.setUserSession(request, user);
            mv.setViewName("redirect:/home");
        }else{
            mv.setViewName("login");
            mv.addObject("page", 1);
            mv.addObject("msg", msg);
        }
        return mv;
    }

    @GetMapping("/logout")
    public String logout(){
        SessionUtil.removeUserSession(request);
        return "redirect:/login";
    }

}
