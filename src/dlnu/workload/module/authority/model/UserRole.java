package dlnu.workload.module.authority.model;

import java.io.Serializable;

import dlnu.workload.module.common.model.User;

/**
 * 用户角色关系
 * 
 * @author weber 2015.4.17
 *
 */
public class UserRole implements Serializable {

	private static final long serialVersionUID = 1L;
	private User User = null;
	private Role role = null;

	public UserRole() {
	}

	public User getUser() {
		return User;
	}

	public void setUser(User user) {
		User = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
