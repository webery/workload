package dlnu.workload.module.authority.realm;

import java.util.HashSet;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import dlnu.workload.module.common.model.User;
import dlnu.workload.module.common.service.UserService;

/**
 * 
 * @author weber 2015.4.18
 *
 */
public class UserRealm extends AuthorizingRealm {

	private static Logger logger = Logger.getLogger(UserRealm.class);

	private UserService userService;

	/**
	 * 权限验证,进行鉴权单缓存中无用户授权信息的时候调用
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {

		String userId = (String) principals.getPrimaryPrincipal();
		logger.debug("doGetAuthorizationInfo userId" + userId);

		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.setRoles((new HashSet<String>(userService
				.findRoles(userId))));
		authorizationInfo.setStringPermissions(new HashSet<String>(userService
				.findPermissions(userId)));

		return authorizationInfo;
	}

	/**
	 * 登陆验证 Principals(身份)和Credentials(凭证)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {

		logger.debug("doGetAuthenticationInfo");

		String account = (String) token.getPrincipal();

		User user = userService.get(new User(account));

		logger.debug(" user:" + user);

		if (user == null) {
			throw new UnknownAccountException();// 没找到帐号
		}

		if (user.getStatus() == 2) {
			throw new LockedAccountException(); // 帐号锁定
		}

		// 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
				user.getId(), // 用户id
				user.getPassword(), // 密码
				getName() // realm name
		);
		authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(user
				.getSalt()));// salt=username+salt
		org.apache.shiro.subject.Subject subject = SecurityUtils.getSubject();
		subject.getSession(true).setAttribute("user", user);

		return authenticationInfo;
	}

	@Override
	public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
		super.clearCachedAuthorizationInfo(principals);
	}

	@Override
	public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
		super.clearCachedAuthenticationInfo(principals);
	}

	@Override
	public void clearCache(PrincipalCollection principals) {
		super.clearCache(principals);
	}

	public void clearAllCachedAuthorizationInfo() {
		getAuthorizationCache().clear();
	}

	public void clearAllCachedAuthenticationInfo() {
		getAuthenticationCache().clear();
	}

	public void clearAllCache() {
		clearAllCachedAuthenticationInfo();
		clearAllCachedAuthorizationInfo();
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
