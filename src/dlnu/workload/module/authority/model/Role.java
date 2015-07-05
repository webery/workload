package dlnu.workload.module.authority.model;

import java.io.Serializable;
import java.util.List;

/**
 * 用户角色(权限集合)
 * 
 * @author weber 2015.4.17
 */
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String roles; // 角色标识 程序中判断使用,如"admin"
	private String description; // 角色描述,UI界面显示使用
	private Integer available = 0; // 是否可用,如果不可用将不会添加给用户

	private List<Permission> permissions = null;

	public Role() {
	}

	public Role(Integer id) {
		this.id = id;
	}

	public Role(String roles, String description, Integer available) {
		this.roles = roles;
		this.description = description;
		this.available = available;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getroles() {
		return roles;
	}

	public void setroles(String roles) {
		this.roles = roles;
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

		Role role = (Role) o;

		if (id != null ? !id.equals(role.id) : role.id != null)
			return false;

		return true;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "Role{" + "id=" + id + ", role='" + roles + '\''
				+ ", description='" + description + '\'' + ", available="
				+ available + '}';
	}
}
