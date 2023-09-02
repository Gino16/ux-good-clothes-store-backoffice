package com.gino.store.goodclothesstore.backoffice.mapper;

import com.gino.store.backoffice.model.TagRequest;
import com.gino.store.backoffice.model.TagResponse;
import com.gino.store.goodclothesstore.backoffice.model.Tag;
import java.util.List;

public interface TagsMapper {
  List<TagResponse> toTagResponse(List<Tag> tags);
  Tag toTag(TagRequest tagRequest);
}
