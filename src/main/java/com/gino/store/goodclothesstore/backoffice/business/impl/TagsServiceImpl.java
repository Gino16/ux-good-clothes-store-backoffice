package com.gino.store.goodclothesstore.backoffice.business.impl;

import com.gino.store.backoffice.model.TagRequest;
import com.gino.store.backoffice.model.TagResponse;
import com.gino.store.backoffice.model.TagsResponse;
import com.gino.store.goodclothesstore.backoffice.business.TagsService;
import com.gino.store.goodclothesstore.backoffice.mapper.TagsMapper;
import com.gino.store.goodclothesstore.backoffice.repository.TagsRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class TagsServiceImpl implements TagsService {

  private final TagsRepository tagsRepository;
  private final TagsMapper tagsMapper;

  @Override
  public Mono<TagsResponse> listTags(String xAuthToken) {
    return getTagsFromRepository(xAuthToken);
  }

  @Override
  public Mono<TagsResponse> getTagsByTitle(String xAuthToken, String title) {
    return Mono.fromCallable(() -> tagsRepository.findByName(title))
        .map(tags -> TagsResponse.builder()
            .tags(tagsMapper.toTagResponse(tags))
            .build())
        .doOnSuccess(tagsResponse -> log.info("Tags response built"));
  }

  private Mono<TagsResponse> getTagsFromRepository(String xAuthToken) {
    return Mono.fromCallable(tagsRepository::listTags)
        .map(tags -> TagsResponse.builder()
            .tags(tagsMapper.toTagResponse(tags))
            .build())
        .doOnSuccess(tagsResponse -> log.info("Tags response built"));
  }

  @Override
  public Mono<Void> addTag(String xAuthToken, Mono<TagRequest> tagRequest) {
    return tagRequest.map(
            tagsMapper::toTag)
        .map(tagsRepository::save)
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
    return Mono.fromCallable(() -> tagsRepository.findById(tagId)
            .orElseThrow(() -> new RuntimeException("Tag not found")))
        .map(tag -> TagResponse.builder()
            .id(tag.getId())
            .title(tag.getTitle())
            .build());
  }
}
