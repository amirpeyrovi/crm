package ir.parto.crm.modules.server.controller.transientObject.serverGroup;

import ir.parto.crm.modules.server.controller.transientObject.serverVendor.ServerVendorDTO;

public class ServerGroupDTO {
    private Long serverGroupId;
    private String title;
    private ServerVendorDTO serverVendor;

    public ServerGroupDTO() {
    }

    public ServerGroupDTO(Long serverGroupId, String title, ServerVendorDTO serverVendor) {
        this.serverGroupId = serverGroupId;
        this.title = title;
        this.serverVendor = serverVendor;
    }

    public Long getServerGroupId() {
        return serverGroupId;
    }

    public void setServerGroupId(Long serverGroupId) {
        this.serverGroupId = serverGroupId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ServerVendorDTO getServerVendor() {
        return serverVendor;
    }

    public void setServerVendor(ServerVendorDTO serverVendor) {
        this.serverVendor = serverVendor;
    }
}
