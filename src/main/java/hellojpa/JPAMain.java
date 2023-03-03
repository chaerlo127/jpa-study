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
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team); // persist를 통해 pk 값이 생성됨

            Member member = new Member();
            member.setUsername("member1");
            member.setTeamId(team.getId());

            em.persist(member);

            Member findMember = em.find(Member.class, member.getTeamId());
            Long findTeamId = findMember.getTeamId();
            Team findTeam = em.find(Team.class, findTeamId);

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();
    }
}
