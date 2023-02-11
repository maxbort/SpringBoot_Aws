package com.cchj.admin.springboot.web;

import com.cchj.admin.springboot.service.posts.PostsService;
import com.cchj.admin.springboot.web.dto.PostsResponseDto;
import com.cchj.admin.springboot.web.dto.PostsSaveRequestDto;
import com.cchj.admin.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
//스프링에서 Bean을 주입받는 방식
//        1. @Autowired
//        2. setter
//        3. 생성자
//        가장 권장하는 방식은 생성자
//        즉, 생성자로 Bean 객체를 받도록 하면 @Autowired와 동일한 효과
// RequiredArgsConstructor를 통해 final이 선언된 모든 필드 값을 인자값으로 하는 생성자를
// 롬복의 @RequiredArgsConstructor가 대신 생성해줌.
// 생성자를 직접 안쓰고 롬복 어노테이션으로 사용한 이유는 클래스의 의존성 변경 시 생성자 코드를 매번 수정하는 번거로움을 해결하기 위함

@RestController
// @Controller와 @ResponseBdoy가 결합된 어노테이션.
// @Controller와 달리 컨트롤러 클래스의 각 메서드마다 @ResponseBody를 추가할 필요가 없다.
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        return postsService.findById(id);
    }

}
