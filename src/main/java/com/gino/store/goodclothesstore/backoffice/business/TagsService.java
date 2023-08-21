package com.gino.store.goodclothesstore.backoffice.business;

import com.gino.store.backoffice.model.TagRequest;
import com.gino.store.backoffice.model.TagResponse;
import com.gino.store.backoffice.model.TagsResponse;
import java.util.UUID;
import reactor.core.publisher.Mono;

public interface TagsService {

  Mono<TagsResponse> listTags(String xAuthToken);
  Mono<TagsResponse> getTagsByTitle(String xAuthToken, String title);
  Mono<Void> addTag(String xAuthToken, Mono<TagRequest> tagRequest);
  Mono<Void> deleteTag(String tagId, UUID xAuthToken);
  Mono<TagResponse> getTagById(String tagId, UUID id);
}
