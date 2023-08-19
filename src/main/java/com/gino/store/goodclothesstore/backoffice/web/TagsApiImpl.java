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
  public Mono<ResponseEntity<Void>> addTag(UUID xAuthToken, Mono<TagRequest> tagRequest,
      ServerWebExchange exchange) {
    return tagsService.addTag(xAuthToken, tagRequest)
        .map(ResponseEntity::ok);
  }


  @Override
  public Mono<ResponseEntity<TagResponse>> getTagById(UUID xAuthToken, UUID tagId,
      ServerWebExchange exchange) {
    return tagsService.getTagById(xAuthToken, tagId)
        .map(ResponseEntity::ok);
  }

  @Override
  public Mono<ResponseEntity<TagsResponse>> listTags(UUID xAuthToken, Integer limit, Integer offset,
      ServerWebExchange exchange) {
    return tagsService.listTags(xAuthToken, limit, offset)
        .map(ResponseEntity::ok);
  }

  @Override
  public Mono<ResponseEntity<Void>> updateTag(UUID tagId, UUID xAuthToken,
      Mono<TagRequest> tagRequest, ServerWebExchange exchange) {
    return TagsApi.super.updateTag(tagId, xAuthToken, tagRequest, exchange);
  }

  @Override
  public Mono<ResponseEntity<Void>> deleteTag(UUID tagId, UUID xAuthToken,
      ServerWebExchange exchange) {
    return tagsService.deleteTag(tagId, xAuthToken)
        .map(ResponseEntity::ok);
  }

}
