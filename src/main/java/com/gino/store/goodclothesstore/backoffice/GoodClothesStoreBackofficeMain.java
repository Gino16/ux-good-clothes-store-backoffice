package com.gino.store.goodclothesstore.backoffice;

import com.gino.store.goodclothesstore.backoffice.model.Tag;
import com.gino.store.goodclothesstore.backoffice.repository.TagsRepository;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GoodClothesStoreBackofficeMain implements CommandLineRunner {

  @Autowired
  private TagsRepository tagsRepository;

  public static void main(String[] args) {
    SpringApplication.run(GoodClothesStoreBackofficeMain.class, args);
  }

  @Override
  public void run(String... args) {
    FakeValuesService fakeValuesService = new FakeValuesService(Locale.ENGLISH,
        new RandomService());
    List<Tag> tags = new ArrayList<>();
    for (int i = 0; i < 100; i++) {
      tags.add(Tag.builder()
          .title(fakeValuesService.bothify("Tag-?????"))
          .build());
    }
    tagsRepository.saveAll(tags);
  }
}
