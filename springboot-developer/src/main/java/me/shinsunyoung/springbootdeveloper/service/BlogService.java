package me.shinsunyoung.springbootdeveloper.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.shinsunyoung.springbootdeveloper.domain.Article;
import me.shinsunyoung.springbootdeveloper.dto.AddArticleRequest;
import me.shinsunyoung.springbootdeveloper.dto.UpdateArticleRequest;
import me.shinsunyoung.springbootdeveloper.repository.BlogRepository;

@RequiredArgsConstructor // final이 붙거나 @NotNull이 붙은 필드의 생성자 추가
@Service // 빈으로 등록
public class BlogService {

	private final BlogRepository blogRepository;

	// 블로그 글 추가 메서드
	public Article save(AddArticleRequest request) {
		return blogRepository.save(request.toEntity());
	}

	// 데이터 베이스에 저장되어 있는 글을 가져오는 메서드
	public List<Article> findAll() {
		return blogRepository.findAll();
	}

	// 글 하나를 조회하는 메서드
	public Article findById(long id) {
		return blogRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found: " + id));
	}

	// id에 해당하는 블로그 글을 삭제하는 매서드
	public void delete(long id) {
		blogRepository.deleteById(id);
	}

	@Transactional
	public Article update(long id, UpdateArticleRequest request) {
		Article article = blogRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("not found: " + id));

		article.update(request.getTitle(), request.getContent());

		return article;
	}

}
