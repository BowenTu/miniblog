package top.bowentu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import top.bowentu.common.utils.SessionUtil;
import top.bowentu.pojo.Blog;
import top.bowentu.pojo.User;
import top.bowentu.service.IBlogService;
import top.bowentu.service.IUserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private IBlogService blogService;
    @Autowired
    private IUserService userService;

    @GetMapping("/userPage")
    public ModelAndView showUserPage(@RequestParam(value = "page", defaultValue = "0")Integer page,
                                     @RequestParam(value = "userid", required = true)Integer userid){
        ModelAndView mv = new ModelAndView("user");
        User selectedUser = userService.findByUserId(userid);
        User user = SessionUtil.getUserSession(request);
        List<Blog> blogs = blogService.getBlogListByUserId(selectedUser.getUid());
        mv.addObject("selectedUser", selectedUser);
        mv.addObject("user",user);
        mv.addObject("blogs", blogs);
        mv.addObject("page", page);
        return mv;
    }

    @GetMapping("/follow")
    public ModelAndView follow(@RequestParam(value = "userid", required = true)Integer userid){
        return null;
    }
}
