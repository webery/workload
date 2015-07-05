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
 * 毕业设计工作量申请
 * 
 * @author weber 2015.4.24
 */
public class GrdtdesignAccount implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id = null;
	private String semester = null;

	@NotNull(message = "{grdtdesignAccount.major.null}")
	private Major major = null;// 专业

	@NotEmpty(message = "{grdtdesignAccount.grade.null}")
	@Length(min = 4, max = 4, message = "{grdtdesignAccount.grade.length.illegal}")
	@Pattern(regexp = "[0-9]{4,4}", message = "{grdtdesignAccount.grade.illegal}")
	private String grade = null;// 年级

	@NotNull(message = "{grdtdesignAccount.classNum.null}")
	@Range(min = 0, max = 100, message = "{grdtdesignAccount.classNum.illegal}")
	private Integer classNum = 0;// 班级数

	@NotNull(message = "{grdtdesignAccount.stuNum.null}")
	@Range(min = 0, max = 10000, message = "{grdtdesignAccount.stuNum.illegal}")
	private Integer stuNum = 0;

	@NotNull(message = "{grdtdesignAccount.weekNum.null}")
	@Range(min = 0, max = 100, message = "{grdtdesignAccount.weekNum.illegal}")
	private Integer weekNum = 0;

	@NumberFormat(pattern = "^(\\d|[1-9]\\d+)(\\.\\d+)?$")
	private double factor = 0.0;

	@NumberFormat(pattern = "^(\\d|[1-9]\\d+)(\\.\\d+)?$")
	private double workload = 0.0;

	@NumberFormat(pattern = "^(\\d|[1-9]\\d+)(\\.\\d+)?$")
	private double period = 0;// 计划学时

	@NotEmpty(message = "{grdtdesignAccount.campus.null}")
	@Length(min = 1, max = 15, message = "{grdtdesignAccount.campus.length.illegal}")
	private String campus = null;

	private User creator = null;
	private Date createDate = null;

	@NotNull(message = "{grdtdesignAccount.remark.null}")
	@Length(min = 0, max = 100, message = "{grdtdesignAccount.remark.length.illegal}")
	private String remark = null;

	private Integer status = 0;

	//
	private List<GrdtdesignActTeacher> grdtdesignActTeachers = null;

	public GrdtdesignAccount() {
	}

	public GrdtdesignAccount(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "[ id:" + this.id + " semester:" + this.semester + " major:"
				+ this.getMajor() + " grade:" + this.grade + " classNum:"
				+ this.classNum + "stuNum:" + this.stuNum + " weekNum:"
				+ this.weekNum + " factor:" + this.factor + " workload:"
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

	public Integer getClassNum() {
		return classNum;
	}

	public void setClassNum(Integer classNum) {
		this.classNum = classNum;
	}

	public Integer getStuNum() {
		return stuNum;
	}

	public void setStuNum(Integer stuNum) {
		this.stuNum = stuNum;
	}

	public Integer getWeekNum() {
		return weekNum;
	}

	public void setWeekNum(Integer weekNum) {
		this.weekNum = weekNum;
	}

	public double getFactor() {
		return factor;
	}

	public void setFactor(double factor) {
		this.factor = factor;
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

	public double getPeriod() {
		return period;
	}

	public void setPeriod(double period) {
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

	public List<GrdtdesignActTeacher> getGrdtdesignActTeachers() {
		return grdtdesignActTeachers;
	}

	public void setGrdtdesignActTeachers(
			List<GrdtdesignActTeacher> grdtdesignActTeachers) {
		this.grdtdesignActTeachers = grdtdesignActTeachers;
	}

}
