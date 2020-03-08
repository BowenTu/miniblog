package top.bowentu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import top.bowentu.common.utils.SessionUtil;
import top.bowentu.pojo.BlogDetail;
import top.bowentu.pojo.ColumnCount;
import top.bowentu.pojo.User;
import top.bowentu.service.IBlogService;
import top.bowentu.service.IUserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

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
                                     @RequestParam(value = "userid", required = true)Integer theUserid){
        ModelAndView mv = new ModelAndView("user");
        User user = SessionUtil.getUserSession(request);
        User theUser = userService.findByUserId(theUserid);

        //获取用户中心各栏目的条目数
        ColumnCount columnCount = userService.getUserColumnCount(theUserid);
        mv.addObject("column", columnCount);
        // 根据情况显示关注和取消关注
        if(theUserid!=user.getUid()){
            if(userService.getFollowerIds(theUserid).contains(user.getUid())){
                mv.addObject("follow","取消关注");
            }else{
                mv.addObject("follow","关注");
            }
        }
        mv.addObject("theUser",theUser);
        mv.addObject("user",user);
        mv.addObject("page", page);
        //设置用户中心各栏目的显示
        if(page==0){
            List<BlogDetail> blogDetailList = blogService.getBlogDetailListByUserId(theUserid);
            mv.addObject("blogDetails", blogDetailList);
        }else if(page==1){
            Set<User> followers = userService.getFollowers(theUserid);
            mv.addObject("follows", followers);
        }else if(page==2){
            Set<User> followings = userService.getFollowings(theUserid);
            mv.addObject("follows", followings);
        }else if(page==3){
            List<User> allUser = userService.findAll();
            mv.addObject("allUser",allUser);
        }
        return mv;
    }

    @GetMapping("/follow")
    public String follow(@RequestParam(value = "userid", required = true)Integer theUserid){
        User user = SessionUtil.getUserSession(request);
        userService.follow(user.getUid(), theUserid);
        return "redirect:/userPage?page=0&&userid="+theUserid;
    }

    @GetMapping("/delFollow")
    public String cancelFollow(@RequestParam(value = "userid", required = true)Integer theUserid){
        User user = SessionUtil.getUserSession(request);
        userService.delFollow(user.getUid(), theUserid);
        return "redirect:/userPage?page=0&&userid="+theUserid;
    }

    @GetMapping({"/home","/"})
    public ModelAndView visitHome(){
        ModelAndView mv = new ModelAndView();
        User user = SessionUtil.getUserSession(request);
        if(user==null){
            mv.setViewName("redirect:/login");
        }else{
//            List<BlogDetail> blogDetailList = blogService.getBlogDetailListAccordToTime();
            List<BlogDetail> blogDetailList = blogService.getFollowingBlogDetail(user.getUid());
            mv.addObject("blogDetails", blogDetailList);
            mv.addObject("user", user);
            mv.setViewName("home");
        }
        return mv;
    }
}
