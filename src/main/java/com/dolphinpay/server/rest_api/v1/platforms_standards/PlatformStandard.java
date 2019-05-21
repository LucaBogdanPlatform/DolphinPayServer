package com.dolphinpay.server.rest_api.v1.platforms_standards;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "platforms_standards")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlatformStandard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "z_id")
    private int id;

    @Column(name = "z_role")
    private int role;

    @Column(name = "z_name", unique = true)
    private String name;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "z_creation_time")
    private Date creationTime;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "z_last_update_time")
    private Date lastUpdateTime;
}
