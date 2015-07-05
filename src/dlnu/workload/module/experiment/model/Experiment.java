package dlnu.workload.module.experiment.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import dlnu.workload.module.common.model.College;
import dlnu.workload.module.common.model.User;

/**
 * 实验上机
 * 
 * @author weber
 *
 */
public class Experiment implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id = null;

	@NotEmpty(message = "{experiment.name.null}")
	@Length(min = 1, max = 15, message = "{experiment.name.length.illegal}")
	private String name = null;

	private College college = null;

	@NotNull(message = "{experiment.remark.null}")
	@Length(min = 0, max = 150, message = "{experiment.remark.length.illegal}")
	private String remark = null;

	private User creator = null;

	// @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createDate = null;

	private Integer status = 0;
	
	public Experiment() {
	}

	public Experiment(String id) {
		this.id = id;
	}

	public Experiment(College college, String name) {
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}
	

	@Override
	public String toString() {

		return "[id:" + this.id + " name:" + this.name + " college:"
				+ this.college + " remark:" + this.remark + " creator:"
				+ this.creator + " createDate:" + this.createDate + " status:"
				+ this.status + "]";
	}

}
