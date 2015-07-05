package dlnu.workload.module.theoryteaching.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.NumberFormat;

import dlnu.workload.module.common.model.User;

public class CourseAccount implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id = null;
	private String semester = null;

	@NotNull(message = "{courseAccount.course.null}")
	private Course course = null;

	private String courseName = null;
	private User teacher = null;

	@NotEmpty(message = "{courseAccount.student.null}")
	@Length(min = 1, max = 15, message = "{courseAccount.student.length.illegal}")
	private String student = null;

	@NotNull(message = "{courseAccount.courseNum.null}")
	@Range(min = 0, max = 10000, message = "{courseAccount.courseNum.illegal}")
	private Integer courseNum = 0;

	@NotNull(message = "{courseAccount.courseRepnum.null}")
	@Range(min = 0, max = 10000, message = "{courseAccount.courseRepnum.illegal}")
	private Integer courseRepnum = 0;

	@NotNull(message = "{courseAccount.classHour.null}")
	@Range(min = 0, max = 10000, message = "{courseAccount.classHour.illegal}")
	private Integer classHour = 0;

	@NumberFormat(pattern = "^(\\d|[1-9]\\d+)(\\.\\d+)?$")
	private double typeFactor = 0.0;

	@NumberFormat(pattern = "^(\\d|[1-9]\\d+)(\\.\\d+)?$")
	private double repFactor = 0.0;

	public String getCampus() {
		return campus;
	}

	public void setCampus(String campus) {
		this.campus = campus;
	}

	private double workload = 0.0;

	@NotEmpty(message = "{courseAccount.campus.null}")
	@Length(min = 1, max = 20, message = "{courseAccount.campus.length.illegal}")
	private String campus = null;

	private Integer status = 0;

	public CourseAccount() {
	}

	public CourseAccount(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public User getTeacher() {
		return teacher;
	}

	public void setTeacher(User teacher) {
		this.teacher = teacher;
	}

	public String getStudent() {
		return student;
	}

	public void setStudent(String student) {
		this.student = student;
	}

	public Integer getCourseNum() {
		return courseNum;
	}

	public void setCourseNum(Integer courseNum) {
		this.courseNum = courseNum;
	}

	public Integer getCourseRepnum() {
		return courseRepnum;
	}

	public void setCourseRepnum(Integer courseRepnum) {
		this.courseRepnum = courseRepnum;
	}

	public Integer getClassHour() {
		return classHour;
	}

	public void setClassHour(Integer classHour) {
		this.classHour = classHour;
	}

	public double getTypeFactor() {
		return typeFactor;
	}

	public void setTypeFactor(double typeFactor) {
		this.typeFactor = typeFactor;
	}

	public double getRepFactor() {
		return repFactor;
	}

	public void setRepFactor(double repFactor) {
		this.repFactor = repFactor;
	}

	public double getWorkload() {
		return workload;
	}

	public void setWorkload(double workload) {
		this.workload = workload;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {

		return "[ id:" + this.id + " semester:" + this.semester + " course:"
				+ this.course + " courseName:" + this.courseName + " teacher:"
				+ this.teacher + " staudent:" + this.student + " courseNum:"
				+ this.courseNum + " courseRepnum:" + this.courseRepnum
				+ " classHour;" + this.classHour + " typeFactor:"
				+ this.typeFactor + " repFactor:" + this.repFactor
				+ " workload:" + this.workload + " campus:" + this.campus
				+ " status:" + this.status + "]";
	}

}
