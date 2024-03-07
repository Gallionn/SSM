package demo.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import demo.ssm.mapper.StudentMapper;
import demo.ssm.mapper.UserMapper;
import demo.ssm.pojo.SC;
import demo.ssm.pojo.Student;
import demo.ssm.pojo.StudentExample;
import demo.ssm.pojo.User;
import demo.ssm.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private UserMapper userMapper;

    public PageInfo<Student> getStudentPage(String pageNum,Student student,Integer min,Integer max) {

           StudentExample studentExample = new StudentExample();
           StudentExample.Criteria criteria =studentExample.createCriteria();
           if(student.getsNo()!=null)
               criteria.andSNoLike("%"+student.getsNo()+"%");
           if(student.getsName()!=null)
               criteria.andSNameLike("%"+student.getsName()+"%");
           if(student.getsDept()!=null)
               criteria.andSDeptLike("%"+student.getsDept()+"%");
           if(student.getsSex()!=null)
               criteria.andSSexLike("%"+student.getsSex()+"%");
           if(min!=null)
               criteria.andSAgeGreaterThanOrEqualTo(min);
           if(max!=null)
               criteria.andSAgeLessThanOrEqualTo(max);
           PageHelper.startPage(Integer.parseInt(pageNum),5);
           List<Student> list=studentMapper.selectByExample(studentExample);
           System.out.println(list.get(0));
           PageInfo<Student> pageInfo = new PageInfo<>(list,5);
           return pageInfo;

    }

    @Override
    public void addStudent(Student student) {
         studentMapper.insert(student);
         User user = new User();
         user.setuFlag("student");
         user.setuId(student.getsNo());
         user.setuPassword(student.getsNo().substring(student.getsNo().length()-3));
         System.out.println(student);
         System.out.println(user);
         userMapper.insert(user);

    }

    @Override
    public void updateStudent(Student student) {
        studentMapper.updateByPrimaryKeySelective(student);
    }

    @Override
    public void deleteStudent(Student student) {
        System.out.println(student.getsNo());
        studentMapper.deleteByPrimaryKey(student.getsNo());
        userMapper.deleteByPrimaryKey(student.getsNo());
    }

    @Override
    public Student getStudent(String uId) {
        return studentMapper.selectByPrimaryKey(uId);
    }

}
