package top.bowentu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import top.bowentu.pojo.Blog;
import top.bowentu.pojo.User;
import top.bowentu.service.IBlogService;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private IBlogService blogService;

    @RequestMapping("/home/{username}")
    public ModelAndView visitHome(@PathVariable("username") String username){
        System.out.println(username);
        ModelAndView mv = new ModelAndView();
        List<Blog> blogs = blogService.getBlogListByUserName(username);
        User user = blogService.findUserByUserName(username);
        for(Blog blog:blogs){
            System.out.println(blog);
        }
        mv.addObject("blogs", blogs);
        mv.addObject("user", user);
        mv.setViewName("home");
        return mv;
    }
}
