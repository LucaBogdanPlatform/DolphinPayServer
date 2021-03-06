package com.dolphinpay.server.rest_api.v1.platforms_roles;

import com.dolphinpay.server.rest_api.v1._JSONEntities.JSONGenericPlatform;
import com.dolphinpay.server.rest_api.v1.platforms_permissions.PlatformsPermissions;
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
@Table(name = "platforms_roles", uniqueConstraints = {@UniqueConstraint(columnNames = {"z_name"})})
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlatformsRoles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "z_id", insertable = false)
    private int id;

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

    public JSONGenericPlatform.JSONRole getHttpResponse(List<PlatformsPermissions> jsonRolePermissions) {
        JSONGenericPlatform.JSONRole jsonRole = new JSONGenericPlatform.JSONRole();
        jsonRole.setName(this.name);
        jsonRole.setPermissions(jsonRolePermissions);
        return jsonRole;
    }
}
