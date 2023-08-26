package com.gino.store.goodclothesstore.backoffice.web;

import com.gino.store.backoffice.api.TagsApi;
import com.gino.store.backoffice.model.TagRequest;
import com.gino.store.backoffice.model.TagResponse;
import com.gino.store.backoffice.model.TagsResponse;
import com.gino.store.goodclothesstore.backoffice.business.TagsService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
  public Mono<ResponseEntity<Void>> addTag(String xAuthToken, Mono<TagRequest> tagRequest,
      ServerWebExchange exchange) {
    return tagsService.addTag(xAuthToken, tagRequest)
        .then(Mono.just(ResponseEntity.status(HttpStatus.CREATED).build()));
  }


  @Override
  public Mono<ResponseEntity<TagResponse>> getTagById(String xAuthToken, UUID tagId,
      ServerWebExchange exchange) {
    return tagsService.getTagById(xAuthToken, tagId)
        .map(ResponseEntity::ok);
  }

  @Override
  public Mono<ResponseEntity<TagsResponse>> listTags(String xAuthToken,
      ServerWebExchange exchange) {
    return tagsService.listTags(xAuthToken)
        .map(ResponseEntity::ok);
  }

  @Override
  public Mono<ResponseEntity<TagsResponse>> getTagsByTitle(String xAuthToken, String title,
      ServerWebExchange exchange) {
    return tagsService.getTagsByTitle(xAuthToken, title)
        .map(ResponseEntity::ok);
  }

  @Override
  public Mono<ResponseEntity<Void>> updateTag(String xAuthToken, UUID tagId,
      Mono<TagRequest> tagRequest, ServerWebExchange exchange) {
    return TagsApi.super.updateTag(xAuthToken, tagId, tagRequest, exchange);
  }

  @Override
  public Mono<ResponseEntity<Void>> deleteTag(String xAuthToken, UUID tagId,
      ServerWebExchange exchange) {
    return tagsService.deleteTag(xAuthToken, tagId)
        .then(Mono.just(ResponseEntity.noContent().build()));
  }

}
