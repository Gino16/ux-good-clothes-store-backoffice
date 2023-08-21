package com.gino.store.goodclothesstore.backoffice.business.impl;

import com.gino.store.backoffice.model.Metadata;
import com.gino.store.backoffice.model.TagRequest;
import com.gino.store.backoffice.model.TagResponse;
import com.gino.store.backoffice.model.TagsResponse;
import com.gino.store.goodclothesstore.backoffice.business.TagsService;
import com.gino.store.goodclothesstore.backoffice.mapper.TagsMapper;
import com.gino.store.goodclothesstore.backoffice.model.Tag;
import com.gino.store.goodclothesstore.backoffice.redis.InMemoryService;
import com.gino.store.goodclothesstore.backoffice.repository.TagsRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
@Slf4j
public class TagsServiceImpl implements TagsService {

  private final TagsRepository tagsRepository;
  private final TagsMapper tagsMapper;
  private final InMemoryService redisService;

  @Override
  public Mono<TagsResponse> listTags(String xAuthToken) {
/*    return redisService.getObject(xAuthToken, Session.class)
        .filter(session -> session.getTags() != null)
        .map(session -> TagsResponse.builder()
            .tags(tagsMapper.tagToTagResponse(session.getTags()))
            .build())
        .switchIfEmpty(getTagsFromRepository(xAuthToken));*/
    return getTagsFromRepository(xAuthToken);
  }

  @Override
  public Mono<TagsResponse> getTagsByTitle(String xAuthToken, String title) {
    return null;
  }

  private Mono<TagsResponse> getTagsFromRepository(String xAuthToken) {
    return Mono.fromCallable(tagsRepository::listTags)
        .publishOn(Schedulers.boundedElastic())
/*        .doOnSuccess(tags -> {
          log.info("Tags list retrieved");
          //Save on redis
          redisService.getObject(xAuthToken, Session.class)
              .map(session -> {
                log.info("Session retrieved");
                session.setTags(tags);
                return session;
              })
              .flatMap(session -> redisService.setObject(xAuthToken, session))
              .subscribe();
        })*/
        .map(tags -> TagsResponse.builder()
            .tags(tagsMapper.tagToTagResponse(tags))
            .build())
        .doOnSuccess(tagsResponse -> log.info("Tags response built"));
  }

  @Override
  public Mono<Void> addTag(String xAuthToken, Mono<TagRequest> tagRequest) {
    return tagRequest.map(
            tagsMapper::tagRequestToTag)
        .map(tagsRepository::save)
/*        .flatMap(tag -> redisService.hasKey(xAuthToken)
            .filter(result -> result)
            .switchIfEmpty(redisService.setObject(xAuthToken, Session.builder()
                .tags(List.of(tag)).build()))
        )*/
        .then();
  }

  @Override
  public Mono<Void> deleteTag(String xAuthToken, UUID tagId) {
    return Mono.fromRunnable(() -> tagsRepository.deleteById(tagId))
        .doOnTerminate(() -> log.info("Tag {} deleted", tagId))
        .then();
  }

  @Override
  public Mono<TagResponse> getTagById(String xAuthToken, UUID tagId) {
/*    return redisService.getObject(xAuthToken, Session.class)
        .map(session -> session.getTags().stream()
            .filter(tag -> tag.getId().equals(tagId))
            .findFirst()
            .orElseGet(() -> tagsRepository.findById(tagId)
                .orElseThrow(() -> new RuntimeException("Tag not found"))))
        .map(tag -> TagResponse.builder()
            .id(tag.getId())
            .title(tag.getName())
            .build())
        .switchIfEmpty(Mono.error(new RuntimeException("Session not exists")));*/
    return Mono.fromCallable(() -> tagsRepository.findById(tagId)
            .orElseThrow(() -> new RuntimeException("Tag not found")))
        .map(tag -> TagResponse.builder()
            .id(tag.getId())
            .title(tag.getName())
            .build());
  }





}
