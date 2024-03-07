package demo.ssm.service;

import com.github.pagehelper.PageInfo;
import demo.ssm.mapper.TeacherMapper;
import demo.ssm.pojo.Teacher;

public interface TeacherService {
    public PageInfo<Teacher> getTeacherPage(String pageNum,Teacher teacher,Integer min,Integer max);

    public void addTeacher(Teacher teacher);

    public void updateTeacher(Teacher teacher);
    public void deleteTeacher(Teacher teacher);

    public  Teacher getTeacher(String uId);
}
