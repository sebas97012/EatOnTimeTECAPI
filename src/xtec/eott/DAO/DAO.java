package xtec.eott.DAO;
import org.hibernate.Session;


public class DAO {
    public void create(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(this);
        session.getTransaction().commit();
        session.close();
    }

    public Object read(String id){
        Session session = HibernateUtil.getSession();
        Object obj = session.get(this.getClass(), id);
        session.close();
        return obj;
    }

    public Object read(int id){
        Session session = HibernateUtil.getSession();
        Object obj = session.get(this.getClass(), id);
        session.close();
        return obj;
    }

    public void update(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.update(this);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.delete(this);
        session.getTransaction().commit();
        session.close();
    }
}