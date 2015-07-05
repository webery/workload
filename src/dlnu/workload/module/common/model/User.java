package dlnu.workload.module.common.model;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;

import dlnu.workload.module.theoryteaching.model.CourseAccount;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "{user.id.null}")
	@Length(min = 5, max = 15, message = "{user.id.length.illegal}")
	@Pattern(regexp = "[A-Za-z0-9]{5,15}", message = "{user.id.illegal}")
	private String id = null;

	@NotEmpty(message = "{user.name.null}")
	@Length(min = 1, max = 20, message = "{user.name.length.illegal}")
	private String name = null;

	@NotNull(message = "{user.college.null}")
	private College college = null;

	@NotNull(message = "{user.major.null}")
	private Major major = null;

	@NotNull(message = "{user.teacherTitle.null}")
	private TeacherTitle teacherTitle = null;

	@NumberFormat(pattern = "^(\\d|[1-9]\\d+)(\\.\\d+)?$")
	private double workload = 0;

	@NumberFormat(pattern = "^(\\d|[1-9]\\d+)(\\.\\d+)?$")
	private double price = 0;

	@NotNull(message = "user.remark.null}")
	@Length(min = 0, max = 100, message = "{user.remark.length.illegal}")
	private String remark = null;

	private Integer status = 0;

	private String password = null;

	private String salt = null;

	//
	private List<CourseAccount> courseAccounts = null;

	public User() {
	}

	public User(String id) {
		this.id = id;
	}

	public User(String id, String password) {
		this.id = id;
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public College getCollege() {
		return college;
	}

	public void setCollege(College college) {
		this.college = college;
	}

	public Major getMajor() {
		return major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}

	public TeacherTitle getTeacherTitle() {
		return teacherTitle;
	}

	public void setTeacherTitle(TeacherTitle teacherTitle) {
		this.teacherTitle = teacherTitle;
	}

	public double getWorkload() {
		return workload;
	}

	public void setWorkload(double workload) {
		this.workload = workload;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public List<CourseAccount> getCourseAccounts() {
		return courseAccounts;
	}

	public void setCourseAccounts(List<CourseAccount> courseAccounts) {
		this.courseAccounts = courseAccounts;
	}

	public String toString() {

		return "[ id:" + this.id + " name:" + this.name + " college:"
				+ this.college + " major:" + this.major + " teacherTitle:"
				+ this.teacherTitle + " workload:" + this.workload + " price:"
				+ this.price + " remark:" + this.remark + " status:"
				+ this.status + " password:" + this.password + " salt:"
				+ this.salt + "]";
	}

}
