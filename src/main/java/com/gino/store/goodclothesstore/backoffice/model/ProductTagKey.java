package com.gino.store.goodclothesstore.backoffice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductTagKey implements Serializable {

  @Column(name = "product_id")
  private String productId;
  @Column(name = "tag_id")
  private String tagId;

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof ProductTagKey) {
      ProductTagKey productTagKey = (ProductTagKey) obj;
      return productTagKey.productId.equals(this.productId) && productTagKey.tagId
          .equals(this.tagId);
    }
    return false;
  }
}
