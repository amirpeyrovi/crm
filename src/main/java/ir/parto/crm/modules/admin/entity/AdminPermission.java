package ir.parto.crm.modules.admin.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "AdminPermission")
public class AdminPermission implements Serializable {
    @Id
    @Column(columnDefinition = "number")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "permission_title", columnDefinition = "nvarchar2(100)", unique = true)
    private String title;

    @Column(name = "description", columnDefinition = "nvarchar2(500)")
    private String description;
}
