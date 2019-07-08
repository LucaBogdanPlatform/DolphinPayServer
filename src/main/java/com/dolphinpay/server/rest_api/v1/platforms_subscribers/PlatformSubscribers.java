package com.dolphinpay.server.rest_api.v1.platforms_subscribers;

import com.dolphinpay.server.rest_api.v1.platforms_roles.PlatformsRoles;
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
@Table(name = "platforms_subscribers")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlatformSubscribers {
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
    @JoinColumn(name = "z_stand_room")
    private StandsRooms room;

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
}
