package dlnu.workload.module.practice.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.NumberFormat;

import dlnu.workload.module.common.model.User;

/**
 * 毕业设计包含的老师
 * 
 * @author weber 2015.4.24
 *
 */
public class GrdtdesignActTeacher implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id = null;

	private GrdtdesignAccount grdtdesignAccount = null;

	@NotNull(message = "{grdtdesignActTeacher.teacher.null}")
	private User teacher = null;

	@NumberFormat(pattern = "^(\\d|[1-9]\\d+)(\\.\\d+)?$")
	private double workload = 0.0;

	@NumberFormat(pattern = "^(\\d|[1-9]\\d+)(\\.\\d+)?$")
	private double period = 0;

	@NotNull(message = "grdtdesignActTeacher.remark.null}")
	@Length(min = 0, max = 100, message = "{grdtdesignActTeacher.remark.length.illegal}")
	private String remark = null;

	private Integer status = 0;

	//
	private CourseDesign courseDesign = null;

	public GrdtdesignActTeacher() {
	}

	@Override
	public String toString() {

		return "[ id:" + this.id + " grdtdesignAccount:"
				+ this.grdtdesignAccount + " teacher:" + this.teacher
				+ " workload:" + this.workload + " remark:" + " period:"
				+ this.period + this.remark + " status:" + this.status + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public GrdtdesignAccount getGrdtdesignAccount() {
		return grdtdesignAccount;
	}

	public void setGrdtdesignAccount(GrdtdesignAccount grdtdesignAccount) {
		this.grdtdesignAccount = grdtdesignAccount;
	}

	public User getTeacher() {
		return teacher;
	}

	public void setTeacher(User teacher) {
		this.teacher = teacher;
	}

	public double getWorkload() {
		return workload;
	}

	public void setWorkload(double workload) {
		this.workload = workload;
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

	//
	public CourseDesign getCourseDesign() {
		return courseDesign;
	}

	public void setCourseDesign(CourseDesign courseDesign) {
		this.courseDesign = courseDesign;
	}

	public double getPeriod() {
		return period;
	}

	public void setPeriod(double period) {
		this.period = period;
	}

}
