package dlnu.workload.module.experiment.model;

import java.io.Serializable;

import dlnu.workload.module.common.model.User;

public class ExpActTeacher implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id = null;
	private ExperimentAccount expAccount = null;
	private User teacher = null;
	private double pWorkload = 0.0;
	private double wWorkload = 0.0;
	private double cWorkload = 0.0;
	private double period = 0.0;

	public ExpActTeacher() {
	}

	public String toString() {
		return "[ id:" + this.id + " expAccount:" + this.expAccount
				+ " teacher:" + this.teacher + " pWorkload:" + this.pWorkload
				+ " wWorkload:" + this.wWorkload + " cWorkload:"
				+ this.cWorkload + " period:" + this.period + "]";
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

	public User getTeacher() {
		return teacher;
	}

	public void setTeacher(User teacher) {
		this.teacher = teacher;
	}

	public double getpWorkload() {
		return pWorkload;
	}

	public void setpWorkload(double pWorkload) {
		this.pWorkload = pWorkload;
	}

	public double getwWorkload() {
		return wWorkload;
	}

	public void setwWorkload(double wWorkload) {
		this.wWorkload = wWorkload;
	}

	public double getcWorkload() {
		return cWorkload;
	}

	public void setcWorkload(double cWorkload) {
		this.cWorkload = cWorkload;
	}

	public double getPeriod() {
		return period;
	}

	public void setPeriod(double period) {
		this.period = period;
	}

}
