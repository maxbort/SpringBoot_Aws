package com.cchj.admin.springboot.service.posts;

import com.cchj.admin.springboot.domain.posts.Posts;
import com.cchj.admin.springboot.domain.posts.PostsRepository;
import com.cchj.admin.springboot.web.dto.PostsListResponseDto;
import com.cchj.admin.springboot.web.dto.PostsResponseDto;
import com.cchj.admin.springboot.web.dto.PostsSaveRequestDto;
import com.cchj.admin.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.transaction.annotation.Transactional;
@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id) );

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional(readOnly = true) // readOnly 옵션을 통해 트랜잭션 범위는 유지하되, 조회 기능만 남겨둠 -> 속도 향상
                                    // 수정, 삭제 기능이 없는 메소드에서 사용할 것!
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new) // .map(posts -> new PostsListResponseDto(posts))와 같은 코드
                                                // postsRepository 결과로 넘어온 Posts의 Stream을 map을 통해
                                                // PostsListResponseDto 변환 -> List로 반환하는 메소드.
                .collect(Collectors.toList());
    }

    public PostsResponseDto findById (Long id){
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        return new PostsResponseDto(entity);
    }
}
