package com.manuonda.urlshortener.service;


import com.manuonda.urlshortener.domain.entities.ShortUrl;
import com.manuonda.urlshortener.domain.models.CreateShortUrlCmd;
import com.manuonda.urlshortener.domain.models.ShortUrlDto;
import com.manuonda.urlshortener.repositorys.ShortUrlRepository;
import com.manuonda.urlshortener.repositorys.UserRepository;
import com.manuonda.urlshortener.ApplicationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.util.InvalidUrlException;

import java.time.Instant;
import java.util.List;

import static com.manuonda.urlshortener.service.RandomUtils.generateRandomShortKey;
import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class ShortUrlService {

    private final ShortUrlRepository shortUrlRepository;
    private final EntityMapper entityMapper;
    private final ApplicationProperties properties;
    private final UserRepository userRepository;

    public ShortUrlService(ShortUrlRepository shortUrlRepository,
                           EntityMapper entityMapper,
                           ApplicationProperties properties,
                           UserRepository userRepository) {
        this.shortUrlRepository = shortUrlRepository;
        this.entityMapper = entityMapper;
        this.properties = properties;
        this.userRepository = userRepository;
    }

    public List<ShortUrlDto> findAllPublicShortUrls() {
      return shortUrlRepository.findPublicShortUrl()
              .stream()
              .map(entityMapper::toShortUrlDto)
              .toList();
    }

    public ShortUrlDto createShortUrl(CreateShortUrlCmd cmd) {
        ShortUrl shortUrl = new ShortUrl();
        if(properties.validateOriginalUrl()){
            boolean urlExists = UrlExistenceValidator.isUrlExists(cmd.originalUrl());
            if(!urlExists){
                throw new InvalidUrlException("The provided URL does not exist: " + cmd.originalUrl());
            }
        }

        var shorUrlKey = generateRandomShortKey();
        shortUrl.setShortKey(shorUrlKey);
        shortUrl.setOriginalUrl(cmd.originalUrl());
        if (cmd.userId() == null) {
            shortUrl.setCreatedBy(null);
            shortUrl.setIsPrivate(false);
            shortUrl.setExpiresAt(Instant.now().plus(properties.defaultExpirationDays(), DAYS));
        } else {
           shortUrl.setCreatedBy(this.userRepository.findById(cmd.userId()).orElseThrow());
           shortUrl.setIsPrivate(cmd.isPrivate());
           shortUrl.setExpiresAt(cmd.expirationInDays() != null ?  Instant.now().plus(cmd.expirationInDays(), DAYS) : null);
        }
        shortUrl.setClickCount(0L);
        shortUrl.setCreatedAt(Instant.now());
        this.shortUrlRepository.save(shortUrl);
        return this.entityMapper.toShortUrlDto(shortUrl);
        

    }


    private String generateUniqueShortKey() {
        String shortKey;
        do {
            shortKey = generateRandomShortKey();
        } while (shortUrlRepository.existsByShortKey(shortKey));
        return shortKey;
    }
}
