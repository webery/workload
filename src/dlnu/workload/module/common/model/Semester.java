package dlnu.workload.module.common.model;

import java.io.Serializable;

public class Semester implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id = null;
	private String name = null;
	private Integer status = 0;

	public Semester() {
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {

		return "[ id:" + " name:" + this.name + " status:" + this.status + " ]";
	}

}
