package com.dolphinpay.server.rest_api.stands_orders_states;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "stands_orders_states")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StandsOrdersState {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sos_id")
    private int id;

    @Column(name = "sos_name", unique = true)
    private String name;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date lastUpdate;

}
