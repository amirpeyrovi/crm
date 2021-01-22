package ir.parto.crm.modules.server.controller.transientObject.serverGroup;

public class ServerGroupRelationalDTO {
    private Long serverGroupId;
    private String title;

    public ServerGroupRelationalDTO() {
    }

    public ServerGroupRelationalDTO(Long serverGroupId, String title) {
        this.serverGroupId = serverGroupId;
        this.title = title;
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
}
