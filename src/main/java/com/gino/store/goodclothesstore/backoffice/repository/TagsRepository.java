package com.gino.store.goodclothesstore.backoffice.repository;

import com.gino.store.goodclothesstore.backoffice.model.Tag;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TagsRepository extends PagingAndSortingRepository<Tag, UUID> {

  @Query(value = "SELECT * FROM {h-schema}tags t", nativeQuery = true)
  List<Tag> listTags();

  Optional<Tag> findById(UUID id);

  @Query(value = "SELECT * FROM {h-schema}tags t WHERE t.title LIKE %?1%", nativeQuery = true)
  List<Tag> findByName(String title);

  Tag save(Tag tag);

  void deleteById(UUID id);

  void saveAll(Iterable<Tag> tags);

}
