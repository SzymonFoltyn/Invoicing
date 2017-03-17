package invoicing.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@MappedSuperclass
public class Document implements Serializable {

    /**
     * klucz główny, którym bedziemy identyfikować wiesze
     * generateValue zwalnia hibernate auto inkrementacja
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User seller;

    @Column
    String documenteNumber;

    public Document() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public String getDocumentNumber() {
        return documenteNumber;
    }

    public void setDocumentNumber(String invoiceNumber) {
        this.documenteNumber = invoiceNumber;
    }

    @Override
    public String toString() {
        return "Document{" +
                "date=" + date +
                ", seller=" + seller +
                ", items=" +
                ", invoiceNumber='" + documenteNumber + '\'' +
                '}';
    }
}
