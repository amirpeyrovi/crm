package ir.parto.crm.modules.server.controller.transientObject.server;

import ir.parto.crm.modules.server.controller.transientObject.serverGroup.ServerGroupRelationalDTO;

import java.time.LocalDateTime;

public class ServerDTO {
    private Long serverId;
    private String title;
    private String address;
    private String username;
    private String password;
    private Integer accessHash;
    private Integer ssl;
    private ServerGroupRelationalDTO serverGroup;

    public ServerDTO() {
    }

    public ServerDTO(Long serverId, String title, String address, String username, String password, Integer accessHash, Integer ssl, ServerGroupRelationalDTO serverGroup) {
        this.serverId = serverId;
        this.title = title;
        this.address = address;
        this.username = username;
        this.password = password;
        this.accessHash = accessHash;
        this.ssl = ssl;
        this.serverGroup = serverGroup;
    }

    public Long getServerId() {
        return serverId;
    }

    public void setServerId(Long serverId) {
        this.serverId = serverId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAccessHash() {
        return accessHash;
    }

    public void setAccessHash(Integer accessHash) {
        this.accessHash = accessHash;
    }

    public Integer getSsl() {
        return ssl;
    }

    public void setSsl(Integer ssl) {
        this.ssl = ssl;
    }

    public ServerGroupRelationalDTO getServerGroup() {
        return serverGroup;
    }

    public void setServerGroup(ServerGroupRelationalDTO serverGroup) {
        this.serverGroup = serverGroup;
    }
}
