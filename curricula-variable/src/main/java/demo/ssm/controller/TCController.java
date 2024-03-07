package demo.ssm.controller;

import com.github.pagehelper.PageInfo;
import demo.ssm.pojo.TC;
import demo.ssm.service.TCService;
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
public class TCController {

    @Autowired
    private TCService tCService;

    @RequestMapping(value = "/select/tc_info/{pageNum}",method = RequestMethod.GET)
    public String select_tc_info(@PathVariable("pageNum") String pageNum, Model model,TC tc)
    {
        PageInfo<TC> page = tCService.getTCPage(pageNum,tc);
        model.addAttribute("page",page);
        System.out.println(page);
        return "/tc_info_all";
    }

    @RequestMapping(value = "/select/t_application/{pageNum}",method = RequestMethod.GET)
    public String select_t_application(@PathVariable("pageNum") String pageNum, Model model,TC tc)
    {
        PageInfo<TC> page = tCService.getTCPageWherenoDeal(pageNum,tc);
        model.addAttribute("page",page);
        //System.out.println(page);
        return "/t_application";
    }

    @RequestMapping(value = "/delete/tc_info",method = RequestMethod.POST)
    public String delete_tc_info(TC tc)
    {
        System.out.println(tc);
        tCService.deletTC(tc);
        return "redirect:/select/tc_info/1";
    }
    @RequestMapping(value = "/update/t_application",method = RequestMethod.POST)
    public String t_application_update(TC tc)
    {
        tCService.updateTC(tc);
        return "redirect:/select/t_application/1";
    }
    @RequestMapping(value = "/delete/t_mycourse_delete",method = RequestMethod.POST)
    public String delete_t_mycourse_delete(TC tc)
    {
        tCService.deletTC(tc);
        return "redirect:/select/t_mycourse/1";
    }
    @RequestMapping(value = "/to/insert/t_mycourse_add",method = RequestMethod.GET)
    public String to_insert_t_mycourse()
    {
        return "/t_mycourse_add";
    }
    @RequestMapping(value = "/insert/t_mycourse_add",method = RequestMethod.POST)
    public String insert_t_mycourse(TC tc,HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        String uId = session.getAttribute("uId").toString();
        tc.settNo(uId);
        tCService.addTC(tc);
        return "redirect:/select/t_mycourse/1";
    }
    @RequestMapping(value = "/select/t_mycourse/{pageNum}",method = RequestMethod.GET)
    public String select_t_mycourse(@PathVariable("pageNum") String pageNum, Model model, HttpServletRequest request, HttpServletResponse response)
    {
        HttpSession session = request.getSession();
        String uId = session.getAttribute("uId").toString();
        TC tc = new TC();
        tc.settNo(uId);
        PageInfo<TC> page = tCService.getTCPage(pageNum,tc);
        model.addAttribute("page",page);
        //System.out.println(page);
        return "/t_mycourse";
    }
}
