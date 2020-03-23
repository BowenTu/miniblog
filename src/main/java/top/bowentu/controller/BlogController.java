package top.bowentu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import top.bowentu.common.constant.InformMessage;
import top.bowentu.common.utils.SessionUtil;
import top.bowentu.pojo.User;
import top.bowentu.service.IBlogService;
import javax.servlet.http.HttpServletRequest;

@Controller
public class BlogController {
    @Autowired
    private IBlogService blogService;
    @Autowired
    private HttpServletRequest request;

    @PostMapping("/publishBlog")
    public ModelAndView publishBlog(@RequestParam("blogContent") String blogContent){
        ModelAndView mv = new ModelAndView("redirect:/home");
        User user = SessionUtil.getUserSession(request);
        if("".equals(blogContent)){
            mv.addObject("msg", InformMessage.CONTENT_CAN_NOT_BE_NULL);
        }else{
            blogService.insertBlog(user.getUid(), blogContent);
        }
        System.out.println("用户"+user.getUsername()+"发布了微博:"+blogContent);
        return mv;
    }

    @RequestMapping(value = "/deleteBlog")
    @ResponseBody
    public String deleteBlog(Integer blogId) {
        User user = SessionUtil.getUserSession(request);
        if (user == null) return "error";
        blogService.deleteBlog(user.getUid(), blogId);
        //userid=-1表示所有用户的动态
        blogService.deleteBlog(-1, blogId);
        return "success";
    }
}
