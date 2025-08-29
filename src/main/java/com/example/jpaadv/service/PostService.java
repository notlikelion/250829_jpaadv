package com.example.jpaadv.service;

import com.example.jpaadv.model.dto.PostDTO;
import com.example.jpaadv.model.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // Repository DI
public class PostService {
    private final PostRepository postRepository; // final
    // 의존성 주입이 편하게

    // 저장을 했을 때 -> 3가지 형태로
    // 1) PK. (Form... JSP, Thymeleaf SSR)
    // 2) Entity 자체 !!! (Restful)
    // 3) boolean, int
    @Transactional // 쓰기 범위 내에서 락. 종료하려는 중간에 에러가 나면 이전 상황으로 rollback.
    public Long save(PostDTO.SaveRequest dto) {
        // postRepository.save -> 엔티티 자체
        // dto -> 변환로직 -> dto.toEntity() -> id와 audit 필드가 생략된 entity
        return postRepository.save(dto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public List<PostDTO.Response> findAll() {
        return postRepository.findAllDesc().stream()
                .map(PostDTO.Response::fromEntity)
                // list로 만들어주는 stream.
                .collect(Collectors.toList());
    }
}
