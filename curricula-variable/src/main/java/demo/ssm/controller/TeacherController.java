package demo.ssm.controller;

import com.github.pagehelper.PageInfo;
import demo.ssm.pojo.Teacher;
import demo.ssm.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @RequestMapping(value = "/select/t_info_all/{pageNum}",method = RequestMethod.GET)
    public String t_info_all(@PathVariable("pageNum")String pageNum, Model model,Teacher teacher,Integer min,Integer max)
    {
        System.out.println(teacher);
        System.out.println("min="+min);
        System.out.println("max="+max);
        PageInfo<Teacher> page = teacherService.getTeacherPage(pageNum,teacher,min,max);
        model.addAttribute("page",page);
        //System.out.println(page);
        return "/t_info_all";
    }

    @RequestMapping(value = "/insert/t_info_add",method = RequestMethod.POST)
    public String t_info_add(Teacher teacher)
    {
        teacherService.addTeacher(teacher);
        return "redirect:/select/t_info_all/1";
    }

    @RequestMapping(value = "/to/update/t_info_update",method = RequestMethod.GET)
    public String to_t_info_update(Teacher teacher,Model model)
    {
        teacher = teacherService.getTeacher(teacher.gettNo());
        System.out.println(teacher);
        model.addAttribute("teacher",teacher);
        return "/t_info_update";
    }

    @RequestMapping(value = "/update/t_info_update",method = RequestMethod.POST)
    public String t_info_update(Teacher teacher)
    {
        teacherService.updateTeacher(teacher);
        return "redirect:/select/t_info_all/1";
    }
    @RequestMapping(value = "/delete/t_info",method = RequestMethod.POST)
    private String delete_t_info(Teacher teacher)
    {
        teacherService.deleteTeacher(teacher);
        return "redirect:/select/t_info_all/1";
    }

    @RequestMapping(value = "/select/t_info",method = RequestMethod.GET)
    public String select_t_info(HttpServletRequest request, HttpServletResponse response,Model model)
    {
        HttpSession session = request.getSession();
        String uFlag = session.getAttribute("uFlag").toString();
        String uId = session.getAttribute("uId").toString();
        if(!uFlag.equals("teacher"))
            return "redirect:/logout";
        Teacher teacher = teacherService.getTeacher(uId);
        model.addAttribute("teacher",teacher);
        return "/t_info";
    }
}
