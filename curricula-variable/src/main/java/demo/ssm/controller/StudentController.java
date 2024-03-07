package demo.ssm.controller;

import com.github.pagehelper.PageInfo;
import demo.ssm.pojo.Student;
import demo.ssm.service.StudentService;
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
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/select/s_info_all/{pageNum}",method = RequestMethod.GET)
    public String s_info_all(@PathVariable("pageNum")String pageNum, Model model,Student student,Integer min,Integer max)
    {
        System.out.println("传进来的"+student);
        PageInfo<Student> page = studentService.getStudentPage(pageNum,student,min,max);
        System.out.println(page);
        model.addAttribute("page",page);
        return "/s_info_all";
    }

    @RequestMapping(value = "/insert/s_info_add",method = RequestMethod.POST)
    public String s_info_add(Student student)
    {
        studentService.addStudent(student);
        return "redirect:/select/s_info_all/1";
    }

    @RequestMapping(value = "/to/update/s_info_update",method = RequestMethod.GET)
    public String to_s_info_update(Student student,Model model)
    {
        student = studentService.getStudent(student.getsNo());
        System.out.println(student);
        model.addAttribute("student",student);
        return "/s_info_update";
    }

    @RequestMapping(value = "/update/s_info_update",method = RequestMethod.POST)
    public String s_info_update(Student student,HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        System.out.println(student);
        //String uFlag = session.getAttribute("uFlag").toString();
        //System.out.println(uFlag);
        studentService.updateStudent(student);
//        if(uFlag.equals("admin"))
//             return "redirect:/select/s_info";
//        else if(uFlag.equals("student"))
//            return "redirect:/select/s_info_all/1";
//        else
//            return "redirect:/logout";
        return "redirect:/select/s_info_all/1";
    }
    @RequestMapping(value = "/delete/s_info",method = RequestMethod.POST)
    private String delete_s_info(Student student)
    {
        System.out.println(student);
        studentService.deleteStudent(student);
        return "redirect:/select/s_info_all/1";
    }
    @RequestMapping(value = "/select/s_info",method = RequestMethod.GET)
    public String select_s_info(HttpServletRequest request, HttpServletResponse response, Model model)
    {
        HttpSession session = request.getSession();
        String uFlag = session.getAttribute("uFlag").toString();
        String uId = session.getAttribute("uId").toString();
        if(!uFlag.equals("student"))
            return "redirect:/logout";
        Student student = studentService.getStudent(uId);
        model.addAttribute("student",student);
        return "/s_info";
    }

}
