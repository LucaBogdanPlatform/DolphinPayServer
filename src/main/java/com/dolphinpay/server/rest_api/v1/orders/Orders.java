package com.dolphinpay.server.rest_api.v1.orders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "z_id", insertable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "z_state")
    private Orders state;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "z_expected_end_prepare")
    private Date expectedEndTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "z_closure_time")
    private Date officialClosureTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "z_sum_optional_time")
    private Date sumOptionalTime;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "z_creation_time")
    private Date creationTime;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "z_last_update_time")
    private Date lastUpdateTime;
}
