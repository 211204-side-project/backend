package com.scope.socialboardweb.repository.custom;

import com.scope.socialboardweb.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Locale;

@RequiredArgsConstructor
@Repository
public class CustomPostRepository {
  private final EntityManager em;

  public List<Post> searchPostByTitleOrContent(String keyword) {
    String originalKeyword = keyword.toLowerCase(Locale.ROOT);
    String noSpaceKeyword = keyword.replace(" ", "").toLowerCase(Locale.ROOT);

    originalKeyword = "%" + originalKeyword + "%";
    originalKeyword = originalKeyword.replace(" ", "%");
    noSpaceKeyword = "%" + noSpaceKeyword + "%";

    System.out.println("noSpaceKeyword = " + noSpaceKeyword);
    System.out.println("originalKeyword = " + originalKeyword);

    String jpql = "select p from Post p where lower(p.title) like :original " +
      "or lower(p.title) like :nospace " +
      "or lower(p.content) like :original " +
      "or lower(p.content) like :nospace";

    List<Post> searchedPosts = em.createQuery(jpql, Post.class)
      .setParameter("original", originalKeyword)
      .setParameter("nospace", noSpaceKeyword)
      .getResultList();

    return searchedPosts;
  }
}
