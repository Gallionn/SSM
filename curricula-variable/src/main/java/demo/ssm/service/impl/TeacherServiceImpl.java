package demo.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import demo.ssm.mapper.TeacherMapper;
import demo.ssm.mapper.UserMapper;
import demo.ssm.pojo.Teacher;
import demo.ssm.pojo.TeacherExample;
import demo.ssm.pojo.User;
import demo.ssm.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo<Teacher> getTeacherPage(String pageNum,Teacher teacher,Integer min,Integer max) {

            TeacherExample teacherExample = new TeacherExample();
            TeacherExample.Criteria criteria = teacherExample.createCriteria();
            if(teacher.gettNo()!=null)
                 criteria.andTNoLike("%"+teacher.gettNo()+"%");
             if(teacher.gettName()!=null)
                 criteria.andTNameLike("%"+teacher.gettName()+"%");
            if(teacher.gettDept()!=null)
                 criteria.andTDeptLike("%"+teacher.gettDept()+"%");
            if(teacher.gettSex()!=null)
                 criteria.andTSexLike("%"+teacher.gettSex()+"%");
            if(teacher.gettTel()!=null)
                 criteria.andTTelLike("%"+teacher.gettTel()+"%");
            if(min!=null)
                criteria.andTAgeGreaterThanOrEqualTo(min);
            if(max!=null)
                criteria.andTAgeLessThanOrEqualTo(max);
            PageHelper.startPage(Integer.parseInt(pageNum),5);
            List<Teacher> list=teacherMapper.selectByExample(teacherExample);
            PageInfo<Teacher> pageInfo = new PageInfo<>(list,5);
            return pageInfo;

    }

    @Override
    public void addTeacher(Teacher teacher) {
        teacherMapper.insert(teacher);
        User user = new User();
        user.setuFlag("teacher");
        user.setuId(teacher.gettNo());
        user.setuPassword(teacher.gettNo().substring(teacher.gettNo().length()-3));
        userMapper.insert(user);
    }

    @Override
    public void updateTeacher(Teacher teacher) {
            teacherMapper.updateByPrimaryKeySelective(teacher);
    }

    @Override
    public void deleteTeacher(Teacher teacher) {
        teacherMapper.deleteByPrimaryKey(teacher.gettNo());
        userMapper.deleteByPrimaryKey(teacher.gettNo());
    }

    @Override
    public Teacher getTeacher(String uId) {
        return teacherMapper.selectByPrimaryKey(uId);
    }


}
