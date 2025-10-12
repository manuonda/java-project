package com.manuonda.urlshortener.repositorys;

import com.manuonda.urlshortener.domain.entities.ShortUrl;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.JpaRepositoryNameSpaceHandler;

import java.util.List;

public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {
   List<ShortUrl> findByIsPrivateIsFalseOrderByCreatedAtDesc();

//   @Query(
//           """
//           Select su from ShortUrl su
//           left join fetch  su.createdBy
//           where su.isPrivate = false
//           order by su.createdAt desc
//           """
//   )
   @Query("SELECT su from ShortUrl  su where su.isPrivate = false order by su.createdAt desc")
   @EntityGraph(attributePaths ={"createdBy"})
   List<ShortUrl> findPublicShortUrl();
}
