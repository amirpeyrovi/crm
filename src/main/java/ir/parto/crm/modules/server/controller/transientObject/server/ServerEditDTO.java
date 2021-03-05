package ir.parto.crm.modules.server.controller.transientObject.server;

import ir.parto.crm.modules.server.model.entity.Server;
import ir.parto.crm.modules.server.model.entity.ServerGroup;

public class ServerEditDTO {
    private String title;
    private String address;
    private String username;
    private String password;
    private Integer accessHash;
    private Integer ssl;
    private Long serverGroupId;

    public ServerEditDTO() {
    }

    public ServerEditDTO(String title, String address, String username, String password, Integer accessHash, Integer ssl, Long serverGroupId) {
        this.title = title;
        this.address = address;
        this.username = username;
        this.password = password;
        this.accessHash = accessHash;
        this.ssl = ssl;
        this.serverGroupId = serverGroupId;
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

    public Long getServerGroupId() {
        return serverGroupId;
    }

    public void setServerGroupId(Long serverGroupId) {
        this.serverGroupId = serverGroupId;
    }

    public Server convert2Object() {
        Server obj = new Server();
        if (this.title != null) obj.setTitle(this.title);
        if (this.address != null) obj.setAddress(this.address);
        if (this.username != null) obj.setUsername(this.username);
        if (this.password != null) obj.setPassword(this.password);
        if (this.accessHash != null) obj.setAccessHash(this.accessHash);
        if (this.ssl != null) obj.setSsl(this.ssl);
        if (this.serverGroupId != null) obj.setServerGroup(new ServerGroup(this.serverGroupId));
        return obj;
    }
}
