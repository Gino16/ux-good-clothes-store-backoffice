package com.gino.store.goodclothesstore.backoffice.mapper.impl;

import com.gino.store.backoffice.model.TagRequest;
import com.gino.store.backoffice.model.TagResponse;
import com.gino.store.goodclothesstore.backoffice.mapper.TagsMapper;
import com.gino.store.goodclothesstore.backoffice.model.Tag;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class TagsMapperImpl implements TagsMapper {

  @Override
  public List<TagResponse> toTagResponse(List<Tag> tags) {
    return tags.stream()
        .map(tag -> TagResponse.builder()
            .id(tag.getId())
            .title(tag.getTitle())
            .build())
        .collect(Collectors.toList());
  }

  @Override
  public Tag toTag(TagRequest tagRequest) {
    return Tag.builder()
        .title(tagRequest.getTitle())
        .build();
  }


}
