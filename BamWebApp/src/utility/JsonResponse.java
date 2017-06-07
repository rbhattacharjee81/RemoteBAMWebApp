package utility;

public class JsonResponse {

	private String status;
	private Object response;
	private String error;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "JsonResponse{" + "status=" + status + ", response=" + response
				+ ", error=" + error + '}';
	}

}
