package dlnu.workload.module.authority.model;

import java.io.Serializable;

/**
 * 用户权限
 * 
 * @author weber 2015.4.17
 */
public class Permission implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String permissions; // 权限标识 程序中判断使用,如"user:create"
	private String description; // 权限描述,UI界面显示使用
	private Integer available = 0; // 是否可用,如果不可用将不会添加给用户

	public Permission() {
	}

	public Permission(String permissions, String description, Integer available) {
		this.permissions = permissions;
		this.description = description;
		this.available = available;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getpermissions() {
		return permissions;
	}

	public void setpermissions(String permissions) {
		this.permissions = permissions;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getAvailable() {
		return available;
	}

	public void setAvailable(Integer available) {
		this.available = available;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Permission role = (Permission) o;

		if (id != null ? !id.equals(role.id) : role.id != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "Role{" + "id=" + id + ", permission='" + permissions + '\''
				+ ", description='" + description + '\'' + ", available="
				+ available + '}';
	}
}
