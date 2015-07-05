package dlnu.workload.module.experiment.model;

import java.io.Serializable;

public class ExperimentItem implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id = null;
	private ExperimentAccount expAccount = null;
	private String student = null;
	private double period = 0.0;
	private double operiod = 0.0;
	private Integer courseNum = 0;
	private Integer courseRepnum = 0;
	private Integer classStunum = 0;
	private double classTime = 0.0;
	private double factor = 0.0;
	private double workload = 0.0;

	public ExperimentItem() {
	}

	public ExperimentItem(String id) {
		this.id = id;
	}

	public String toString() {

		return "[ id:" + this.id + " expAccount:" + this.expAccount
				+ " student:" + this.student + " period:" + this.period
				+ " operiod:" + this.operiod + " courseNum:" + this.courseNum
				+ " courseRepnum:" + this.courseRepnum + " classStunum:"
				+ this.classStunum + " classTime:" + this.classTime
				+ " factor:" + this.factor + " workload:" + this.workload
				+ " ]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ExperimentAccount getExpAccount() {
		return expAccount;
	}

	public void setExpAccount(ExperimentAccount expAccount) {
		this.expAccount = expAccount;
	}

	public String getStudent() {
		return student;
	}

	public void setStudent(String student) {
		this.student = student;
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

}
