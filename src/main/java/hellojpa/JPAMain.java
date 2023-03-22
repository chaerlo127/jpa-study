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

            em.flush(); // commit 전에 저장하고 싶은 경우
            em.clear(); // 영속성 컨텍스트 완전히 초기화

            // 영속성 컨텍스트: 1차 캐시에 다 저장이 되어 있기 때문에 따로 sql을 날리지 않음.
            Member findMember = em.find(Member.class, member.getId());
            List<Member> members = findMember.getTeam().getMembers();

            for(Member member1: members){
                System.out.println("m = " + member.getUsername());
            }

            tx.commit();

        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();
    }
}
