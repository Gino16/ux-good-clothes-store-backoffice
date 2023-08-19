package com.gino.store.goodclothesstore.backoffice.web;

import com.gino.store.backoffice.api.TagsApi;
import com.gino.store.backoffice.model.TagRequest;
import com.gino.store.backoffice.model.TagResponse;
import com.gino.store.backoffice.model.TagsResponse;
import com.gino.store.goodclothesstore.backoffice.business.TagsService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/tags")
public class TagsApiImpl implements TagsApi {

  private final TagsService tagsService;

  @Override
  public Mono<ResponseEntity<TagsResponse>> listTags(Integer limit, Integer offset,
      ServerWebExchange exchange) {
    return tagsService.listTags(limit, offset)
        .map(ResponseEntity::ok);
  }

  @Override
  public Mono<ResponseEntity<TagResponse>> getTagById(UUID tagId,
      ServerWebExchange exchange) {
    return tagsService.getTagById(tagId)
        .map(ResponseEntity::ok);
  }

  @Override
  public Mono<ResponseEntity<Void>> addTag(Mono<TagRequest> tagRequest,
      ServerWebExchange exchange) {
    return tagsService.addTag(tagRequest)
        .map(ResponseEntity::ok);
  }

  @Override
  public Mono<ResponseEntity<Void>> deleteTag(UUID tagId, ServerWebExchange exchange) {
    return tagsService.deleteTag(tagId)
        .map(ResponseEntity::ok);
  }
}
