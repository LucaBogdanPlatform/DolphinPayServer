package com.dolphinpay.server.rest_api.v1.users_devices;

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
@Table(name = "users_devices")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsersDevices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "z_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "z_user")
    private User user;

    @Column(name = "z_firebase_token")
    private String firebaseToken;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "z_creation_time")
    private Date creationTime;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "z_last_update_time")
    private Date lastUpdateTime;
}
