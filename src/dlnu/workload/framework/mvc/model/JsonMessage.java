package dlnu.workload.framework.mvc.model;

import org.springframework.validation.BindingResult;

public class JsonMessage {

	private Integer code = null;// 结果状态码，曹兆状态码约定
	private String message = null;// 状态码描述
	private Object data = null;// 操作返回数据

	public JsonMessage() {
	}

	public JsonMessage(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public JsonMessage(Integer code, String message, Object data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public JsonMessage(Integer code, String message, BindingResult result) {
		this.code = code;
		this.message = message;
		this.data = new FormErrorWrapper(result);
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
