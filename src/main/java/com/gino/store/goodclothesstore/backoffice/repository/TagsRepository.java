package com.gino.store.goodclothesstore.backoffice.repository;

import com.gino.store.goodclothesstore.backoffice.model.Tag;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TagsRepository extends PagingAndSortingRepository<Tag, UUID> {

  @Query(value = "SELECT * FROM {h-schema}tags t", nativeQuery = true)
  List<Tag> listTags();

  Optional<Tag> findById(UUID id);
  Tag save(Tag tag);
  void deleteById(UUID id);
  void saveAll(Iterable<Tag> tags);

}
