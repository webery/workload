package dlnu.workload.module.authority.model;

import java.io.Serializable;

/**
 * 用户角色关系
 * @author weber
 *2015.4.18
 */

public class RolePermission implements Serializable {


	private static final long serialVersionUID = 1L;
	private Role role;
    private Permission permission;
    
    public RolePermission() {
    }

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}
  
}
