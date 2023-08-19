package com.gino.store.goodclothesstore.backoffice.business;

import com.gino.store.backoffice.model.TagRequest;
import com.gino.store.backoffice.model.TagResponse;
import com.gino.store.backoffice.model.TagsResponse;
import java.util.UUID;
import reactor.core.publisher.Mono;

public interface TagsService {

  Mono<TagsResponse> listTags(UUID xAuthToken, Integer limit, Integer offset);
  Mono<Void> addTag(UUID xAuthToken, Mono<TagRequest> tagRequest);
  Mono<Void> deleteTag(UUID tagId, UUID xAuthToken);
  Mono<TagResponse> getTagById(UUID tagId, UUID id);
}
