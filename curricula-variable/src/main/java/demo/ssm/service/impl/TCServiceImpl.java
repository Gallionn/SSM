package demo.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import demo.ssm.mapper.TCMapper;
import demo.ssm.pojo.Student;
import demo.ssm.pojo.TC;
import demo.ssm.pojo.TCExample;
import demo.ssm.service.TCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TCServiceImpl implements TCService {

    @Autowired
    private TCMapper tCMapper;

    @Override
    public void addTC(TC tc) {
        tc.setTcStatus("未处理");
        tCMapper.insert(tc);
    }

    @Override
    public void updateTC(TC tc) {

        System.out.println(tc.getTcStatus());
        System.out.println(tc.gettNo());
        System.out.println(tc.getcId());
        tCMapper.updateByPrimaryKeySelective(tc);
    }

    @Override
    public void deletTC(TC tc) {
        tCMapper.deleteByPrimaryKey(tc);
    }

    @Override
    public PageInfo<TC> getTCPage(String pageNum,TC tc) {

            TCExample tcExample =new TCExample();
            TCExample.Criteria criteria =tcExample.createCriteria();
            if(tc.getcId()!=null)
                criteria.andCIdLike("%"+tc.getcId()+"%");
            if(tc.getcNo()!=null)
               criteria.andCNoLike("%"+tc.getcNo()+"%");
            if(tc.gettNo()!=null)
               criteria.andTNoLike("%"+tc.gettNo()+"%");
            if(tc.getTcStatus()!=null)
               criteria.andTcStatusLike("%"+tc.getTcStatus()+"%");
            if(tc.getTime()!=null)
               criteria.andTimeLike("%"+tc.getTime()+"%");
            PageHelper.startPage(Integer.parseInt(pageNum),5);
            List<TC> list=tCMapper.selectByExample(tcExample);
            PageInfo<TC> pageInfo = new PageInfo<>(list,5);
            return pageInfo;

    }

    @Override
    public PageInfo<TC> getTCPageWherenoDeal(String pageNum,TC tc) {

            PageHelper.startPage(Integer.parseInt(pageNum),5);
            TCExample tcExample =new TCExample();
        TCExample.Criteria criteria =tcExample.createCriteria();
        criteria.andTcStatusLike("%"+"未处理"+"%");
        if(tc.getcId()!=null)
            criteria.andCIdLike("%"+tc.getcId()+"%");
        if(tc.getcNo()!=null)
            criteria.andCNoLike("%"+tc.getcNo()+"%");
        if(tc.gettNo()!=null)
            criteria.andTNoLike("%"+tc.gettNo()+"%");
        if(tc.getTime()!=null)
            criteria.andTimeLike("%"+tc.getTime()+"%");
            List<TC> list=tCMapper.selectByExample(tcExample);
            PageInfo<TC> pageInfo = new PageInfo<>(list,5);
            return pageInfo;

    }
}
