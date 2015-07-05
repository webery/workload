package dlnu.workload.framework.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;

import dlnu.workload.module.common.model.College;
import dlnu.workload.module.common.model.Major;
import dlnu.workload.module.common.model.User;

public class BaseController {

	/**
	 * 获取缓存的用户信息
	 * 
	 * @return
	 */
	public User getUser() {
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute("user");
		return user;
	}

	/**
	 * 获取缓存用户id
	 * 
	 * @return
	 */
	public String getUserId() {

		return this.getUser().getId();
	}

	public Major getMajor() {

		return this.getUser().getMajor();
	}

	/**
	 * 获取缓存用户专业id
	 * 
	 * @return
	 */
	public String getMajorId() {

		return this.getUser().getMajor().getId();
	}

	public College getCollege() {

		return this.getUser().getCollege();
	}

	/**
	 * 获取缓存用户学院id
	 * 
	 * @return
	 */
	public String getCollegeId() {

		return this.getUser().getCollege().getId();
	}

	/**
	 * 测试是否本用户
	 * 
	 * @return
	 */
	public void checkUserId(String userId) {

		if (!this.getUser().getId().equals(userId)) {
			throw new AuthorizationException("无权限操作!");
		}
	}

	/**
	 * 测试是否是当前登陆用户专业
	 * 
	 * @return
	 */
	public void checkMajor(String majorId) {
		if (!this.getUser().getMajor().getId().equals(majorId)) {
			throw new AuthorizationException("无权限操作!");
		}
	}

	/**
	 * 测试是否是当前登陆用户学院
	 * 
	 * @return
	 */
	public void checkCollege(String collegeId) {
		if (!this.getUser().getCollege().getId().endsWith(collegeId)) {
			throw new AuthorizationException("无权限操作!");
		}
	}
}
