package com.cchj.admin.springboot.web;

import com.cchj.admin.springboot.config.auth.LoginUser;
import com.cchj.admin.springboot.config.auth.dto.SessionUser;
import com.cchj.admin.springboot.service.posts.PostsService;
import com.cchj.admin.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) { // Model은 서버 템플릿 엔진에서 사용할 수 있는 객체 저장 가능
        // postsService.findAllDesc()로 가져온 결과를 posts로 index.mustache에 전달.
        model.addAttribute("posts", postsService.findAllDesc());

        if(user != null){
            model.addAttribute("userName", user.getName());
        }
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

/*
(SessionUser) httpSession.getAttribute("user")
1. CustomOAuth2UserService에서 로그인 성공 시 세션에 SessionUser를 저장하도록 구성하였음
2. 즉, 로그인 성공 시 httpSession.getAttribute("user")에서 값을 가져 올 수 있음

if (user != null)
1. 세션에 저장된 값이 있을 때만 model에 userName으로 등록.
2. 세션에 저장된 값이 없으면 model엔 아무런 값이 아무런 값이 없는 상태이니 로그인 버튼이 보이게 된다.

@LoginUser SessionUser user 를 사용함으로써
기존의 (User) httpSession.getAttribute("user")로 가져오던 세션 정보 값이 개선
어떤 컨트롤러든 @LoginUser만 사용하면 세션 정보를 가져올 수 있게 됨.
 */
