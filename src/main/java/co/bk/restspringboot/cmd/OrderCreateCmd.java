package co.bk.restspringboot.cmd;

import co.bk.restspringboot.Order;
import co.bk.restspringboot.OrderLine;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * RequestWrapper to process multiple items submitted via JSON.
 */
public class OrderCreateCmd implements Serializable {

	@NotNull
	private Order order;

	private List<OrderLine> orderLines = new ArrayList<OrderLine>();
	
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    /**
     * @param items/lines in the order
     */
    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }
}
