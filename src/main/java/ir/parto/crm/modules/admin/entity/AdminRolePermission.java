package ir.parto.crm.modules.admin.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "crm_admin_role_permission")
public class AdminRolePermission implements Serializable {
    @Id
    @Column(columnDefinition = "number")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "permission_title", columnDefinition = "nvarchar2(100)", unique = true)
    private String title;

    @Column(name = "permission_add", columnDefinition = "number(1)")
    private String addPerm;

    @Column(name = "permission_update", columnDefinition = "number(1)")
    private String updatePerm;

    @Column(name = "permission_delete", columnDefinition = "number(1)")
    private String deletePerm;

    @Column(name = "permission_view", columnDefinition = "number(1)")
    private String viewPerm;

    @ManyToOne
    @JoinColumn(name = "admin_role_id", foreignKey = @ForeignKey(name = "permission_role_fk"))
    private AdminRole adminRole;
}
