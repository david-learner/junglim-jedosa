package com.jedosa.junglim.article.repository;

import com.jedosa.junglim.article.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByArticle_IdAndDeletedOrderByCreatedDateTimeAsc(Long articleId, Boolean deleted);
}
