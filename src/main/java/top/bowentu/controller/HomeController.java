package top.bowentu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import top.bowentu.common.constant.InformMessage;
import top.bowentu.common.utils.SessionUtil;
import top.bowentu.pojo.Blog;
import top.bowentu.pojo.User;
import top.bowentu.service.IBlogService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private IBlogService blogService;
    @Autowired
    private HttpServletRequest request;

    @GetMapping("/home")
    public ModelAndView visitHome(){
        ModelAndView mv = new ModelAndView();
        User user = SessionUtil.getUserSession(request);
        List<Blog> blogs = blogService.getBlogListByUserId(user.getUid());
        mv.addObject("blogs", blogs);
        mv.addObject("user", user);
        mv.setViewName("home");
        return mv;
    }

    @PostMapping("/publishBlog")
    public ModelAndView publishBlog(@RequestParam("blogContent") String blogContent){
        ModelAndView mv = new ModelAndView("redirect:/home");
        User user = SessionUtil.getUserSession(request);
        if("".equals(blogContent)){
            mv.addObject("msg", InformMessage.CONTENT_CAN_NOT_BE_NULL);
        }else{
            blogService.saveBlog(user.getUid(), blogContent);
        }
        System.out.println("用户"+user.getUsername()+"发布了微博:"+blogContent);
        return mv;
    }
}
