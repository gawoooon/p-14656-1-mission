package com.back.global.initData;

import com.back.domain.post.post.service.PostService;
import com.back.domain.post.post.document.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class BaseInitData {

    private final PostService postService;

    @Bean
    public ApplicationRunner baseInitDataRunner() {
        return args -> {
            work1();
            work2();
            work3();
        };
    }

    private void work1() {
        log.debug("Post entity 개수: {}", postService.count());
        log.debug("샘플 Post 데이터 생성");
        if (postService.count() == 0) {
            for (int i = 1; i <= 10; i++) {
                String title = "Sample Post Title " + i;
                String content = "This is the content of sample post number " + i + ".";
                String author = "Author" + i;
                Post post = postService.create(title, content, author);
                log.debug("Created Post: {}", post);
            }
        }
    }


    private void work2(){
        log.debug("기존 Post 전체 조회");
        for (Post post : postService.findAll()) {
            log.debug("Existing Post: {}", post);
        }
    }

    private void work3(){
        // 정확한 ID로 시도
        String testId1 = "j0YDjZsBjAGckH4-56gK";
        log.debug("ID {} 로 Post 단건 조회", testId1);
        postService.findById(testId1).ifPresentOrElse(
                post -> log.debug("조회된 Post: {}", post),
                () -> log.debug("ID {} 로 조회한 결과가 없습니다.", testId1)
        );

        // 부분 조회로 시도
        String testId2 = "YDjZsBjAGckH4";
        log.debug("ID {} 로 Post 단건 조회", testId2);
        postService.findById(testId2).ifPresentOrElse(
                post -> log.debug("조회된 Post: {}", post),
                () -> log.debug("ID {} 로 조회한 결과가 없습니다.", testId2)
        );

        // 존재하지 않는 ID로 시도
        String testId3 = "nonExistingId";
        log.debug("ID {} 로 Post 단건 조회", testId3);
        postService.findById(testId3).ifPresentOrElse(
                post -> log.debug("조회된 Post: {}", post),
                () -> log.debug("ID {} 로 조회한 결과가 없습니다.", testId3)
        );
    }
}