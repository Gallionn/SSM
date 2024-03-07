package demo.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import demo.ssm.mapper.SCMapper;
import demo.ssm.pojo.*;
import demo.ssm.service.SCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SCServiceImpl implements SCService {

    @Autowired
    private SCMapper sCMapper;


    @Override
    public PageInfo<SC> getSCPage(String pageNum, String type,SC sc,Integer min,Integer max) {
        PageHelper.startPage(Integer.parseInt(pageNum),5);
            SCExample scExample = new SCExample();
            SCExample.Criteria criteria = scExample.createCriteria();
            if(sc.getsNo()!=null)
                criteria.andSNoLike("%"+sc.getsNo()+"%");
            if(sc.getcId()!=null)
                criteria.andCIdLike("%"+sc.getcId()+"%");
            if(sc.getInfo()!=null)
                criteria.andInfoLike("%"+sc.getInfo()+"%");
            if(min!=null)
                criteria.andGradeGreaterThanOrEqualTo(min);
            if(max!=null)
                criteria.andGradeLessThanOrEqualTo(max);
            if(type!=null)
                criteria.andScStatusLike("%"+type+"%");
            List<SC> list=sCMapper.selectByExample(scExample);
            PageInfo<SC> pageInfo = new PageInfo<>(list,5);
            return pageInfo;


    }

    @Override
    public void updateBysc(SC sc) {
        System.out.println(sc.getScStatus());
        System.out.println(sc.getsNo());
        System.out.println(sc.getcId());
        sCMapper.updateByPrimaryKeySelective(sc);
    }

    @Override
    public void addSc(SC sc) {
        System.out.println(sc);
        sc.setScStatus("未处理");
        sCMapper.insert(sc);
    }

    @Override
    public void deletesc(SC sc) {
        sCMapper.deleteByPrimaryKey(sc);
    }

    @Override
    public List<schedule> s_class_chedule(String sNo) {
        return sCMapper.myshedule(sNo);
    }

}
