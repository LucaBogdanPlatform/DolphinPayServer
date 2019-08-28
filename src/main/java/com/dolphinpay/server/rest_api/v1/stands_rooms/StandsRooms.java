package com.dolphinpay.server.rest_api.v1.stands_rooms;

import com.dolphinpay.server.rest_api.v1._JSONEntities.JSONStandRoom;
import com.dolphinpay.server.rest_api.v1.stands.Stands;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "stands_rooms")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class StandsRooms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "z_id", insertable = false)
    private int id;

    @Column(name = "z_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "z_stand")
    private Stands stand;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "z_creation_time")
    private Date creationTime;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "z_last_update_time")
    private Date lastUpdateTime;

    public JSONStandRoom getHttpResponseStandard() {
        JSONStandRoom.JSONStandRoomBuilder response = JSONStandRoom.builder();
        response.id(this.id);
        response.name(this.name);
        response.stand(stand.getHttpResponseStandard());
        return response.build();
    }

}
