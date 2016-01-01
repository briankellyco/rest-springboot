package co.bk.restspringboot;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "app_order_line")
public class OrderLine extends EntityBean {
	
	@Id
    @SequenceGenerator(name = "seq", sequenceName = "s_app_order", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
	private Long pk;
	
	@Column(length=30)
    private String articleNumber;

	@Column(length=100)
	private String articleName;

    @Column(precision = 12, scale = 2)
    private BigDecimal unitPrice;
	
	private int quantity;

	@JsonIgnore
	@ManyToOne
	private Order order;

	public Long getPk() {
		return pk;
	}

	public void setPk(Long pk) {
		this.pk = pk;
	}

	public String getArticleName() {
		return articleName;
	}

	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	public String getArticleNumber() {
		return articleNumber;
	}

	public void setArticleNumber(String articleNumber) {
		this.articleNumber = articleNumber;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Order getOrder() { 
		return order; 
	}
	
	public void setOrder(Order order) {
		this.order = order;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((articleNumber == null) ? 0 : articleNumber.hashCode());
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
        final OrderLine other = (OrderLine) obj;
        if (articleNumber == null) {
            if (other.articleNumber != null)
                return false;
        } else if (!articleNumber.equals(other.articleNumber))
            return false;
        return true;
    }
    
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
                .append("articleNumber", this.articleNumber)
                .append("articleName", this.articleName)
                .append("unitPrice", this.unitPrice)
                .append("quantity", this.quantity).toString();
    }
    
}