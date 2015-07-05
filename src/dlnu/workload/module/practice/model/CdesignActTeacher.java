package dlnu.workload.module.practice.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.NumberFormat;

import dlnu.workload.module.common.model.User;

/**
 * 课程设计包含的教师
 * 
 * @author weber 2015.4.24
 *
 */
public class CdesignActTeacher implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id = null;

	private CdesignAccount cdesignAccount = null;

	@NotNull(message = "{cdesignActTeacher.teacher.null}")
	private User teacher = null;

	@NumberFormat(pattern = "^(\\d|[1-9]\\d+)(\\.\\d+)?$")
	private double workload = 0.0;

	@NumberFormat(pattern = "^(\\d|[1-9]\\d+)(\\.\\d+)?$")
	private double period = 0.0;

	@NotNull(message = "cdesignActTeacher.remark.null}")
	@Length(min = 0, max = 100, message = "{cdesignActTeacher.remark.length.illegal}")
	private String remark = null;

	private Integer status = 0;

	public CdesignActTeacher() {
	}

	public CdesignActTeacher(String id) {
		this.id = id;
	}

	@Override
	public String toString() {

		return "[ id:" + this.id + " cdesignAccount:" + this.cdesignAccount
				+ " teacher:" + this.teacher + " workload:" + this.workload
				+ " period:" + this.period + " remark:" + this.remark
				+ " status:" + this.status + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public CdesignAccount getCdesignAccount() {
		return cdesignAccount;
	}

	public void setCdesignAccount(CdesignAccount cdesignAccount) {
		this.cdesignAccount = cdesignAccount;
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

	public double getPeriod() {
		return period;
	}

	public void setPeriod(double period) {
		this.period = period;
	}

}
