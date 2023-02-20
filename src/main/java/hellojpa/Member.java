package hellojpa;


import javax.persistence.*;
import java.util.Date;

@Entity
public class Member {
    @Id
    private Long id;

    @Column(name = "name")
    private String username;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    // 생성 일자
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    // 수정 일자
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob // varchar를 넘어서는 큰 값을 작성하고자할 때 사용하는 것
    private String description;

    public Member(){}
}
