package dlnu.workload.framework.mvc.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

/**
 * 封装 hibernate valid 验证错误的信息，返回前台显示
 * @author weber
 *
 */
public class FormErrorWrapper extends HashMap<String, String> {

	private static final long serialVersionUID = 1L;

	public FormErrorWrapper() {
		super();
	}

	public FormErrorWrapper(BindingResult result) {

		List<ObjectError> errors = result.getAllErrors();
		Iterator<ObjectError> it = errors.iterator();
		while (it.hasNext()) {
			ObjectError error = it.next();
			this.put(error.getObjectName(), error.getDefaultMessage());
		}
	}

}
