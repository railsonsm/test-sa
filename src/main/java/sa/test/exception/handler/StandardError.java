package sa.test.exception.handler;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class StandardError implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long timestamp;
	private Integer status;
	private String error;
	private String message;
	private String path;

	public StandardError(Integer status, String error, String message, String path) {
		this.timestamp = System.currentTimeMillis();
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}

	public Long getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getError() {
		return this.error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}