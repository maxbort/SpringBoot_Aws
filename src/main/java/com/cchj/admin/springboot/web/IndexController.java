package com.cchj.admin.springboot.web;

import com.cchj.admin.springboot.service.posts.PostsService;
import com.cchj.admin.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    @GetMapping("/")
    public String index(Model model) { // Model은 서버 템플릿 엔진에서 사용할 수 있는 객체 저장 가능
        // postsService.findAllDesc()로 가져온 결과를 posts로 index.mustache에 전달.
        model.addAttribute("posts", postsService.findAllDesc());
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        //@PathVaraiable -> 메서드 매개변수를 URI 템플릿 변수에 바인딩하는데 사용. 여기서는 /posts/update/{id}에서 id를 추출함
        PostsResponseDto dto = postsService.findById(id);  // 파라미터로 넘어온 id 값으로 게시글 정보를 가져온다.
        model.addAttribute("post",dto); // 가져온 정보를 model에 "post"라는 이름으로 저장한다.

        return "posts-update";
    }
}
