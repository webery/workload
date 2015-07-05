package dlnu.workload.module.experiment.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import dlnu.workload.module.common.model.User;

public class ExperimentAccount implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id = null;
	private String semester = null;

	@NotNull(message = "{experimentAccount.courseNum.null}")
	private Experiment experiment = null;

	private String experimentName = null;
	private double period = 0.0;
	private double operiod = 0.0;
	private Integer courseNum = 0;
	private Integer courseRepnum = 0;
	private Integer classStunum = 0;
	private double classTime = 0.0;
	private double factor = 0.0;
	private double workload = 0.0;
	private User creator = null;
	private Date createDate = null;

	@NotEmpty(message = "{experimentAccount.campus.null}")
	@Length(min = 1, max = 20, message = "{experimentAccount.campus.length.illegal}")
	private String campus = null;

	private Integer status = 0;

	public ExperimentAccount() {
	}

	public ExperimentAccount(String id) {
		this.id = id;
	}

	public String toString() {

		return "[ id:" + this.id + " semester:" + this.semester
				+ " experiment:" + this.experiment + " experimentName:"
				+ this.experimentName + " period:" + this.period + " operiod:"
				+ this.operiod + " courseNum:" + this.courseNum
				+ " courseRepnum:" + this.courseRepnum + " classStunum:"
				+ this.classStunum + " classTime:" + this.classTime
				+ " factor:" + this.factor + " workload:" + this.workload
				+ " creator:" + this.creator + " createDate:" + this.createDate
				+ " campus:" + this.campus + " status:" + this.status + " ]";
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

	public Experiment getExperiment() {
		return experiment;
	}

	public void setExperiment(Experiment experiment) {
		this.experiment = experiment;
	}

	public String getExperimentName() {
		return experimentName;
	}

	public void setExperimentName(String experimentName) {
		this.experimentName = experimentName;
	}

	public double getPeriod() {
		return period;
	}

	public void setPeriod(double period) {
		this.period = period;
	}

	public double getOperiod() {
		return operiod;
	}

	public void setOperiod(double operiod) {
		this.operiod = operiod;
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

	public Integer getClassStunum() {
		return classStunum;
	}

	public void setClassStunum(Integer classStunum) {
		this.classStunum = classStunum;
	}

	public double getClassTime() {
		return classTime;
	}

	public void setClassTime(double classTime) {
		this.classTime = classTime;
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

	public String getCampus() {
		return campus;
	}

	public void setCampus(String campus) {
		this.campus = campus;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
