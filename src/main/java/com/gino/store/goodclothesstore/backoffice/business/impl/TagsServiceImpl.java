package com.gino.store.goodclothesstore.backoffice.business.impl;

import static com.gino.store.goodclothesstore.backoffice.util.Utils.toJson;

import com.gino.store.backoffice.model.Metadata;
import com.gino.store.backoffice.model.TagRequest;
import com.gino.store.backoffice.model.TagResponse;
import com.gino.store.backoffice.model.TagsResponse;
import com.gino.store.goodclothesstore.backoffice.business.TagsService;
import com.gino.store.goodclothesstore.backoffice.mapper.TagsMapper;
import com.gino.store.goodclothesstore.backoffice.model.Tag;
import com.gino.store.goodclothesstore.backoffice.repository.TagsRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class TagsServiceImpl implements TagsService {

  private final TagsRepository tagsRepository;
  private final TagsMapper tagsMapper;

  @Override
  public Mono<TagsResponse> listTags(Integer limit, Integer offset) {
    return Mono.fromCallable(() -> tagsRepository.listTags(buildPageRequest(limit, offset)))
        .map(tags -> TagsResponse.builder()
            .tags(tagsMapper.tagToTagResponse(tags.getContent()))
            .metadata(buildMetadata(tags))
            .build());
  }

  @Override
  public Mono<Void> addTag(Mono<TagRequest> tagRequest) {
    return tagRequest.map(
            tagsMapper::tagRequestToTag)
        .map(tagsRepository::save)
        .doOnSuccess(tag -> log.info("Tag {} saved", toJson(tag)))
        .then();
  }

  @Override
  public Mono<Void> deleteTag(UUID tagId) {
    return Mono.fromRunnable(() -> tagsRepository.deleteById(tagId))
        .doOnTerminate(() -> log.info("Tag {} deleted", tagId))
        .then();
  }

  @Override
  public Mono<TagResponse> getTagById(UUID tagId) {
    return Mono.fromCallable(() -> tagsRepository.findById(tagId))
        .map(tag -> tag.orElseThrow(() -> new RuntimeException("Tag not found")))
        .map(tag -> TagResponse.builder()
            .id(tag.getId())
            .title(tag.getName())
            .build());
  }

  private PageRequest buildPageRequest(Integer limit, Integer offset) {
    return PageRequest.of(
        calculatePage(limit, offset),
        limit);
  }

  private static int calculatePage(Integer limit, Integer offset) {
    return (offset / limit) + 1;
  }

  private Metadata buildMetadata(Page<Tag> tags) {
    return Metadata.builder()
        .limit(tags.getNumberOfElements())
        .offset((int) tags.getPageable().getOffset())
        .totalRecords(tags.getContent().size())
        .build();
  }


}
