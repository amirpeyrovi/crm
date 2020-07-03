package ir.parto.crm.utils.transientObject;

import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiResponse {
    private List<String> faultMessageList;
    private String result;
    private int faultCode;
    private Page dataPage;
    private List dataList;
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();

    public ApiResponse() {
    }

    public ApiResponse(String result, Page dataPage) {
        this.result = result;
        this.dataPage = dataPage;
    }

    public ApiResponse(String result, List dataList) {
        this.result = result;
        this.dataList = dataList;
    }

    public ApiResponse(String result, int faultCode,List<String> faultMessageList) {
        this.faultMessageList = faultMessageList;
        this.result = result;
        this.faultCode = faultCode;
    }

    public List<String> getFaultMessageList() {
        return faultMessageList;
    }

    public void setFaultMessageList(List<String> faultMessageList) {
        this.faultMessageList = faultMessageList;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getFaultCode() {
        return faultCode;
    }

    public void setFaultCode(int faultCode) {
        this.faultCode = faultCode;
    }

    public Page getData() {
        return dataPage;
    }

    public void setData(Page dataPage) {
        this.dataPage = dataPage;
    }

    public List getDataList() {
        return dataList;
    }

    public void setDataList(List dataList) {
        this.dataList = dataList;
    }

    public Map getFaultResponse(){
        Map map = new HashMap<>();
        map.put("result",this.result);
        map.put("faultMessage",this.faultMessageList);
        map.put("faultCode",this.faultCode);
        map.put("Timestamp",this.dateTimeFormatter);
        return map;
    }

    public Map getSuccessResponse(){
        Map map = new HashMap<>();
        map.put("result",this.result);
        if(this.dataPage != null){
            map.put("data",this.dataPage);
        }else{
            map.put("data",this.dataList);
        }
        map.put("Timestamp",this.dateTimeFormatter);
        return map;
    }
}
