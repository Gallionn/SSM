package demo.ssm.service;

import com.github.pagehelper.PageInfo;
import demo.ssm.pojo.Course;
import demo.ssm.pojo.Student;

public interface CourseService {
    public PageInfo<Course> getCoursePage(String pageNum,Course course,Integer min,Integer max);
    public void addCourse(Course course);
    public void updateCourse(Course course);
    public void deleteCourse(Course course);

    Course getCourse(String cNo);
    //public void getAllcourse();
}
