package com.dolphinpay.server.rest_api.v1.users;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "z_id")
    private int id;

    @Column(name = "z_username")
    private String username;

    @Column(name = "z_email")
    private String email;

    @OneToOne
    @JoinColumn(name = "z_standard_platform")
    private PlatformStandard standard_platform;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "z_creation_time")
    private Date creationTime;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "z_last_update_time")
    private Date lastUpdateTime;
}
