package ir.parto.crm.modules.server.vendor;

import ir.parto.crm.modules.service.model.entity.Service;
import ir.parto.crm.utils.transientObject.ServerVendorResponse;

public class NetBill {
    private String url;
    private String adminId;
    private String secure;

    public String getUrl() {
        return url;
    }

    public NetBill setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getAdminId() {
        return adminId;
    }

    public NetBill setAdminId(String adminId) {
        this.adminId = adminId;
        return this;
    }

    public String getSecure() {
        return secure;
    }

    public NetBill setSecure(String secure) {
        this.secure = secure;
        return this;
    }

    public ServerVendorResponse createAccount(Service service) {
        String result = "";
        String message = "";
        Object returnDate = null;

        return new ServerVendorResponse().setResult(result).setMessage(message).setReturnData(returnDate);
    }

    public ServerVendorResponse updateAccount(Service service) {
        String result = "";
        String message = "";
        Object returnDate = null;

        return new ServerVendorResponse().setResult(result).setMessage(message).setReturnData(returnDate);
    }

    public ServerVendorResponse suspendAccount(Service service) {
        String result = "";
        String message = "";
        Object returnDate = null;

        return new ServerVendorResponse().setResult(result).setMessage(message).setReturnData(returnDate);
    }

    public ServerVendorResponse unSuspendAccount(Service service) {
        String result = "";
        String message = "";
        Object returnDate = null;

        return new ServerVendorResponse().setResult(result).setMessage(message).setReturnData(returnDate);
    }

    public ServerVendorResponse terminateAccount(Service service) {
        String result = "";
        String message = "";
        Object returnDate = null;

        return new ServerVendorResponse().setResult(result).setMessage(message).setReturnData(returnDate);
    }

    private String createBaseUrl() {
        return "http" + ((this.getSecure() == "on") ? "s" : "") + "://" + this.getUrl() + "?admin=" + this.getAdminId();
    }

}
