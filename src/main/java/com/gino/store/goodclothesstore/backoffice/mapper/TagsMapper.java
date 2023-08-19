package com.gino.store.goodclothesstore.backoffice.mapper;

import com.gino.store.backoffice.model.TagRequest;
import com.gino.store.backoffice.model.TagResponse;
import com.gino.store.goodclothesstore.backoffice.model.Tag;
import java.util.List;
import org.springframework.data.domain.Page;

public interface TagsMapper {
  List<TagResponse> tagToTagResponse(List<Tag> tags);
  Tag tagRequestToTag(TagRequest tagRequest);
}
