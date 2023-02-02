package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JPAMain {
    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");// meta-inf에서 작성한 unit name을 넎어준다.
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션
        tx.begin();
        try{
            Member findMember = em.find(Member.class, 1L);
            // 저장하지 않고, 그대로 둬도 가능
            // java collection 다루는 것곽 같음
            findMember.setName("HelloJPA");
            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();
    }
}
