package demo.ssm.service;

import com.github.pagehelper.PageInfo;
import demo.ssm.pojo.SC;
import demo.ssm.pojo.Student;

public interface StudentService {
    public PageInfo<Student> getStudentPage(String pageNum,Student student,Integer min,Integer max);

    public void addStudent(Student student);

    public void updateStudent(Student student);
    public void deleteStudent(Student student);
    public Student getStudent(String uId);
}
