package demo.ssm.service;

import com.github.pagehelper.PageInfo;
import demo.ssm.pojo.SC;
import demo.ssm.pojo.Student;
import demo.ssm.pojo.schedule;

import java.util.List;

public interface SCService {
    PageInfo<SC> getSCPage(String pageNum,String type,SC sc,Integer min,Integer max);
    public void updateBysc(SC sc);
    public void addSc(SC sc);

    public void deletesc(SC sc);
    public List<schedule> s_class_chedule(String sNo);
}
