package top.bowentu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import top.bowentu.service.ILoginService;

@Controller
public class LoginController {
    @Autowired
    private ILoginService loginService;

    @GetMapping("/test")
    public ModelAndView test(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("test");
        mv.addObject("msg","success");
        mv.addObject("n",3);
        return mv;
    }

    @GetMapping("/login")
    public ModelAndView login(@RequestParam(value = "page",defaultValue = "0") Integer page) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login");
        mv.addObject("page", page);
        return mv;
    }

    @PostMapping("/loginByUser")
    public ModelAndView loginByUser(@RequestParam(value = "username", required = true) String username,
                              @RequestParam(value = "password",required = true) String password){
        ModelAndView mv = new ModelAndView();
        if(loginService.login(username,password)) {
            mv.setViewName("redirect:/home/"+username);
            return mv;
        }
        mv.setViewName("login");
        mv.addObject("page",0);
        mv.addObject("msg","用户名或密码错误");
        return mv;
    }

    @PostMapping("/register")
    public ModelAndView register(String username, String password, String confirmPassword){
        ModelAndView mv = new ModelAndView("login");
        mv.addObject("page", 1);
        if("".equals(username)||"".equals(password)){
            mv.addObject("msg", "用户名或密码不能为空");
        }else if(!password.equals(confirmPassword)){
            mv.addObject("msg", "两次密码输入不一致");
        }else if(!loginService.register(username, password)){
            mv.addObject("msg", "用户名已存在");
        }else{
            mv.addObject("msg","注册成功");
            System.out.println("新用户"+username+"已注册");
        }
        return mv;
    }
}
