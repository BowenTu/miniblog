package top.bowentu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import top.bowentu.pojo.Blog;
import top.bowentu.service.IBlogService;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private IBlogService blogService;

    @GetMapping("/home")
    public ModelAndView visitHome(@RequestParam("userid") Integer userid){
        ModelAndView mv = new ModelAndView();
        List<Blog> blogs = blogService.getBlogList(userid);
        for(Blog blog:blogs){
            System.out.println(blog);
        }
        mv.addObject("blogs", blogs);
        mv.setViewName("home");
        return mv;
    }
}
