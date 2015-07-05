package dlnu.workload.module.practice.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.NumberFormat;

import dlnu.workload.module.common.model.Major;
import dlnu.workload.module.common.model.User;

/**
 * 
 * 课程设计工作量
 * 
 * @author weber 2015.4.24
 *
 */
public class CdesignAccount implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id = null;
	private String semester = null;

	private CourseDesign courseDesign = null;
	private String coursedesignName = null;

	@NotNull(message = "{cdesignAccount.major.null}")
	private Major major = null;

	@NotEmpty(message = "{cdesignAccount.grade.null}")
	@Length(min = 4, max = 4, message = "{cdesignAccount.grade.length.illegal}")
	@Pattern(regexp = "[0-9]{4,4}", message = "{cdesignAccount.grade.illegal}")
	private String grade = null;

	@NotNull(message = "{cdesignAccount.classNum.null}")
	@Range(min = 0, max = 100, message = "{cdesignAccount.classNum.illegal}")
	private Integer classNum = 0;

	@NotNull(message = "{cdesignAccount.weekNum.null}")
	@Range(min = 0, max = 100, message = "{cdesignAccount.weekNum.illegal}")
	private Integer weekNum = 0;

	@NotNull(message = "{cdesignAccount.preday.null}")
	@Range(min = 0, max = 100, message = "{cdesignAccount.preday.illegal}")
	private Integer preday = 0;

	@NumberFormat(pattern = "^(\\d|[1-9]\\d+)(\\.\\d+)?$")
	private double workload = 0.0;

	@NumberFormat(pattern = "^(\\d|[1-9]\\d+)(\\.\\d+)?$")
	private Integer period = 0;

	@NotEmpty(message = "{cdesignAccount.campus.null}")
	@Length(min = 1, max = 15, message = "{cdesignAccount.campus.length.illegal}")
	private String campus = null;

	private User creator = null;
	private Date createDate = null;

	@NotNull(message = "{cdesignAccount.remark.null}")
	@Length(min = 0, max = 100, message = "{cdesignAccount.remark.length.illegal}")
	private String remark = null;

	private Integer status = 0;

	//
	private List<CdesignActTeacher> cdesignActTeachers = null;

	public CdesignAccount() {
	}

	public CdesignAccount(String id) {
		this.id = id;
	}

	@Override
	public String toString() {

		return "[ id:" + this.id + " semester:" + this.semester
				+ " courseDesign:" + this.courseDesign + " coursedesignName:"
				+ this.coursedesignName + " major:" + this.major + " grade:"
				+ this.grade + " classNum:" + this.classNum + " weekNum:"
				+ this.weekNum + " preday:" + this.preday + " workload :"
				+ this.workload + " campus:" + this.campus + " period:"
				+ this.period + " creator:" + this.creator + " createDate:"
				+ this.createDate + " remark:" + this.remark + " status:"
				+ this.status + "]";
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

	public CourseDesign getCourseDesign() {
		return courseDesign;
	}

	public void setCourseDesign(CourseDesign courseDesign) {
		this.courseDesign = courseDesign;
	}

	public String getCoursedesignName() {
		return coursedesignName;
	}

	public void setCoursedesignName(String coursedesignName) {
		this.coursedesignName = coursedesignName;
	}

	public Major getMajor() {
		return major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Integer getClassNum() {
		return classNum;
	}

	public void setClassNum(Integer classNum) {
		this.classNum = classNum;
	}

	public Integer getWeekNum() {
		return weekNum;
	}

	public void setWeekNum(Integer weekNum) {
		this.weekNum = weekNum;
	}

	public Integer getPreday() {
		return preday;
	}

	public void setPreday(Integer preday) {
		this.preday = preday;
	}

	public double getWorkload() {
		return workload;
	}

	public void setWorkload(double workload) {
		this.workload = workload;
	}

	public String getCampus() {
		return campus;
	}

	public void setCampus(String campus) {
		this.campus = campus;
	}

	public Integer getPeriod() {
		return period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<CdesignActTeacher> getCdesignActTeachers() {
		return cdesignActTeachers;
	}

	public void setCdesignActTeachers(List<CdesignActTeacher> cdesignActTeachers) {
		this.cdesignActTeachers = cdesignActTeachers;
	}

}
