package invoicing.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "invoice")
public class Invoice extends Document implements Serializable {

    /**
     * pomijamy id bo dziedziczy po Document
     */


    @ManyToOne //w takim przypadku musimy wskazać rodzica anotacji jak ponizej
    @JoinColumn(name = "buyer_id")
    private User buyer;

    /**
     * relacja dwustronna - OneToMany
     */
    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL) // encja dba o wsyztskie swoje dzieci
    private List<Item> items;

    @Column
    private BigDecimal total; // zmienniono na BigDecimal z float bo jest niedokładny


    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Invoice() {
        super();
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "buyer=" + buyer +
                ", total=" + total +
                "} " + super.toString();
    }
}
