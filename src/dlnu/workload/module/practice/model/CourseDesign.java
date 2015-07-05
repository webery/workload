package dlnu.workload.module.practice.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import dlnu.workload.module.common.model.College;
import dlnu.workload.module.common.model.User;

/**
 * 课程设计
 * 
 * @author weber 2015.4.24
 *
 */
public class CourseDesign implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id = null;
	@NotEmpty(message = "{CourseDesign.name.null}")
	@Length(min = 1, max = 15, message = "{CourseDesign.name.length.illegal}")
	private String name = null;

	private College college = null;
	@NotNull(message = "{CourseDesign.remark.null}")
	@Length(min = 0, max = 150, message = "{CourseDesign.remark.length.illegal}")
	private String remark = null;
	private User creator = null;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createDate = null;
	private Integer status = 0;

	public CourseDesign() {
	}

	public CourseDesign(String id) {
		this.id = id;
	}

	public CourseDesign(College college, String name) {
		this.college = college;
		this.name = name;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	@Override
	public String toString() {

		return "[id:" + this.id + " name:" + this.name + " college:"
				+ this.college + " remark:" + this.remark + " status:"
				+ this.status + "]";
	}

}
