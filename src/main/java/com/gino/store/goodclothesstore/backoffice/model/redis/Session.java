package com.gino.store.goodclothesstore.backoffice.model.redis;


import com.gino.store.backoffice.model.TagResponse;
import com.gino.store.goodclothesstore.backoffice.model.Tag;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@RedisHash("session")
public class Session {
  private List<Tag> tags;
}
