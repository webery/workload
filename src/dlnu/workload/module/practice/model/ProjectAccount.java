package dlnu.workload.module.practice.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;

import dlnu.workload.module.common.model.User;

public class ProjectAccount implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id = null;
	private String semester = null;
	private User creator = null;

	@NotEmpty(message = "{projectAccount.year.null}")
	@Length(min = 4, max = 4, message = "{projectAccount.year.length.illegal}")
	@Pattern(regexp = "[0-9]{4,4}", message = "{projectAccount.year.illegal}")
	private String year = null;

	@NotNull(message = "{projectAccount.spcode.null}")
	@Length(min = 0, max = 10, message = "{projectAccount.spcode.illegal}")
	@Pattern(regexp = "[A-Za-z0-9]{0,10}", message = "{projectAccount.spcode.illegal}")
	private String spcode = null;

	@NumberFormat(pattern = "^(\\d|[1-9]\\d+)(\\.\\d+)?$")
	private double sworkload = 0.0;

	@NotNull(message = "{projectAccount.mpcode.null}")
	@Length(min = 0, max = 10, message = "{projectAccount.mpcode.illegal}")
	@Pattern(regexp = "[A-Za-z0-9]{0,10}", message = "{projectAccount.mpcode.illegal}")
	private String mpcode = null;

	@NumberFormat(pattern = "^(\\d|[1-9]\\d+)(\\.\\d+)?$")
	private double mworkload = 0.0;

	@NumberFormat(pattern = "^(\\d|[1-9]\\d+)(\\.\\d+)?$")
	private double mrworkload = 0.0;

	@NotNull(message = "{projectAccount.remark.null}")
	@Length(min = 0, max = 100, message = "{projectAccount.remark.length.illegal}")
	private String remark = null;

	private Date createDate = null;

	public ProjectAccount() {
	}

	public ProjectAccount(String id) {
		this.id = id;
	}

	public String toString() {

		return "[ id:" + this.id + " semester:" + this.semester + " creator:"
				+ this.creator + " year:" + this.year + " spcode:"
				+ this.spcode + " sworkload:" + this.sworkload + " mpcode:"
				+ this.mpcode + " mworkload:" + this.mworkload + " mrworkload:"
				+ this.mrworkload + " remark:" + this.remark + " createDate:"
				+ this.createDate + "]";
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

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getSpcode() {
		return spcode;
	}

	public void setSpcode(String spcode) {
		this.spcode = spcode;
	}

	public double getSworkload() {
		return sworkload;
	}

	public void setSworkload(double sworkload) {
		this.sworkload = sworkload;
	}

	public String getMpcode() {
		return mpcode;
	}

	public void setMpcode(String mpcode) {
		this.mpcode = mpcode;
	}

	public double getMworkload() {
		return mworkload;
	}

	public void setMworkload(double mworkload) {
		this.mworkload = mworkload;
	}

	public double getMrworkload() {
		return mrworkload;
	}

	public void setMrworkload(double mrworkload) {
		this.mrworkload = mrworkload;
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

}
