package dlnu.workload.module.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginViewController {

	private static Logger logger = Logger.getLogger(LoginViewController.class);

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginView() {

		logger.debug("loginView");

		return "login";
	}

	@RequestMapping(value = "/login")
	public String showLoginForm(HttpServletRequest req, Model model) {
		String exceptionClassName = (String) req
				.getAttribute("shiroLoginFailure");
		String error = null;
		if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
			error = "用户名/密码错误";
		} else if (IncorrectCredentialsException.class.getName().equals(
				exceptionClassName)) {
			error = "用户名/密码错误";
		} else if ("jCaptcha.error".equals(exceptionClassName)) {
			error = "验证码错误";
		} else if (exceptionClassName != null) {
			error = "其他错误：" + exceptionClassName;
		}
		model.addAttribute("error", error);
		return "login";
	}
	/*
	 * public String login(
	 * 
	 * @RequestParam(value = "account", required = true) String account,
	 * 
	 * @RequestParam(value = "password", required = true) String password,
	 * HttpServletRequest request) {
	 * 
	 * logger.debug("login account:" + account + " password:" + password);
	 * 
	 * Subject subject = SecurityUtils.getSubject(); UsernamePasswordToken token
	 * = new UsernamePasswordToken(account, password);
	 * 
	 * try { subject.login(token); } catch (Exception e) {//
	 * AuthenticationException
	 * 
	 * logger.debug("error " + e); return "login"; }
	 * 
	 * return "redirect:/"; }
	 */
}
