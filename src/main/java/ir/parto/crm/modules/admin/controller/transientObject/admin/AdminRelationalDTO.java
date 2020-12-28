package ir.parto.crm.modules.admin.controller.transientObject.admin;

public class AdminRelationalDTO {
    private Long adminId;
    private String username;
    private String firstName;
    private String lastName;

    public AdminRelationalDTO() {
    }

    public AdminRelationalDTO(Long adminId, String username, String firstName, String lastName) {
        this.adminId = adminId;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getAdminId() {
        return adminId;
    }

    public AdminRelationalDTO setAdminId(Long adminId) {
        this.adminId = adminId;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public AdminRelationalDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public AdminRelationalDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public AdminRelationalDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}
