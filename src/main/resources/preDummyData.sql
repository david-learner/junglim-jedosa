/* 미리 생성 되어야 하는 테이블 및 데이터 */
/* 배송정보 - 배송비 */
CREATE TABLE DELIVERY_PROPERTY (
        fee decimal NOT NULL
);
INSERT INTO DELIVERY_PROPERTY (fee) VALUES (2500);

/* 메인 상품 샘플 슬라이드 이미지 */
INSERT INTO PRODUCT_SAMPLE_IMAGE (path, sequence) values ('', 1);
INSERT INTO PRODUCT_SAMPLE_IMAGE (path, sequence) values ('', 2);
INSERT INTO PRODUCT_SAMPLE_IMAGE (path, sequence) values ('', 3);