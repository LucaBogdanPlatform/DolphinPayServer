package com.dolphinpay.server.rest_api.v1.orders;

import com.dolphinpay.server.rest_api.v1.orders_states.OrdersStates;
import com.dolphinpay.server.rest_api.v1.users.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "orders")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "z_id", insertable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "z_state", insertable = false)
    private OrdersStates state;

    @Column(name = "z_valid_retire_code")
    private String retireCode;

    @ManyToOne
    @JoinColumn(name = "z_user")
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "z_expected_end_prepare")
    private Date expectedEndTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "z_closure_time", insertable = false)
    private Date officialClosureTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "z_sum_optional_time", insertable = false)
    private Date sumOptionalTime;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "z_creation_time")
    private Date creationTime;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "z_last_update_time")
    private Date lastUpdateTime;

    public Map<String, String> toMap() throws JsonProcessingException {
        Map<String, String> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        String order = mapper.writeValueAsString(this);
        map.put("order", order);
        return map;
    }
}
