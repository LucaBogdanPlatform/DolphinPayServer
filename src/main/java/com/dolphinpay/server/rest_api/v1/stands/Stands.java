package com.dolphinpay.server.rest_api.v1.stands;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "stands")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Stands {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "z_id", insertable = false)
    private int id;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "z_creation_time")
    private Date creationTime;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "z_last_update_time")
    private Date lastUpdateTime;

    public JSONStands getResponse() {
        return JSONStands.builder().id(this.id).build();
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JSONStands {
        private int id;
    }
}
