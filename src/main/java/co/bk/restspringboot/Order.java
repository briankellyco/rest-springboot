package co.bk.restspringboot;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="app_order")
public class Order extends EntityBean {

    @Id
    @SequenceGenerator(name = "seq", sequenceName = "s_app_order", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @Column( name = "pk" )
    private Long pk;

    @Enumerated(EnumType.ORDINAL)
    private OrderState state = OrderState.INIT;

    @Column(name="order_number", length=30)
    private String orderNumber;

    // Bi-directionality added with "mappedBy" enabling "order.getOrderItems()" call.
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "order")
    private Set<OrderLine> orderLines = new HashSet<OrderLine>();

    public Long getPk() {
        return pk;
    }

    public void setPk(Long pk) {
        this.pk = pk;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Set<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void addOrderLine(OrderLine orderLine) {

        if (!getOrderLines().contains(orderLine)) {
            orderLine.setOrder(this);
            getOrderLines().add(orderLine);
        }
    }

    public void setOrderLines(List<OrderLine> orderLines) {

        for (OrderLine orderLine : orderLines) {
            addOrderLine(orderLine);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((orderNumber == null) ? 0 : orderNumber.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Order other = (Order) obj;
        if (orderNumber == null) {
            if (other.orderNumber != null)
                return false;
        } else if (!orderNumber.equals(other.orderNumber))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
                .append("orderNumber", this.orderNumber)
                .append("state", this.state).toString();
    }
}
