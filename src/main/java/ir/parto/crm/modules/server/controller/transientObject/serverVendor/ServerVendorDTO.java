package ir.parto.crm.modules.server.controller.transientObject.serverVendor;

public class ServerVendorDTO {
    private Long serverVendorId;
    private String title;

    public ServerVendorDTO() {
    }

    public ServerVendorDTO(Long serverVendorId, String title) {
        this.serverVendorId = serverVendorId;
        this.title = title;
    }

    public Long getServerVendorId() {
        return serverVendorId;
    }

    public void setServerVendorId(Long serverVendorId) {
        this.serverVendorId = serverVendorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
