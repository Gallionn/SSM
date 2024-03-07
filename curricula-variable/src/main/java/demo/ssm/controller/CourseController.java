package demo.ssm.controller;

import com.github.pagehelper.PageInfo;
import demo.ssm.pojo.Course;
import demo.ssm.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "/select/c_info/{pageNum}",method = RequestMethod.GET)
    public String c_info_all(@PathVariable("pageNum")String pageNum, Model model, Course course,
                             Integer min, Integer max, HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        String uFlag = session.getAttribute("uFlag").toString();
        PageInfo<Course> page = courseService.getCoursePage(pageNum,course,min,max);
        model.addAttribute("page",page);
        //System.out.println(page);
        return "/c_info_"+uFlag.charAt(0);
    }

    @RequestMapping(value = "/insert/c_info_add",method = RequestMethod.POST)
    public String insert_c_info(Course course)
    {
          courseService.addCourse(course);
          return "redirect:/select/c_info/1";
    }

    @RequestMapping(value = "/to/update/c_info_update",method = RequestMethod.GET)
    public String to_update_c_info(Course course,Model model)
    {
        course = courseService.getCourse(course.getcNo());
        model.addAttribute("course",course);
        return "/c_info_update";
    }
    @RequestMapping(value = "/update/c_info_update",method = RequestMethod.POST)
    public String update_c_info(Course course)
    {
       courseService.updateCourse(course);
       return "redirect:/select/c_info/1";
    }
    @RequestMapping(value = "/delete/c_info",method = RequestMethod.POST)
    public String delete_c_info(Course course)
    {
         courseService.deleteCourse(course);
        return "redirect:/select/c_info/1";

    }


}
