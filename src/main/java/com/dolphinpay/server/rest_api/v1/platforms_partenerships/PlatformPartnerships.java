package com.dolphinpay.server.rest_api.v1.platforms_partenerships;

import com.dolphinpay.server.rest_api.v1._JSONEntities.JSONGenericPlatform;
import com.dolphinpay.server.rest_api.v1.platforms_permissions.PlatformsPermissions;
import com.dolphinpay.server.rest_api.v1.platforms_roles.PlatformsRoles;
import com.dolphinpay.server.rest_api.v1.stands.Stands;
import com.dolphinpay.server.rest_api.v1.users.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "platforms_partnerships")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlatformPartnerships {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "z_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "z_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "z_role", insertable = false)
    private PlatformsRoles role;

    @ManyToOne
    @JoinColumn(name = "z_stand")
    private Stands stand;

    @Column(name = "z_name")
    private String name;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "z_creation_time")
    private Date creationTime;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "z_last_update_time")
    private Date lastUpdateTime;


    public JSONGenericPlatform getHttpResponseStandard(List<PlatformsPermissions> jsonRolePermissions) {
        JSONGenericPlatform jsonGenericPlatform = new JSONGenericPlatform();
        jsonGenericPlatform.setId(this.id);
        jsonGenericPlatform.setName(this.name);
        jsonGenericPlatform.setCreationDate(this.creationTime);
        jsonGenericPlatform.setStandId(this.stand.getId());
        JSONGenericPlatform.JSONRole jsonRoles = role.getHttpResponse(jsonRolePermissions);
        jsonGenericPlatform.setRole(jsonRoles);
        return jsonGenericPlatform;
    }
}
