package demo.ssm.controller;

import demo.ssm.pojo.User;
import demo.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/update/user_update",method = RequestMethod.POST)
    public String user_update(User user,String repassword,HttpServletRequest request, Model model)
    {
       // if(!repassword.equals(user.getuPassword()))
         //   return "redirect:/to/update/user";
        HttpSession session = request.getSession();
        String uId = session.getAttribute("uId").toString();
        String uFlag = session.getAttribute("uFlag").toString();
        System.out.println("要修改的用户名"+uId);
        System.out.println(user.getuPassword());
        model.addAttribute(uId);
        user.setuId(uId);
        user.setuFlag(uFlag);
        userService.user_update(user);
        return "redirect:/return";
    }


    @RequestMapping(value = "/to/update/user_update", method = RequestMethod.GET)
    public String to_user_update(User user, Model model,HttpServletRequest request) {
        HttpSession session = request.getSession();
        String uId = session.getAttribute("uId").toString();
        user.setuId(uId);
        model.addAttribute("user", user);
        System.out.println(user.getuPassword());
        System.out.println(user.getuId());
        return "/user_update";
    }

    @RequestMapping(value = {"/tologin","/"}, method = RequestMethod.GET)
    public String to_login(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Object s = session.getAttribute("uFlag");
        if (s == null)
            return "/login";
        else {
            return "/" + s.toString().charAt(0) + "_index";
        }
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index_get(HttpServletRequest request, HttpServletResponse response, String uId, String uPassword, Model model) {
        String uFlag = userService.index_get(uId, uPassword);
        if (uFlag == null)
            return "redirect:/";
        HttpSession session = request.getSession();
        session.setAttribute("uId", uId);
        session.setAttribute("uPassword", uPassword);
        session.setAttribute("uFlag", uFlag);
        model.addAttribute("uId", uId);
        System.out.println(uId + uPassword);
        session.setMaxInactiveInterval(3 * 60);
        return "/" + uFlag.charAt(0) + "_index";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(value = "/return", method = RequestMethod.GET)
    public String return_index(HttpServletRequest request, HttpServletResponse response,Model model) {
        HttpSession session = request.getSession();
        String user = session.getAttribute("uFlag").toString();
        String uId = session.getAttribute("uId").toString();
        model.addAttribute("uId",uId);
        return "/" + user.charAt(0) + "_index";
    }


}
