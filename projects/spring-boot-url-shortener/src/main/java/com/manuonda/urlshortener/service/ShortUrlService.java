package com.manuonda.urlshortener.service;


import com.manuonda.urlshortener.domain.entities.ShortUrl;
import com.manuonda.urlshortener.domain.models.ShortUrlDto;
import com.manuonda.urlshortener.repositorys.ShortUrlRepository;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.util.List;

@Service
public class ShortUrlService {

    private final ShortUrlRepository shortUrlRepository;
    private final EntityMapper entityMapper;

    public ShortUrlService(ShortUrlRepository shortUrlRepository,
                           EntityMapper entityMapper) {
        this.shortUrlRepository = shortUrlRepository;
        this.entityMapper = entityMapper;
    }

    public List<ShortUrlDto> findAllPublicShortUrls() {
      return shortUrlRepository.findPublicShortUrl()
              .stream()
              .map(entityMapper::toShortUrlDto)
              .toList();
    }

}
