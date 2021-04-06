package com.jedosa.junglim.admin.menu.main.domain;

import com.jedosa.junglim.admin.menu.main.dto.ProductSampleImageDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class ProductSampleImage {

    public static final List<Long> SEQUENCES = Arrays.asList(1L, 2L, 3L);
    // 메인 화면에 노출될 수 있는 상품 샘플 이미지 슬라이드의 이미지 수는 최대 4개
    private static final Integer MAX_IMAGE_COUNT = 4;
    private static final Integer MINIMUM_IMAGE_COUNT = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_sample_image_id")
    private Long id;
    // id와 동일한 역할이지만 query로 조작 가능한 고유값을 만들기 위해 sequence를 별도로 추가
    @Column(unique = true, nullable = false)
    private Long sequence;
    private String path;

    public ProductSampleImage(Long sequence, String path) {
        if (sequence > MAX_IMAGE_COUNT || sequence < MINIMUM_IMAGE_COUNT) {
            throw new IllegalArgumentException();
        }
        this.sequence = sequence;
        this.path = path;
    }

    public void update(ProductSampleImageDto dto) {
        this.path = dto.getPath();
    }

    public void deletePath() {
        this.path = "";
    }
}
