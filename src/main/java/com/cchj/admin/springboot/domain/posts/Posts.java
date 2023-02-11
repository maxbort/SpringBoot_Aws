package com.cchj.admin.springboot.domain.posts;

import com.cchj.admin.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
// 클래스 내 모든 필드의 Getter 메소드를 자동생성
// getId(), getTitle() 등..
@NoArgsConstructor
// 기본 생성자 자동 추가
// 여기서는 public Posts() {} 와 같은 효과
@Entity
// 테이블과 링크될 클래스임을 알려줌
// 기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍으로 테이블이름을 매칭한다.
public class Posts extends BaseTimeEntity { // 실제 DB의 테이블과 매칭될 클래스(Entity 클래스라고도 한다.)
    // 여기서 setter가 없는데 getter/setter를 무작정 생성하면 해당 클래스의 인스턴스 값들이 언제 어디서 변해야 하는지
    // 코드상으로  명확하게 구분이 힘들다.
    // 따라서 Entity클래스에는 절대 setter 메소드를 만들지 말 것! -> 필드의 값 변경을 위해서는 명확히 그 목적과 의도를
    // 나타낼 수 있는 메소드를 추가할 것!

    @Id
    // 해당 테이블의 PK 필드를 나타낸다. -> Entity의 PK는 Long 타입의 Auto_increment가 가장 좋음
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 생성규칙
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;
    //테이블의 칼럼을 나타내며 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 칼럼이 된다.
    // 사용하는 이유는 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    // 해당 클래스의 빌더 패턴 클래스를 생성
    // 빌더 패턴 : 복합 객체의 생성과정과 표현 방법을 분리하여 동일한 생성 절차에서 서로 다른 표현 결과를 만들 수 있게 하는 패턴
    // 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함한다.
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this. content = content;
    }
}
