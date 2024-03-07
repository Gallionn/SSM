package demo.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import demo.ssm.mapper.CourseMapper;
import demo.ssm.pojo.Course;
import demo.ssm.pojo.CourseExample;
import demo.ssm.pojo.Student;
import demo.ssm.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public PageInfo<Course> getCoursePage(String pageNum,Course course,Integer min,Integer max) {


           CourseExample courseExample = new CourseExample();
           CourseExample.Criteria criteria =courseExample.createCriteria();
           if(course.getcNo()!=null)
               criteria.andCNoLike("%"+course.getcNo()+"%");
           if(course.getcName()!=null)
               criteria.andCNameLike("%"+course.getcName()+"%");
           if(course.getcPno()!=null)
               criteria.andCPnoLike("%"+course.getcPno()+"%");
           if(min!=null)
               criteria.andCCreditGreaterThanOrEqualTo(min);
           if(max!=null)
               criteria.andCCreditLessThanOrEqualTo(max);

           PageHelper.startPage(Integer.parseInt(pageNum),5);
           List<Course> list=courseMapper.selectByExample(courseExample);
           PageInfo<Course> pageInfo = new PageInfo<>(list,5);
           return pageInfo;
    }

    @Override
    public void addCourse(Course course) {
        courseMapper.insert(course);
    }

    @Override
    public void updateCourse(Course course) {
        courseMapper.updateByPrimaryKeySelective(course);
    }

    @Override
    public void deleteCourse(Course course) {
        courseMapper.deleteByPrimaryKey(course.getcNo());
    }

    @Override
    public Course getCourse(String cNo) {

        return courseMapper.selectByPrimaryKey(cNo);
    }
}
