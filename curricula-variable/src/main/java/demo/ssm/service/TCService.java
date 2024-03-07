package demo.ssm.service;

import com.github.pagehelper.PageInfo;
import demo.ssm.pojo.Student;
import demo.ssm.pojo.TC;

public interface TCService {
    public void addTC(TC tc);
    public void updateTC(TC tc);
    public void deletTC(TC tc);

    public PageInfo<TC> getTCPage(String pageNum,TC tc);
    public PageInfo<TC> getTCPageWherenoDeal(String pageNum,TC tc);
}
