package com.scope.socialboardweb.repository.custom;

import com.scope.socialboardweb.domain.Post;
import com.scope.socialboardweb.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Locale;

@RequiredArgsConstructor
@Repository
public class CustomPostRepository {
  private final EntityManager em;

  /**
   * 제목, 본문, 작성자로 게시글 검색 (LIKE 문법 사용)
   * @param generalKeyword 공백 포함 검색어
   * @param noSpaceKeyword 공백 미포함 검색어
   * @param writers 작성자 목록
   * @param nowPage 출력할 페이지
   * @param size 페이지당 최대 게시글 수
   * @return
   */
  public List<Post> searchPostByTitleOrContentOrUser(String generalKeyword, String noSpaceKeyword, List<User> writers, int nowPage, int size) {

    if (nowPage < 1) {
      throw new IllegalArgumentException("현재 페이지는 1 이상이어야 합니다.");
    }
    if (size < 1) {
      throw new IllegalArgumentException("페이징 사이즈는 최소 1 이상이어야 합니다.");
    }

    String jpql = "select p from Post p where " +
      "lower(p.title) like :general " +
      "or lower(p.title) like :nospace " +
      "or lower(p.content) like :general " +
      "or lower(p.content) like :nospace " +
      "or p.user in :writer";

    TypedQuery<Post> typedQuery = em.createQuery(jpql, Post.class)
      .setParameter("general", generalKeyword)
      .setParameter("nospace", noSpaceKeyword)
      .setParameter("writer", writers);

    typedQuery.setFirstResult( (nowPage-1)*size );
    typedQuery.setMaxResults(size);

    List<Post> searchedPosts = typedQuery.getResultList();


    return searchedPosts;
  }


}
