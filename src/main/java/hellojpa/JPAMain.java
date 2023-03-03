package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JPAMain {
    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");// meta-inf에서 작성한 unit name을 넎어준다.
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션
        tx.begin();
        try{

            // 저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team); // persist를 통해 pk 값이 생성됨

            Member member = new Member();
            member.setUsername("member1");
            member.setTeam(team);

            em.persist(member);

            // 영속성 컨텍스트: 1차 캐시에 다 저장이 되어 있기 때문에 따로 sql을 날리지 않음.
            Member findMember = em.find(Member.class, member.getId());
            Team findTeam = em.find(Team.class, findMember.getTeam().getName());

            // Team 을 바꾸고 싶다면?
            Team newTeam = em.find(Team.class, 100L);
            findMember.setTeam(newTeam);

            tx.commit();

        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();
    }
}
