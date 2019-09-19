package com.dolphinpay.server.rest_api.v1.orders_products;

import com.dolphinpay.server.rest_api.v1.orders_states.OrdersStates;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "orders_products")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrdersProducts {

    public enum StatesIds {
        STATE_NEW(1),
        STATE_PREPARE(2),
        STATE_READY(3),
        STATE_CLOSED(4);

        private final int state;

        StatesIds(int i) {
            this.state = i;
        }
    }

    @EmbeddedId
    private OrdersProductsIds ids;

    @ManyToOne
    @JoinColumn(name = "z_state", insertable = false)
    private OrdersStates state;

    @Column(name = "z_quantity")
    private int quantity;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "z_expected_start_prepare")
    private Date expectedStartTime;

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


    public Map<String, String> toMap() {
        return new Map<String, String>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean containsKey(Object key) {
                return false;
            }

            @Override
            public boolean containsValue(Object value) {
                return false;
            }

            @Override
            public String get(Object key) {
                return null;
            }

            @Override
            public String put(String key, String value) {
                return null;
            }

            @Override
            public String remove(Object key) {
                return null;
            }

            @Override
            public void putAll(Map<? extends String, ? extends String> m) {

            }

            @Override
            public void clear() {

            }

            @Override
            public Set<String> keySet() {
                return null;
            }

            @Override
            public Collection<String> values() {
                return null;
            }

            @Override
            public Set<Entry<String, String>> entrySet() {
                return null;
            }
        };
    }
}
