package demo.ssm.controller;

import com.github.pagehelper.PageInfo;
import demo.ssm.pojo.SC;
import demo.ssm.pojo.schedule;
import demo.ssm.service.SCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class SCController {

    @Autowired
    private SCService sCService;

    @RequestMapping(value = "/select/sc_info_all/{pageNum}",method = RequestMethod.GET)
    public String sc_info_all(@PathVariable("pageNum")String pageNum, Model model,SC sc,Integer min,Integer max)
    {
        System.out.println("sc:"+sc);
        PageInfo<SC> page = sCService.getSCPage(pageNum,null,sc,min,max);
        model.addAttribute("page",page);
        System.out.println(page);
        return "/sc_info_all";
    }

    @RequestMapping(value = "/select/s_application/{pageNum}",method = RequestMethod.GET)
    public String s_application(@PathVariable("pageNum")String pageNum, Model model,SC sc,Integer min,Integer max)
    {
        PageInfo<SC> page = sCService.getSCPage(pageNum,"未处理",sc,min,max);
        model.addAttribute("page",page);
        //System.out.println(page);
        return "/s_application";
    }

    @RequestMapping(value = "/to/update/sc_info_update",method = RequestMethod.GET)
    public String to_sc_info_update(SC sc,Model model)
    {
        System.out.println(sc);
        model.addAttribute("sc",sc);

        return "/sc_info_update";
    }

    @RequestMapping(value = "/update/sc_info_update",method = RequestMethod.POST)
    public String sc_info_update(SC sc,HttpServletRequest request, HttpServletResponse response)
    {
        HttpSession session = request.getSession();
        String uFlag = session.getAttribute("uFlag").toString();
        sCService.updateBysc(sc);
        if(uFlag.equals("admin"))
        return "redirect:/select/sc_info_all/1";
        else if(uFlag.equals("teacher"))
        return "redirect:/select/t_mystudent/1?cId="+sc.getcId();
        else
            return "redirect:/logout";
    }

    @RequestMapping(value = "/update/s_application",method = RequestMethod.POST)
    public String s_application_update(SC sc)
    {
        sCService.updateBysc(sc);
        return "redirect:/select/s_application/1";
    }

    @RequestMapping(value = "/add/sc_info",method = RequestMethod.POST)
    public String sc_info_add(SC sc)
    {
        sCService.addSc(sc);
        return "redirect:/select/s_application/1";
    }

    @RequestMapping(value = "/delete/sc_info",method = RequestMethod.POST)
    public String delete_sc(SC sc)
    {
        sCService.deletesc(sc);
        return "redirect:/select/s_application/1";
    }
    @RequestMapping(value = "/select/mystudent/{pageNum}",method = RequestMethod.GET)
    public String select_mystudent(@PathVariable("pageNum")String pageNum, Model model, String cId)
    {
        SC sc  = new SC();
        sc.setcId(cId);
        PageInfo<SC> page = sCService.getSCPage(pageNum,null,sc,null,null);
        model.addAttribute("page",page);
        //System.out.println(page);
        return "/mystudent";
    }
    @RequestMapping(value = "/select/s_mycourse/{pageNum}",method = RequestMethod.GET)
    public String s_select_mycourse(@PathVariable("pageNum")String pageNum, Model model,HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        String uId = session.getAttribute("uId").toString();
        SC sc  = new SC();
        sc.setsNo(uId);
        PageInfo<SC> page = sCService.getSCPage(pageNum,null,sc,null,null);
        model.addAttribute("page",page);
        //System.out.println(page);
        return "/s_mycourse";
    }
    @RequestMapping(value = "/insert/s_mycourse_add",method = RequestMethod.POST)
    public String insert_s_mycourse_add(SC sc)
    {
        sCService.addSc(sc);
        return "redirect:/select/s_mycourse/1";
    }
    @RequestMapping(value = "/delete/s_mycourse_delete",method = RequestMethod.POST)
    public String delete_mycourse(SC sc)
    {
        sCService.deletesc(sc);
        return "redirect:/select/s_mycourse/1";
    }
    @RequestMapping(value = "/to/update/s_mycourse_readd",method = RequestMethod.POST)
    public String to_sc_update_readd(SC sc,Model model)
    {
        sCService.updateBysc(sc);
        return "redirect:/select/s_mycourse/1";
    }
    @RequestMapping(value = "/update/s_mycourse_readd",method = RequestMethod.POST)
    public String s_mycourse_readd(SC sc)
    {
        sCService.updateBysc(sc);
        return "redirect:/select/s_mycourse/1";
    }
    @RequestMapping(value = "/select/s_class_chedule",method = RequestMethod.GET)
    public String s_class_chedulr( Model model,HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        String uId = session.getAttribute("uId").toString();
        List<schedule> list = sCService.s_class_chedule(uId);
        List<String> myschedule = new ArrayList<>();
        for(int i=0;i<84;i++)
        {
           myschedule.add("-1");
        }
        for (int i=0;i< list.size();i++) {
           // System.out.println(list.get(i).getTime());
            String[] s = list.get(i).getTime().split(",");
            for (String s1 : s) {
                if(!s1.equals(""))
                 myschedule.set(Integer.parseInt(s1), String.valueOf(i));
            }
        }
        model.addAttribute("list",list);
        model.addAttribute("myschedule",myschedule);
        System.out.println(list.size());
        System.out.println(list);
        System.out.println(myschedule);
        //System.out.println(page);
        return "/s_class_chedule";
    }
}
