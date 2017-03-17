package invoicing.database;

import invoicing.bean.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
    private static final SessionFactory sessionFactory;
    static {
        try {
            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(Invoice.class);
            configuration.addAnnotatedClass(Item.class);
            configuration.addAnnotatedClass(User.class);
            sessionFactory = configuration.configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
