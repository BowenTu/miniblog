package top.bowentu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import top.bowentu.pojo.Blog;
import top.bowentu.pojo.User;
import top.bowentu.service.IBlogService;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private IBlogService blogService;

    @GetMapping("/home/{username}")
    public ModelAndView visitHome(@PathVariable("username") String username){
        ModelAndView mv = new ModelAndView();
        List<Blog> blogs = blogService.getBlogListByUserName(username);
        User user = blogService.findUserByUserName(username);
        for(Blog blog:blogs){
        }
        mv.addObject("blogs", blogs);
        mv.addObject("user", user);
        mv.setViewName("home");
        return mv;
    }

    @PostMapping("/publishBlog")
    public ModelAndView publishBlog(@RequestParam("blogContent") String blogContent,
                                    @RequestParam("userid")Integer userid,@RequestParam("username")String username){
        ModelAndView mv = new ModelAndView("redirect:/home/"+username);
        if("".equals(blogContent)){
            mv.addObject("msg","内容不能为空！");
        }else{
            blogService.saveBlog(userid, blogContent);
        }
        System.out.println("用户"+username+"发布了微博:"+blogContent);
        return mv;
    }
}
