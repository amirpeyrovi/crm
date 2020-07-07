package ir.parto.crm.modules.authenticate.model.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseToken {

	private String faultMessage;
	private int faultCode;
	private List data;
	public String result;
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	LocalDateTime now = LocalDateTime.now();
	private String accessToken;
	private String tokenType = "Bearer";

	public ResponseToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public ResponseToken(String accessToken, String result ) {
		this.accessToken = accessToken;
		this.result = result;
//		this.data = "accessToken" + accessToken + "tokenType :" + this.tokenType;
	}

	public Map getResponse(){
		Map map = new HashMap<>();
		Map map1 = new HashMap<>();
		map.put("result" , this.result);
		map1.put("accessToken", this.accessToken);
		map1.put("tokenType", this.tokenType);
		map.put("data" ,map1);
		map.put("Timestamp" , dtf.format(now));
		return  map;

	}
	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

}
