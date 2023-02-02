package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JPAMain {
    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");// meta-inf에서 작성한 unit name을 넎어준다.
        EntityManager em = emf.createEntityManager();
        // database에 접근할 code를 여기서 작성
        EntityTransaction tx = em.getTransaction(); // 트랜잭션
        tx.begin();

        Member member = new Member();
        member.setId(1L);
        member.setName("HelloA");
        em.persist(member);

        tx.commit();
        // 여기까지

        em.close();
        emf.close();
    }
}
