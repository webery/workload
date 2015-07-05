package dlnu.workload.framework.exception.handle;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import dlnu.workload.framework.exception.BusinessException;
import dlnu.workload.framework.exception.SystemException;
import dlnu.workload.framework.exception.UserException;
import dlnu.workload.framework.mvc.model.JsonResponse;

public class CustomSimpleMappingExceptionResolver extends
		SimpleMappingExceptionResolver {
	private static Logger logger = Logger
			.getLogger(CustomSimpleMappingExceptionResolver.class);

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		// Expose ModelAndView for chosen error view.
		logger.error("doResolveException", ex);
		String viewName = determineViewName(ex, request);
		// 1.JSP格式返回
		if (!(request.getHeader("accept").indexOf("application/json") > -1 || (request
				.getHeader("X-Requested-With") != null && request.getHeader(
				"X-Requested-With").indexOf("XMLHttpRequest") > -1))) {
			// 如果不是异步请求
			// Apply HTTP status code for error views, if specified.
			// Only apply it if we're processing a top-level request.
			Integer statusCode = determineStatusCode(request, viewName);
			if (statusCode != null) {
				applyStatusCodeIfPossible(request, response, statusCode);
			}
			return getModelAndView(viewName, ex, request);
		} else {// 2.JSON格式返回
			String message = null;
			if (ex instanceof UserException) {
				message = ex.getMessage();
			} else if (ex instanceof BusinessException) {
				message = ex.getMessage();
			} else if (ex instanceof SystemException) {
				message = ex.getMessage();
			} else if (ex instanceof DataAccessException) {
				if (ex instanceof DataIntegrityViolationException) {
					message = "要录入的信息已经存在!";
				} else if (ex instanceof DataAccessResourceFailureException) {
					message = "数据库连接错误!";
				} else {
					message = "数据库未知错误!";
				}
			} else if (ex instanceof Exception) {
				message = "系统未知错误!";
			}
			try {
				response.setContentType("application/json;charset=utf-8");
				PrintWriter writer = response.getWriter();
				writer.write(JsonResponse.getJsonResponse(1000, message));
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;

		}
	}
}
