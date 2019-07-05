package com.dolphinpay.server.rest_api.v1.platforms_partenerships;

import com.dolphinpay.server.rest_api.v1.platforms_roles.PlatformsRoles;
import com.dolphinpay.server.rest_api.v1.stands.Stands;
import com.dolphinpay.server.rest_api.v1.stands_rooms.StandsRooms;
import com.dolphinpay.server.rest_api.v1.users.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

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

    @Column(name = "z_state_on", insertable = false)
    private boolean active;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "z_creation_time")
    private Date creationTime;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "z_last_update_time")
    private Date lastUpdateTime;
}
