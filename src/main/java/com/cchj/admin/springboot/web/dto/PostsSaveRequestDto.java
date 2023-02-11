package com.cchj.admin.springboot.web.dto;

import com.cchj.admin.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


// Entity 클래스와 유사하지만 Dto 클래스를 추가로 생성
// 절대 Entity클래스를 Request/Response 클래스로 사용하면 안된다.
// Entity 클래스는 데이터베이스와 맞닿은 핵심 클래스이다.
// View Layer와 DB Layer를 철저하게 분리할 것!
// 즉, Controller에서 쓸 Dto와 Entity 클래스는 반드시 분리해야한다.
@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}


