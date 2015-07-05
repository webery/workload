package dlnu.workload.module.common.model;

import java.io.Serializable;

public class TeacherTitle implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id = null;
	private String name = null;
	private Integer workload = 0;
	private Integer price = 0;
	private Integer status = 0;

	public TeacherTitle() {
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

	public Integer getWorkload() {
		return workload;
	}

	public void setWorkload(Integer workload) {
		this.workload = workload;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {

		return "[ id:" + " name:" + this.name + " workload:" + this.workload
				+ " price:" + this.price + " status:" + this.status + " ]";
	}
}
