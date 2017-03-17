package invoicing;


import invoicing.bean.Invoice;
import invoicing.bean.Item;
import invoicing.bean.User;
import invoicing.database.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import java.math.BigDecimal;
import java.util.*;

public class Main {

    public static void saveToDatabase(Session session) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Transaction transaction = session.beginTransaction();

        User u1 = new User();
        u1.setFirstName("Franek");
        u1.setLastName("Kosa");
        u1.setAge(67);
        u1.setSex(User.Sex.MALE.toString());

        User u2 = new User();
        u2.setFirstName("Stasek");
        u2.setLastName("Kowalski");
        u2.setAge(45);
        u2.setSex(User.Sex.MALE.toString());


        Item i1 = new Item();
        i1.setName("Numer 2");
        i1.setPrice(new BigDecimal(2365));
        i1.setQty(new BigDecimal(12));
        i1.setTax(new BigDecimal(98));

        Item i2 = new Item();
        i2.setName("Numer 3");
        i2.setPrice(new BigDecimal(353648));
        i2.setQty(new BigDecimal(45));
        i2.setTax(new BigDecimal(67));


        java.util.List<Item> items = new ArrayList<>();
        Invoice invoice = new Invoice();

        invoice.setBuyer(u1);
        invoice.setSeller(u2);
        invoice.setItems(items);
        invoice.setTotal(i1.getPrice());
        invoice.setDate(new Date());

        i1.setInvoice(invoice);
        i2.setInvoice(invoice);

        items.add(i1);
        items.add(i2);

        session.saveOrUpdate(u1);
        session.saveOrUpdate(u2);
        session.saveOrUpdate(i1);
        session.saveOrUpdate(i2);
        session.saveOrUpdate(invoice);


        transaction.commit();
        session.close();
        sessionFactory.close();
    }

    public static void main(String[] args) {


        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();


        Query query = session.createQuery("from User u");
        Query query2 = session.createQuery("from Invoice u");
        for (Object o : query2.list()) {
            Invoice invoice = (Invoice) o;
            System.out.println(invoice.toString());
            for (Item item : invoice.getItems()) {
                System.out.println(item.getName());

            }
        }
        //można tak
        Query query3 = session.createQuery("from Item i where i.invoice.id = :invoice");
        query3.setParameter("invoice", 3);
        for (Object o : query3.list()) {
            Item item = (Item) o;
            System.out.println(item.getName());

        }


        session.close();
        sessionFactory.close();


    }
}
