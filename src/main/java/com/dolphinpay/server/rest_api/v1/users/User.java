package com.dolphinpay.server.rest_api.v1.users;

import com.dolphinpay.server.rest_api.v1._JSONEntities.JSONGenericPlatform;
import com.dolphinpay.server.rest_api.v1._JSONEntities.JSONUser;
import com.dolphinpay.server.rest_api.v1.platforms_standards.PlatformStandard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"z_username", "z_email"})})
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "z_id", insertable = false)
    private int id;

    @Column(name = "z_username")
    private String username;

    @Column(name = "z_email")
    private String email;

    @OneToOne
    @JoinColumn(name = "z_standard_platform", insertable = false)
    private PlatformStandard standardPlatform;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "z_creation_time")
    private Date creationTime;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "z_last_update_time")
    private Date lastUpdateTime;

    public JSONUser getHttpResponse(JSONGenericPlatform jsonGenericPlatform) {
        JSONUser jsonUser = new JSONUser();
        jsonUser.setId(this.id);
        jsonUser.setUsername(this.username);
        jsonUser.setEmail(this.email);
        jsonUser.setGenericPlatform(jsonGenericPlatform);
        return jsonUser;
    }
}
