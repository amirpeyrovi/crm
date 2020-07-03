package ir.parto.crm.utils.transientObject;

public class ServerVendorResponse {
    private String result;
    private String message;
    private Object returnData;

    public String getResult() {
        return result;
    }

    public ServerVendorResponse setResult(String result) {
        this.result = result;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ServerVendorResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getReturnData() {
        return returnData;
    }

    public ServerVendorResponse setReturnData(Object returnData) {
        this.returnData = returnData;
        return this;
    }
}
