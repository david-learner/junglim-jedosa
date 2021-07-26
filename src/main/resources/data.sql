select * from account;

/* 미리 생성 되어야 하는 테이블 및 데이터 시작 */
/* 배송정보 - 배송비 */
CREATE TABLE DELIVERY_PROPERTY (
        fee decimal NOT NULL
);
INSERT INTO DELIVERY_PROPERTY (fee) VALUES (2500);

/* 메인 상품 샘플 슬라이드 이미지 */
INSERT INTO PRODUCT_SAMPLE_IMAGE (path, sequence) values ('\images\dummy\product-sample-image01.jpg', 1);
INSERT INTO PRODUCT_SAMPLE_IMAGE (path, sequence) values ('\images\dummy\product-sample-image02.jpg', 2);
INSERT INTO PRODUCT_SAMPLE_IMAGE (path, sequence) values ('\images\dummy\product-sample-image03.jpg', 3);

/* 간지 문구 출력 비용 */
INSERT INTO FLYLEAF_CONTENT_PRICE (ITEM_ID, NAME, PRICE) VALUES (1, '간지출력비용', 500);
/* 미리 생성 되어야 하는 테이블 및 데이터 끝 */

/* 테스트를 위한 더미 데이터 시작 */
/* 관리자 아이템 옵션 더미 데이터 시작 */
INSERT INTO PAPER_TYPE (ITEM_ID, NAME) VALUES (1, '스노우지');
INSERT INTO PAPER_TYPE (ITEM_ID, NAME) VALUES (1, '아트지');

INSERT INTO PAPER_SIZE_TYPE (ITEM_ID, NAME) VALUES (1, 'A3');
INSERT INTO PAPER_SIZE_TYPE (ITEM_ID, NAME) VALUES (1, 'A4');

INSERT INTO BINDING_TYPE (ITEM_ID, NAME, PRICE) VALUES (1, '무선제본', 1000);
INSERT INTO BINDING_TYPE (ITEM_ID, NAME, PRICE) VALUES (1, '중철제본', 2000);
INSERT INTO BINDING_TYPE (ITEM_ID, NAME, PRICE) VALUES (1, '크리스탈링 제본', 3000);

INSERT INTO COATING_TYPE (ITEM_ID, NAME, PRICE) VALUES (1, '유광', 1000);
INSERT INTO COATING_TYPE (ITEM_ID, NAME, PRICE) VALUES (1, '무광', 1500);
INSERT INTO COATING_TYPE (ITEM_ID, NAME, PRICE) VALUES (1, '펄', 3000);

INSERT INTO DESIGN_TYPE (ITEM_ID, NAME, PRICE) VALUES (1, '디자인 선택없음', 0);
INSERT INTO DESIGN_TYPE (ITEM_ID, NAME, PRICE) VALUES (1, '정림제도사 디자인1', 1000);
INSERT INTO DESIGN_TYPE (ITEM_ID, NAME, PRICE) VALUES (1, '정림제도사 디자인2', 1000);

INSERT INTO PAPER_PRINTING_TYPE (ITEM_ID, NAME, CATEGORY, PRINTING_COLOR, SINGLE_SIDE_PRICE, DOUBLE_SIDE_PRICE, SIZE)
VALUES (1, '스노우지', 'cover', '흑백', '10', '20', 'A3');
INSERT INTO PAPER_PRINTING_TYPE (ITEM_ID, NAME, CATEGORY, PRINTING_COLOR, SINGLE_SIDE_PRICE, DOUBLE_SIDE_PRICE, SIZE)
VALUES (1, '스노우지', 'cover', '컬러', '100', '200', 'A3');
INSERT INTO PAPER_PRINTING_TYPE (ITEM_ID, NAME, CATEGORY, PRINTING_COLOR, SINGLE_SIDE_PRICE, DOUBLE_SIDE_PRICE, SIZE)
VALUES (1, '아트지', 'cover', '흑백', '15', '30', 'A4');
INSERT INTO PAPER_PRINTING_TYPE (ITEM_ID, NAME, CATEGORY, PRINTING_COLOR, SINGLE_SIDE_PRICE, DOUBLE_SIDE_PRICE, SIZE)
VALUES (1, '아트지', 'cover', '컬러', '150', '300', 'A4');

INSERT INTO PAPER_PRINTING_TYPE (ITEM_ID, NAME, CATEGORY, PRINTING_COLOR, SINGLE_SIDE_PRICE, DOUBLE_SIDE_PRICE, SIZE)
VALUES (1, '스노우지', 'content', '흑백', '10', '20', 'A3');
INSERT INTO PAPER_PRINTING_TYPE (ITEM_ID, NAME, CATEGORY, PRINTING_COLOR, SINGLE_SIDE_PRICE, DOUBLE_SIDE_PRICE, SIZE)
VALUES (1, '스노우지', 'content', '컬러', '100', '200', 'A3');
INSERT INTO PAPER_PRINTING_TYPE (ITEM_ID, NAME, CATEGORY, PRINTING_COLOR, SINGLE_SIDE_PRICE, DOUBLE_SIDE_PRICE, SIZE)
VALUES (1, '아트지', 'content', '흑백', '15', '30', 'A4');
INSERT INTO PAPER_PRINTING_TYPE (ITEM_ID, NAME, CATEGORY, PRINTING_COLOR, SINGLE_SIDE_PRICE, DOUBLE_SIDE_PRICE, SIZE)
VALUES (1, '아트지', 'content', '컬러', '150', '300', 'A4');

INSERT INTO FLYLEAF_COLOR_TYPE (ITEM_ID, NAME, PRICE) VALUES (1, '분홍', 50);
INSERT INTO FLYLEAF_COLOR_TYPE (ITEM_ID, NAME, PRICE) VALUES (1, '노랑', 50);
INSERT INTO FLYLEAF_COLOR_TYPE (ITEM_ID, NAME, PRICE) VALUES (1, '파랑', 50);
/* 관리자 아이템 옵션 더미 데이터 끝 */


INSERT INTO ACCOUNT (address, detailed_address, zipcode, created_date_time, email, name, password, personal_info_agreement, phone, site_usage_agreement, grade) values ('서울 동작구 동작대로 33길 13-13', '2층', '06997', now(), 'admin@junglim.com', '관리자', '11111111', TRUE, '01012345678', TRUE, 'ADMIN');
INSERT INTO ACCOUNT (address, detailed_address, zipcode, created_date_time, email, name, password, personal_info_agreement, phone, site_usage_agreement, grade) values ('서울 동작구 동작대로 33길 13-13', '2층', '06997', now(), 'normal@junglim.com', '김일반', '11111111', TRUE, '01012345678', TRUE, 'NORMAL');

insert into ONE_PAGE_MENU (title, content) values ('주문방법', '<h1>주문방법</h1>');
insert into ONE_PAGE_MENU (title, content) values ('가격표', '<h1 style="text-align: center; ">출력 가격표</h1><p style="text-align: right;">※ 대량 주문시 별도 문의 051.971.4704</p>
<table class="table table-bordered" border="1">
  <tbody>
    <tr>
      <td rowspan="2" style="text-align: center; ">항목</td>
      <td colspan="2" style="text-align: center;">출력</td>
      <td colspan="2" style="text-align: center;">CAD 출력</td>
      <td rowspan="2" style="text-align: center;">유포지</td>
      <td rowspan="2" style="text-align: center;">스프링 무선제본</td>
      <td rowspan="2" style="text-align: center;">컬러 무선제본</td>
      <td rowspan="2" style="text-align: center;">컬러코팅 무선제본</td>
    </tr>
    <tr>
      <td style="text-align: center;">흑백</td>
      <td style="text-align: center;">컬러</td>
      <td style="text-align: center;">흑백</td>
      <td style="text-align: center;">컬러</td>
    </tr>
    <tr>
      <td style="text-align: center;">A4 단면</td>
      <td style="text-align: center;">30</td>
      <td style="text-align: center;">200</td>
      <td style="text-align: center;">400</td>
      <td style="text-align: center;">500</td>
      <td style="text-align: center;"></td>
      <td style="text-align: center;">2000</td>
      <td style="text-align: center;">3000</td>
      <td style="text-align: center;">4000</td>
    </tr>
    <tr>
        <td style="text-align: center;">A4 양면</td>
        <td style="text-align: center;">25</td>
        <td style="text-align: center;">200</td>
        <td style="text-align: center;"></td>
        <td style="text-align: center;"></td>
        <td style="text-align: center;"></td>
        <td style="text-align: center;">2000</td>
        <td style="text-align: center;">3000</td>
        <td style="text-align: center;">4000</td>
    </tr>
    <tr>
        <td style="text-align: center;">A3</td>
        <td style="text-align: center;">60</td>
        <td style="text-align: center;">400</td>
        <td style="text-align: center;">500</td>
        <td style="text-align: center;">800</td>
        <td style="text-align: center;"></td>
        <td style="text-align: center;">2000</td>
        <td style="text-align: center;">3000</td>
        <td style="text-align: center;">4000</td>
    </tr>
    <tr>
        <td style="text-align: center;">A2</td>
        <td style="text-align: center;">500</td>
        <td style="text-align: center;">3000</td>
        <td style="text-align: center;">800</td>
        <td style="text-align: center;">3000</td>
        <td style="text-align: center;">5000</td>
        <td style="text-align: center;"></td>
        <td style="text-align: center;"></td>
        <td style="text-align: center;"></td>
    </tr>
    <tr>
        <td style="text-align: center;">A1</td>
        <td style="text-align: center;">1000</td>
        <td style="text-align: center;">5000</td>
        <td style="text-align: center;">1000</td>
        <td style="text-align: center;">5000</td>
        <td style="text-align: center;">8000</td>
        <td style="text-align: center;"></td>
        <td style="text-align: center;"></td>
        <td style="text-align: center;"></td>
    </tr>
    <tr>
        <td style="text-align: center;">A0</td>
        <td style="text-align: center;">2000</td>
        <td style="text-align: center;">8000</td>
        <td style="text-align: center;">2000</td>
        <td style="text-align: center;">8000</td>
        <td style="text-align: center;">15000</td>
        <td style="text-align: center;"></td>
        <td style="text-align: center;"></td>
        <td style="text-align: center;"></td>
    </tr>
  </tbody>
</table><p style="text-align: center; ">현수막, 실사출력, 폼보드, 현황판 제작, 액자제작 별도 문의</p>');
insert into ONE_PAGE_MENU (title, content) values ('회사소개', '<h1>회사소개</h1>');

/* dummy 결제(payment) */
INSERT INTO PAYMENT VALUES (default, '예금주1', 5501.00, null, null, now(), null, null, 'admin@junglim.com', '관리자', '01012345678', null, '무통장입금', 'WAITING');
INSERT INTO PAYMENT VALUES (default, '예금주2', 5502.00, null, null, now(), null, null, 'admin@junglim.com', '관리자', '01012345678', null, '무통장입금', 'WAITING');
INSERT INTO PAYMENT VALUES (default, '예금주3', 5503.00, null, null, now(), null, null, 'admin@junglim.com', '관리자', '01012345678', null, '무통장입금', 'WAITING');
INSERT INTO PAYMENT VALUES (default, '예금주4', 5504.00, null, null, now(), null, null, 'admin@junglim.com', '관리자', '01012345678', null, '무통장입금', 'WAITING');
INSERT INTO PAYMENT VALUES (default, '예금주5', 5505.00, null, null, now(), null, null, 'admin@junglim.com', '관리자', '01012345678', null, '무통장입금', 'WAITING');
INSERT INTO PAYMENT VALUES (default, '예금주6', 5506.00, null, null, now(), null, null, 'admin@junglim.com', '관리자', '01012345678', null, '무통장입금', 'WAITING');
INSERT INTO PAYMENT VALUES (default, '예금주7', 5507.00, null, null, now(), null, null, 'admin@junglim.com', '관리자', '01012345678', null, '무통장입금', 'WAITING');
INSERT INTO PAYMENT VALUES (default, '예금주8', 5508.00, null, null, now(), null, null, 'admin@junglim.com', '관리자', '01012345678', null, '무통장입금', 'WAITING');
INSERT INTO PAYMENT VALUES (default, '예금주9', 5509.00, null, null, now(), null, null, 'admin@junglim.com', '관리자', '01012345678', null, '무통장입금', 'WAITING');
INSERT INTO PAYMENT VALUES (default, '예금주10', 5510.00, null, null, now(), null, null, 'admin@junglim.com', '관리자', '01012345678', null, '무통장입금', 'WAITING');
INSERT INTO PAYMENT VALUES (default, '예금주11', 5511.00, null, null, now(), null, null, 'admin@junglim.com', '관리자', '01012345678', null, '무통장입금', 'WAITING');
INSERT INTO PAYMENT VALUES (default, '예금주12', 5512.00, null, null, now(), null, null, 'admin@junglim.com', '관리자', '01012345678', null, '무통장입금', 'WAITING');
INSERT INTO PAYMENT VALUES (default, '예금주13', 5513.00, null, null, now(), null, null, 'admin@junglim.com', '관리자', '01012345678', null, '무통장입금', 'WAITING');
INSERT INTO PAYMENT VALUES (default, '예금주14', 5514.00, null, null, now(), null, null, 'admin@junglim.com', '관리자', '01012345678', null, '무통장입금', 'WAITING');
INSERT INTO PAYMENT VALUES (default, '예금주15', 5515.00, null, null, now(), null, null, 'admin@junglim.com', '관리자', '01012345678', null, '무통장입금', 'WAITING');
INSERT INTO PAYMENT VALUES (default, '예금주16', 5516.00, null, null, now(), null, null, 'admin@junglim.com', '관리자', '01012345678', null, '무통장입금', 'WAITING');
INSERT INTO PAYMENT VALUES (default, '예금주17', 5517.00, null, null, now(), null, null, 'admin@junglim.com', '관리자', '01012345678', null, '무통장입금', 'WAITING');
INSERT INTO PAYMENT VALUES (default, '예금주18', 5518.00, null, null, now(), null, null, 'admin@junglim.com', '관리자', '01012345678', null, '무통장입금', 'WAITING');
INSERT INTO PAYMENT VALUES (default, '예금주19', 5519.00, null, null, now(), null, null, 'admin@junglim.com', '관리자', '01012345678', null, '무통장입금', 'WAITING');
INSERT INTO PAYMENT VALUES (default, '예금주20', 5520.00, null, null, now(), null, null, 'admin@junglim.com', '관리자', '01012345678', null, '무통장입금', 'WAITING');
-- INSERT INTO PAYMENT (account_holder_name, amount, created_date_time, orderer_email, orderer_name, orderer_phone, payment_method, payment_status)
-- values ('김일반', 3600, now(), 'normal@junglim.com', '김일반', '01012345678', '무통장입금', 'WAITING');

/* dummy 주문(orders) */
INSERT INTO ORDERS VALUES (default, 5500, now(), 0, 'VISIT', '배송 요청 메시지', '서울 동작구 동작대로 33길 13-13', '2층', '06997', '수령인', '01012345678','ORDER_COMPLETE', 5500, 1, 1);
INSERT INTO ORDERS VALUES (default, 5500, now(), 0, 'VISIT', '배송 요청 메시지', '서울 동작구 동작대로 33길 13-13', '2층', '06997', '수령인', '01012345678','ORDER_COMPLETE', 5500, 1, 2);
INSERT INTO ORDERS VALUES (default, 5500, now(), 0, 'VISIT', '배송 요청 메시지', '서울 동작구 동작대로 33길 13-13', '2층', '06997', '수령인', '01012345678','ORDER_COMPLETE', 5500, 1, 3);
INSERT INTO ORDERS VALUES (default, 5500, now(), 0, 'VISIT', '배송 요청 메시지', '서울 동작구 동작대로 33길 13-13', '2층', '06997', '수령인', '01012345678','ORDER_COMPLETE', 5500, 1, 4);
INSERT INTO ORDERS VALUES (default, 5500, now(), 0, 'VISIT', '배송 요청 메시지', '서울 동작구 동작대로 33길 13-13', '2층', '06997', '수령인', '01012345678','ORDER_COMPLETE', 5500, 1, 5);
INSERT INTO ORDERS VALUES (default, 5500, now(), 0, 'VISIT', '배송 요청 메시지', '서울 동작구 동작대로 33길 13-13', '2층', '06997', '수령인', '01012345678','ORDER_COMPLETE', 5500, 1, 6);
INSERT INTO ORDERS VALUES (default, 5500, now(), 0, 'VISIT', '배송 요청 메시지', '서울 동작구 동작대로 33길 13-13', '2층', '06997', '수령인', '01012345678','ORDER_COMPLETE', 5500, 1, 7);
INSERT INTO ORDERS VALUES (default, 5500, now(), 0, 'VISIT', '배송 요청 메시지', '서울 동작구 동작대로 33길 13-13', '2층', '06997', '수령인', '01012345678','ORDER_COMPLETE', 5500, 1, 8);
INSERT INTO ORDERS VALUES (default, 5500, now(), 0, 'VISIT', '배송 요청 메시지', '서울 동작구 동작대로 33길 13-13', '2층', '06997', '수령인', '01012345678','ORDER_COMPLETE', 5500, 1, 9);
INSERT INTO ORDERS VALUES (default, 5500, now(), 0, 'VISIT', '배송 요청 메시지', '서울 동작구 동작대로 33길 13-13', '2층', '06997', '수령인', '01012345678','ORDER_COMPLETE', 5500, 1, 10);
INSERT INTO ORDERS VALUES (default, 5500, now(), 0, 'VISIT', '배송 요청 메시지', '서울 동작구 동작대로 33길 13-13', '2층', '06997', '수령인', '01012345678','ORDER_COMPLETE', 5500, 1, 11);
INSERT INTO ORDERS VALUES (default, 5500, now(), 0, 'VISIT', '배송 요청 메시지', '서울 동작구 동작대로 33길 13-13', '2층', '06997', '수령인', '01012345678','ORDER_COMPLETE', 5500, 1, 12);
INSERT INTO ORDERS VALUES (default, 5500, now(), 0, 'VISIT', '배송 요청 메시지', '서울 동작구 동작대로 33길 13-13', '2층', '06997', '수령인', '01012345678','ORDER_COMPLETE', 5500, 1, 13);
INSERT INTO ORDERS VALUES (default, 5500, now(), 0, 'VISIT', '배송 요청 메시지', '서울 동작구 동작대로 33길 13-13', '2층', '06997', '수령인', '01012345678','ORDER_COMPLETE', 5500, 1, 14);
INSERT INTO ORDERS VALUES (default, 5500, now(), 0, 'VISIT', '배송 요청 메시지', '서울 동작구 동작대로 33길 13-13', '2층', '06997', '수령인', '01012345678','ORDER_COMPLETE', 5500, 1, 15);
INSERT INTO ORDERS VALUES (default, 5500, now(), 0, 'VISIT', '배송 요청 메시지', '서울 동작구 동작대로 33길 13-13', '2층', '06997', '수령인', '01012345678','ORDER_COMPLETE', 5500, 1, 16);
INSERT INTO ORDERS VALUES (default, 5500, now(), 0, 'VISIT', '배송 요청 메시지', '서울 동작구 동작대로 33길 13-13', '2층', '06997', '수령인', '01012345678','ORDER_COMPLETE', 5500, 1, 17);
INSERT INTO ORDERS VALUES (default, 5500, now(), 0, 'VISIT', '배송 요청 메시지', '서울 동작구 동작대로 33길 13-13', '2층', '06997', '수령인', '01012345678','ORDER_COMPLETE', 5500, 1, 18);
INSERT INTO ORDERS VALUES (default, 5500, now(), 0, 'VISIT', '배송 요청 메시지', '서울 동작구 동작대로 33길 13-13', '2층', '06997', '수령인', '01012345678','ORDER_COMPLETE', 5500, 1, 19);
INSERT INTO ORDERS VALUES (default, 5500, now(), 0, 'VISIT', '배송 요청 메시지', '서울 동작구 동작대로 33길 13-13', '2층', '06997', '수령인', '01012345678','ORDER_COMPLETE', 5500, 1, 20);
-- INSERT INTO ORDERS (all_items_total_price, created_date_time, delivery_fee, delivery_type, message_to_deliver, address, detailed_address, zipcode, receiver_name, receiver_phone, is_ordered, total_price, account_id, payment_id)
-- values (1100, now(), 2500, 'PARCEL', '배송 요청사항', '서울 동작구 동작대로33길 13-13', '2층', '06997', '김보통', '01012345678', TRUE, 3600, 1, 1);

/* dummy 주문 아이템(order item) */
INSERT INTO ORDER_ITEM(GENERATED_DATE_TIME, IS_DELETED, IS_IN_CART, ITEM_ID, ITEM_NAME,
                       BINDING_DIRECTION, BINDING_TYPE, BOOK_COUNT, CONTENT_PAGE_COUNT, CONTENT_PAPER_TYPE,
                       CONTENT_PRINTING_COLOR_TYPE, CONTENT_PRINTING_TYPE, COVER_COATING_TYPE, COVER_DESIGN_NAME, COVER_PAPER_TYPE,
                       COVER_PRINTING_COLOR_TYPE, COVER_PRINTING_TYPE, CUSTOMER_COMMENT, FLYLEAF_COLOR_TYPE, FLYLEAF_CONTENT_PRINTING_VALUE,
                       FLYLEAF_COUNT_PER_BOOK,FLYLEAF_INSERT_LOCATION, HAS_FLYLEAF, HAS_FLYLEAF_CONTENT_PRINTING, PAGE_COUNT_PER_BOOK,
                       PAPER_SIZE, ITEM_PRICE, ORDERER_ID, TOTAL_PRICE, VAT,
                       ORDERS_ID)
VALUES (now(), FALSE, FALSE, 1, '인쇄 및 제본',
        '좌철', '무선', 1, 10, '듀오매트-스노우지-백색-200g',
        '흑백', '단면', '무광', '디자인 선택안함', '듀오매트-스노우지-백색-200g',
        '흑백', '단면', '고객의 요청사항입니다.', 'yellow', '',
        1, '', FALSE, FALSE, 1,
        'a4', 1000, 1, 1000, 100,
        1);
INSERT INTO ORDER_ITEM(GENERATED_DATE_TIME, IS_DELETED, IS_IN_CART, ITEM_ID, ITEM_NAME,
                       BINDING_DIRECTION, BINDING_TYPE, BOOK_COUNT, CONTENT_PAGE_COUNT, CONTENT_PAPER_TYPE,
                       CONTENT_PRINTING_COLOR_TYPE, CONTENT_PRINTING_TYPE, COVER_COATING_TYPE, COVER_DESIGN_NAME, COVER_PAPER_TYPE,
                       COVER_PRINTING_COLOR_TYPE, COVER_PRINTING_TYPE, CUSTOMER_COMMENT, FLYLEAF_COLOR_TYPE, FLYLEAF_CONTENT_PRINTING_VALUE,
                       FLYLEAF_COUNT_PER_BOOK,FLYLEAF_INSERT_LOCATION, HAS_FLYLEAF, HAS_FLYLEAF_CONTENT_PRINTING, PAGE_COUNT_PER_BOOK,
                       PAPER_SIZE, ITEM_PRICE, ORDERER_ID, TOTAL_PRICE, VAT,
                       ORDERS_ID)
VALUES (now(), FALSE, FALSE, 1, '인쇄 및 제본',
        '좌철', '무선', 1, 10, '듀오매트-스노우지-백색-200g',
        '흑백', '단면', '무광', '디자인 선택안함', '듀오매트-스노우지-백색-200g',
        '흑백', '단면', '고객의 요청사항입니다.', 'yellow', '',
        1, '', FALSE, FALSE, 1,
        'a4', 1000, 1, 1000, 100,
        2);
INSERT INTO ORDER_ITEM(GENERATED_DATE_TIME, IS_DELETED, IS_IN_CART, ITEM_ID, ITEM_NAME,
                       BINDING_DIRECTION, BINDING_TYPE, BOOK_COUNT, CONTENT_PAGE_COUNT, CONTENT_PAPER_TYPE,
                       CONTENT_PRINTING_COLOR_TYPE, CONTENT_PRINTING_TYPE, COVER_COATING_TYPE, COVER_DESIGN_NAME, COVER_PAPER_TYPE,
                       COVER_PRINTING_COLOR_TYPE, COVER_PRINTING_TYPE, CUSTOMER_COMMENT, FLYLEAF_COLOR_TYPE, FLYLEAF_CONTENT_PRINTING_VALUE,
                       FLYLEAF_COUNT_PER_BOOK,FLYLEAF_INSERT_LOCATION, HAS_FLYLEAF, HAS_FLYLEAF_CONTENT_PRINTING, PAGE_COUNT_PER_BOOK,
                       PAPER_SIZE, ITEM_PRICE, ORDERER_ID, TOTAL_PRICE, VAT,
                       ORDERS_ID)
VALUES (now(), FALSE, FALSE, 1, '인쇄 및 제본',
        '좌철', '무선', 1, 10, '듀오매트-스노우지-백색-200g',
        '흑백', '단면', '무광', '디자인 선택안함', '듀오매트-스노우지-백색-200g',
        '흑백', '단면', '고객의 요청사항입니다.', 'yellow', '',
        1, '', FALSE, FALSE, 1,
        'a4', 1000, 1, 1000, 100,
        3);
INSERT INTO ORDER_ITEM(GENERATED_DATE_TIME, IS_DELETED, IS_IN_CART, ITEM_ID, ITEM_NAME,
                       BINDING_DIRECTION, BINDING_TYPE, BOOK_COUNT, CONTENT_PAGE_COUNT, CONTENT_PAPER_TYPE,
                       CONTENT_PRINTING_COLOR_TYPE, CONTENT_PRINTING_TYPE, COVER_COATING_TYPE, COVER_DESIGN_NAME, COVER_PAPER_TYPE,
                       COVER_PRINTING_COLOR_TYPE, COVER_PRINTING_TYPE, CUSTOMER_COMMENT, FLYLEAF_COLOR_TYPE, FLYLEAF_CONTENT_PRINTING_VALUE,
                       FLYLEAF_COUNT_PER_BOOK,FLYLEAF_INSERT_LOCATION, HAS_FLYLEAF, HAS_FLYLEAF_CONTENT_PRINTING, PAGE_COUNT_PER_BOOK,
                       PAPER_SIZE, ITEM_PRICE, ORDERER_ID, TOTAL_PRICE, VAT,
                       ORDERS_ID)
VALUES (now(), FALSE, FALSE, 1, '인쇄 및 제본',
        '좌철', '무선', 1, 10, '듀오매트-스노우지-백색-200g',
        '흑백', '단면', '무광', '디자인 선택안함', '듀오매트-스노우지-백색-200g',
        '흑백', '단면', '고객의 요청사항입니다.', 'yellow', '',
        1, '', FALSE, FALSE, 1,
        'a4', 1000, 1, 1000, 100,
        4);
INSERT INTO ORDER_ITEM(GENERATED_DATE_TIME, IS_DELETED, IS_IN_CART, ITEM_ID, ITEM_NAME,
                       BINDING_DIRECTION, BINDING_TYPE, BOOK_COUNT, CONTENT_PAGE_COUNT, CONTENT_PAPER_TYPE,
                       CONTENT_PRINTING_COLOR_TYPE, CONTENT_PRINTING_TYPE, COVER_COATING_TYPE, COVER_DESIGN_NAME, COVER_PAPER_TYPE,
                       COVER_PRINTING_COLOR_TYPE, COVER_PRINTING_TYPE, CUSTOMER_COMMENT, FLYLEAF_COLOR_TYPE, FLYLEAF_CONTENT_PRINTING_VALUE,
                       FLYLEAF_COUNT_PER_BOOK,FLYLEAF_INSERT_LOCATION, HAS_FLYLEAF, HAS_FLYLEAF_CONTENT_PRINTING, PAGE_COUNT_PER_BOOK,
                       PAPER_SIZE, ITEM_PRICE, ORDERER_ID, TOTAL_PRICE, VAT,
                       ORDERS_ID)
VALUES (now(), FALSE, FALSE, 1, '인쇄 및 제본',
        '좌철', '무선', 1, 10, '듀오매트-스노우지-백색-200g',
        '흑백', '단면', '무광', '디자인 선택안함', '듀오매트-스노우지-백색-200g',
        '흑백', '단면', '고객의 요청사항입니다.', 'yellow', '',
        1, '', FALSE, FALSE, 1,
        'a4', 1000, 1, 1000, 100,
        5);
INSERT INTO ORDER_ITEM(GENERATED_DATE_TIME, IS_DELETED, IS_IN_CART, ITEM_ID, ITEM_NAME,
                       BINDING_DIRECTION, BINDING_TYPE, BOOK_COUNT, CONTENT_PAGE_COUNT, CONTENT_PAPER_TYPE,
                       CONTENT_PRINTING_COLOR_TYPE, CONTENT_PRINTING_TYPE, COVER_COATING_TYPE, COVER_DESIGN_NAME, COVER_PAPER_TYPE,
                       COVER_PRINTING_COLOR_TYPE, COVER_PRINTING_TYPE, CUSTOMER_COMMENT, FLYLEAF_COLOR_TYPE, FLYLEAF_CONTENT_PRINTING_VALUE,
                       FLYLEAF_COUNT_PER_BOOK,FLYLEAF_INSERT_LOCATION, HAS_FLYLEAF, HAS_FLYLEAF_CONTENT_PRINTING, PAGE_COUNT_PER_BOOK,
                       PAPER_SIZE, ITEM_PRICE, ORDERER_ID, TOTAL_PRICE, VAT,
                       ORDERS_ID)
VALUES (now(), FALSE, FALSE, 1, '인쇄 및 제본',
        '좌철', '무선', 1, 10, '듀오매트-스노우지-백색-200g',
        '흑백', '단면', '무광', '디자인 선택안함', '듀오매트-스노우지-백색-200g',
        '흑백', '단면', '고객의 요청사항입니다.', 'yellow', '',
        1, '', FALSE, FALSE, 1,
        'a4', 1000, 1, 1000, 100,
        6);
INSERT INTO ORDER_ITEM(GENERATED_DATE_TIME, IS_DELETED, IS_IN_CART, ITEM_ID, ITEM_NAME,
                       BINDING_DIRECTION, BINDING_TYPE, BOOK_COUNT, CONTENT_PAGE_COUNT, CONTENT_PAPER_TYPE,
                       CONTENT_PRINTING_COLOR_TYPE, CONTENT_PRINTING_TYPE, COVER_COATING_TYPE, COVER_DESIGN_NAME, COVER_PAPER_TYPE,
                       COVER_PRINTING_COLOR_TYPE, COVER_PRINTING_TYPE, CUSTOMER_COMMENT, FLYLEAF_COLOR_TYPE, FLYLEAF_CONTENT_PRINTING_VALUE,
                       FLYLEAF_COUNT_PER_BOOK,FLYLEAF_INSERT_LOCATION, HAS_FLYLEAF, HAS_FLYLEAF_CONTENT_PRINTING, PAGE_COUNT_PER_BOOK,
                       PAPER_SIZE, ITEM_PRICE, ORDERER_ID, TOTAL_PRICE, VAT,
                       ORDERS_ID)
VALUES (now(), FALSE, FALSE, 1, '인쇄 및 제본',
        '좌철', '무선', 1, 10, '듀오매트-스노우지-백색-200g',
        '흑백', '단면', '무광', '디자인 선택안함', '듀오매트-스노우지-백색-200g',
        '흑백', '단면', '고객의 요청사항입니다.', 'yellow', '',
        1, '', FALSE, FALSE, 1,
        'a4', 1000, 1, 1000, 100,
        7);
INSERT INTO ORDER_ITEM(GENERATED_DATE_TIME, IS_DELETED, IS_IN_CART, ITEM_ID, ITEM_NAME,
                       BINDING_DIRECTION, BINDING_TYPE, BOOK_COUNT, CONTENT_PAGE_COUNT, CONTENT_PAPER_TYPE,
                       CONTENT_PRINTING_COLOR_TYPE, CONTENT_PRINTING_TYPE, COVER_COATING_TYPE, COVER_DESIGN_NAME, COVER_PAPER_TYPE,
                       COVER_PRINTING_COLOR_TYPE, COVER_PRINTING_TYPE, CUSTOMER_COMMENT, FLYLEAF_COLOR_TYPE, FLYLEAF_CONTENT_PRINTING_VALUE,
                       FLYLEAF_COUNT_PER_BOOK,FLYLEAF_INSERT_LOCATION, HAS_FLYLEAF, HAS_FLYLEAF_CONTENT_PRINTING, PAGE_COUNT_PER_BOOK,
                       PAPER_SIZE, ITEM_PRICE, ORDERER_ID, TOTAL_PRICE, VAT,
                       ORDERS_ID)
VALUES (now(), FALSE, FALSE, 1, '인쇄 및 제본',
        '좌철', '무선', 1, 10, '듀오매트-스노우지-백색-200g',
        '흑백', '단면', '무광', '디자인 선택안함', '듀오매트-스노우지-백색-200g',
        '흑백', '단면', '고객의 요청사항입니다.', 'yellow', '',
        1, '', FALSE, FALSE, 1,
        'a4', 1000, 1, 1000, 100,
        8);
INSERT INTO ORDER_ITEM(GENERATED_DATE_TIME, IS_DELETED, IS_IN_CART, ITEM_ID, ITEM_NAME,
                       BINDING_DIRECTION, BINDING_TYPE, BOOK_COUNT, CONTENT_PAGE_COUNT, CONTENT_PAPER_TYPE,
                       CONTENT_PRINTING_COLOR_TYPE, CONTENT_PRINTING_TYPE, COVER_COATING_TYPE, COVER_DESIGN_NAME, COVER_PAPER_TYPE,
                       COVER_PRINTING_COLOR_TYPE, COVER_PRINTING_TYPE, CUSTOMER_COMMENT, FLYLEAF_COLOR_TYPE, FLYLEAF_CONTENT_PRINTING_VALUE,
                       FLYLEAF_COUNT_PER_BOOK,FLYLEAF_INSERT_LOCATION, HAS_FLYLEAF, HAS_FLYLEAF_CONTENT_PRINTING, PAGE_COUNT_PER_BOOK,
                       PAPER_SIZE, ITEM_PRICE, ORDERER_ID, TOTAL_PRICE, VAT,
                       ORDERS_ID)
VALUES (now(), FALSE, FALSE, 1, '인쇄 및 제본',
        '좌철', '무선', 1, 10, '듀오매트-스노우지-백색-200g',
        '흑백', '단면', '무광', '디자인 선택안함', '듀오매트-스노우지-백색-200g',
        '흑백', '단면', '고객의 요청사항입니다.', 'yellow', '',
        1, '', FALSE, FALSE, 1,
        'a4', 1000, 1, 1000, 100,
        9);
INSERT INTO ORDER_ITEM(GENERATED_DATE_TIME, IS_DELETED, IS_IN_CART, ITEM_ID, ITEM_NAME,
                       BINDING_DIRECTION, BINDING_TYPE, BOOK_COUNT, CONTENT_PAGE_COUNT, CONTENT_PAPER_TYPE,
                       CONTENT_PRINTING_COLOR_TYPE, CONTENT_PRINTING_TYPE, COVER_COATING_TYPE, COVER_DESIGN_NAME, COVER_PAPER_TYPE,
                       COVER_PRINTING_COLOR_TYPE, COVER_PRINTING_TYPE, CUSTOMER_COMMENT, FLYLEAF_COLOR_TYPE, FLYLEAF_CONTENT_PRINTING_VALUE,
                       FLYLEAF_COUNT_PER_BOOK,FLYLEAF_INSERT_LOCATION, HAS_FLYLEAF, HAS_FLYLEAF_CONTENT_PRINTING, PAGE_COUNT_PER_BOOK,
                       PAPER_SIZE, ITEM_PRICE, ORDERER_ID, TOTAL_PRICE, VAT,
                       ORDERS_ID)
VALUES (now(), FALSE, FALSE, 1, '인쇄 및 제본',
        '좌철', '무선', 1, 10, '듀오매트-스노우지-백색-200g',
        '흑백', '단면', '무광', '디자인 선택안함', '듀오매트-스노우지-백색-200g',
        '흑백', '단면', '고객의 요청사항입니다.', 'yellow', '',
        1, '', FALSE, FALSE, 1,
        'a4', 1000, 1, 1000, 100,
        10);
INSERT INTO ORDER_ITEM(GENERATED_DATE_TIME, IS_DELETED, IS_IN_CART, ITEM_ID, ITEM_NAME,
                       BINDING_DIRECTION, BINDING_TYPE, BOOK_COUNT, CONTENT_PAGE_COUNT, CONTENT_PAPER_TYPE,
                       CONTENT_PRINTING_COLOR_TYPE, CONTENT_PRINTING_TYPE, COVER_COATING_TYPE, COVER_DESIGN_NAME, COVER_PAPER_TYPE,
                       COVER_PRINTING_COLOR_TYPE, COVER_PRINTING_TYPE, CUSTOMER_COMMENT, FLYLEAF_COLOR_TYPE, FLYLEAF_CONTENT_PRINTING_VALUE,
                       FLYLEAF_COUNT_PER_BOOK,FLYLEAF_INSERT_LOCATION, HAS_FLYLEAF, HAS_FLYLEAF_CONTENT_PRINTING, PAGE_COUNT_PER_BOOK,
                       PAPER_SIZE, ITEM_PRICE, ORDERER_ID, TOTAL_PRICE, VAT,
                       ORDERS_ID)
VALUES (now(), FALSE, FALSE, 1, '인쇄 및 제본',
        '좌철', '무선', 1, 10, '듀오매트-스노우지-백색-200g',
        '흑백', '단면', '무광', '디자인 선택안함', '듀오매트-스노우지-백색-200g',
        '흑백', '단면', '고객의 요청사항입니다.', 'yellow', '',
        1, '', FALSE, FALSE, 1,
        'a4', 1000, 1, 1000, 100,
        11);
INSERT INTO ORDER_ITEM(GENERATED_DATE_TIME, IS_DELETED, IS_IN_CART, ITEM_ID, ITEM_NAME,
                       BINDING_DIRECTION, BINDING_TYPE, BOOK_COUNT, CONTENT_PAGE_COUNT, CONTENT_PAPER_TYPE,
                       CONTENT_PRINTING_COLOR_TYPE, CONTENT_PRINTING_TYPE, COVER_COATING_TYPE, COVER_DESIGN_NAME, COVER_PAPER_TYPE,
                       COVER_PRINTING_COLOR_TYPE, COVER_PRINTING_TYPE, CUSTOMER_COMMENT, FLYLEAF_COLOR_TYPE, FLYLEAF_CONTENT_PRINTING_VALUE,
                       FLYLEAF_COUNT_PER_BOOK,FLYLEAF_INSERT_LOCATION, HAS_FLYLEAF, HAS_FLYLEAF_CONTENT_PRINTING, PAGE_COUNT_PER_BOOK,
                       PAPER_SIZE, ITEM_PRICE, ORDERER_ID, TOTAL_PRICE, VAT,
                       ORDERS_ID)
VALUES (now(), FALSE, FALSE, 1, '인쇄 및 제본',
        '좌철', '무선', 1, 10, '듀오매트-스노우지-백색-200g',
        '흑백', '단면', '무광', '디자인 선택안함', '듀오매트-스노우지-백색-200g',
        '흑백', '단면', '고객의 요청사항입니다.', 'yellow', '',
        1, '', FALSE, FALSE, 1,
        'a4', 1000, 1, 1000, 100,
        12);
INSERT INTO ORDER_ITEM(GENERATED_DATE_TIME, IS_DELETED, IS_IN_CART, ITEM_ID, ITEM_NAME,
                       BINDING_DIRECTION, BINDING_TYPE, BOOK_COUNT, CONTENT_PAGE_COUNT, CONTENT_PAPER_TYPE,
                       CONTENT_PRINTING_COLOR_TYPE, CONTENT_PRINTING_TYPE, COVER_COATING_TYPE, COVER_DESIGN_NAME, COVER_PAPER_TYPE,
                       COVER_PRINTING_COLOR_TYPE, COVER_PRINTING_TYPE, CUSTOMER_COMMENT, FLYLEAF_COLOR_TYPE, FLYLEAF_CONTENT_PRINTING_VALUE,
                       FLYLEAF_COUNT_PER_BOOK,FLYLEAF_INSERT_LOCATION, HAS_FLYLEAF, HAS_FLYLEAF_CONTENT_PRINTING, PAGE_COUNT_PER_BOOK,
                       PAPER_SIZE, ITEM_PRICE, ORDERER_ID, TOTAL_PRICE, VAT,
                       ORDERS_ID)
VALUES (now(), FALSE, FALSE, 1, '인쇄 및 제본',
        '좌철', '무선', 1, 10, '듀오매트-스노우지-백색-200g',
        '흑백', '단면', '무광', '디자인 선택안함', '듀오매트-스노우지-백색-200g',
        '흑백', '단면', '고객의 요청사항입니다.', 'yellow', '',
        1, '', FALSE, FALSE, 1,
        'a4', 1000, 1, 1000, 100,
        13);
INSERT INTO ORDER_ITEM(GENERATED_DATE_TIME, IS_DELETED, IS_IN_CART, ITEM_ID, ITEM_NAME,
                       BINDING_DIRECTION, BINDING_TYPE, BOOK_COUNT, CONTENT_PAGE_COUNT, CONTENT_PAPER_TYPE,
                       CONTENT_PRINTING_COLOR_TYPE, CONTENT_PRINTING_TYPE, COVER_COATING_TYPE, COVER_DESIGN_NAME, COVER_PAPER_TYPE,
                       COVER_PRINTING_COLOR_TYPE, COVER_PRINTING_TYPE, CUSTOMER_COMMENT, FLYLEAF_COLOR_TYPE, FLYLEAF_CONTENT_PRINTING_VALUE,
                       FLYLEAF_COUNT_PER_BOOK,FLYLEAF_INSERT_LOCATION, HAS_FLYLEAF, HAS_FLYLEAF_CONTENT_PRINTING, PAGE_COUNT_PER_BOOK,
                       PAPER_SIZE, ITEM_PRICE, ORDERER_ID, TOTAL_PRICE, VAT,
                       ORDERS_ID)
VALUES (now(), FALSE, FALSE, 1, '인쇄 및 제본',
        '좌철', '무선', 1, 10, '듀오매트-스노우지-백색-200g',
        '흑백', '단면', '무광', '디자인 선택안함', '듀오매트-스노우지-백색-200g',
        '흑백', '단면', '고객의 요청사항입니다.', 'yellow', '',
        1, '', FALSE, FALSE, 1,
        'a4', 1000, 1, 1000, 100,
        14);
INSERT INTO ORDER_ITEM(GENERATED_DATE_TIME, IS_DELETED, IS_IN_CART, ITEM_ID, ITEM_NAME,
                       BINDING_DIRECTION, BINDING_TYPE, BOOK_COUNT, CONTENT_PAGE_COUNT, CONTENT_PAPER_TYPE,
                       CONTENT_PRINTING_COLOR_TYPE, CONTENT_PRINTING_TYPE, COVER_COATING_TYPE, COVER_DESIGN_NAME, COVER_PAPER_TYPE,
                       COVER_PRINTING_COLOR_TYPE, COVER_PRINTING_TYPE, CUSTOMER_COMMENT, FLYLEAF_COLOR_TYPE, FLYLEAF_CONTENT_PRINTING_VALUE,
                       FLYLEAF_COUNT_PER_BOOK,FLYLEAF_INSERT_LOCATION, HAS_FLYLEAF, HAS_FLYLEAF_CONTENT_PRINTING, PAGE_COUNT_PER_BOOK,
                       PAPER_SIZE, ITEM_PRICE, ORDERER_ID, TOTAL_PRICE, VAT,
                       ORDERS_ID)
VALUES (now(), FALSE, FALSE, 1, '인쇄 및 제본',
        '좌철', '무선', 1, 10, '듀오매트-스노우지-백색-200g',
        '흑백', '단면', '무광', '디자인 선택안함', '듀오매트-스노우지-백색-200g',
        '흑백', '단면', '고객의 요청사항입니다.', 'yellow', '',
        1, '', FALSE, FALSE, 1,
        'a4', 1000, 1, 1000, 100,
        15);
INSERT INTO ORDER_ITEM(GENERATED_DATE_TIME, IS_DELETED, IS_IN_CART, ITEM_ID, ITEM_NAME,
                       BINDING_DIRECTION, BINDING_TYPE, BOOK_COUNT, CONTENT_PAGE_COUNT, CONTENT_PAPER_TYPE,
                       CONTENT_PRINTING_COLOR_TYPE, CONTENT_PRINTING_TYPE, COVER_COATING_TYPE, COVER_DESIGN_NAME, COVER_PAPER_TYPE,
                       COVER_PRINTING_COLOR_TYPE, COVER_PRINTING_TYPE, CUSTOMER_COMMENT, FLYLEAF_COLOR_TYPE, FLYLEAF_CONTENT_PRINTING_VALUE,
                       FLYLEAF_COUNT_PER_BOOK,FLYLEAF_INSERT_LOCATION, HAS_FLYLEAF, HAS_FLYLEAF_CONTENT_PRINTING, PAGE_COUNT_PER_BOOK,
                       PAPER_SIZE, ITEM_PRICE, ORDERER_ID, TOTAL_PRICE, VAT,
                       ORDERS_ID)
VALUES (now(), FALSE, FALSE, 1, '인쇄 및 제본',
        '좌철', '무선', 1, 10, '듀오매트-스노우지-백색-200g',
        '흑백', '단면', '무광', '디자인 선택안함', '듀오매트-스노우지-백색-200g',
        '흑백', '단면', '고객의 요청사항입니다.', 'yellow', '',
        1, '', FALSE, FALSE, 1,
        'a4', 1000, 1, 1000, 100,
        16);
INSERT INTO ORDER_ITEM(GENERATED_DATE_TIME, IS_DELETED, IS_IN_CART, ITEM_ID, ITEM_NAME,
                       BINDING_DIRECTION, BINDING_TYPE, BOOK_COUNT, CONTENT_PAGE_COUNT, CONTENT_PAPER_TYPE,
                       CONTENT_PRINTING_COLOR_TYPE, CONTENT_PRINTING_TYPE, COVER_COATING_TYPE, COVER_DESIGN_NAME, COVER_PAPER_TYPE,
                       COVER_PRINTING_COLOR_TYPE, COVER_PRINTING_TYPE, CUSTOMER_COMMENT, FLYLEAF_COLOR_TYPE, FLYLEAF_CONTENT_PRINTING_VALUE,
                       FLYLEAF_COUNT_PER_BOOK,FLYLEAF_INSERT_LOCATION, HAS_FLYLEAF, HAS_FLYLEAF_CONTENT_PRINTING, PAGE_COUNT_PER_BOOK,
                       PAPER_SIZE, ITEM_PRICE, ORDERER_ID, TOTAL_PRICE, VAT,
                       ORDERS_ID)
VALUES (now(), FALSE, FALSE, 1, '인쇄 및 제본',
        '좌철', '무선', 1, 10, '듀오매트-스노우지-백색-200g',
        '흑백', '단면', '무광', '디자인 선택안함', '듀오매트-스노우지-백색-200g',
        '흑백', '단면', '고객의 요청사항입니다.', 'yellow', '',
        1, '', FALSE, FALSE, 1,
        'a4', 1000, 1, 1000, 100,
        17);
INSERT INTO ORDER_ITEM(GENERATED_DATE_TIME, IS_DELETED, IS_IN_CART, ITEM_ID, ITEM_NAME,
                       BINDING_DIRECTION, BINDING_TYPE, BOOK_COUNT, CONTENT_PAGE_COUNT, CONTENT_PAPER_TYPE,
                       CONTENT_PRINTING_COLOR_TYPE, CONTENT_PRINTING_TYPE, COVER_COATING_TYPE, COVER_DESIGN_NAME, COVER_PAPER_TYPE,
                       COVER_PRINTING_COLOR_TYPE, COVER_PRINTING_TYPE, CUSTOMER_COMMENT, FLYLEAF_COLOR_TYPE, FLYLEAF_CONTENT_PRINTING_VALUE,
                       FLYLEAF_COUNT_PER_BOOK,FLYLEAF_INSERT_LOCATION, HAS_FLYLEAF, HAS_FLYLEAF_CONTENT_PRINTING, PAGE_COUNT_PER_BOOK,
                       PAPER_SIZE, ITEM_PRICE, ORDERER_ID, TOTAL_PRICE, VAT,
                       ORDERS_ID)
VALUES (now(), FALSE, FALSE, 1, '인쇄 및 제본',
        '좌철', '무선', 1, 10, '듀오매트-스노우지-백색-200g',
        '흑백', '단면', '무광', '디자인 선택안함', '듀오매트-스노우지-백색-200g',
        '흑백', '단면', '고객의 요청사항입니다.', 'yellow', '',
        1, '', FALSE, FALSE, 1,
        'a4', 1000, 1, 1000, 100,
        18);
INSERT INTO ORDER_ITEM(GENERATED_DATE_TIME, IS_DELETED, IS_IN_CART, ITEM_ID, ITEM_NAME,
                       BINDING_DIRECTION, BINDING_TYPE, BOOK_COUNT, CONTENT_PAGE_COUNT, CONTENT_PAPER_TYPE,
                       CONTENT_PRINTING_COLOR_TYPE, CONTENT_PRINTING_TYPE, COVER_COATING_TYPE, COVER_DESIGN_NAME, COVER_PAPER_TYPE,
                       COVER_PRINTING_COLOR_TYPE, COVER_PRINTING_TYPE, CUSTOMER_COMMENT, FLYLEAF_COLOR_TYPE, FLYLEAF_CONTENT_PRINTING_VALUE,
                       FLYLEAF_COUNT_PER_BOOK,FLYLEAF_INSERT_LOCATION, HAS_FLYLEAF, HAS_FLYLEAF_CONTENT_PRINTING, PAGE_COUNT_PER_BOOK,
                       PAPER_SIZE, ITEM_PRICE, ORDERER_ID, TOTAL_PRICE, VAT,
                       ORDERS_ID)
VALUES (now(), FALSE, FALSE, 1, '인쇄 및 제본',
        '좌철', '무선', 1, 10, '듀오매트-스노우지-백색-200g',
        '흑백', '단면', '무광', '디자인 선택안함', '듀오매트-스노우지-백색-200g',
        '흑백', '단면', '고객의 요청사항입니다.', 'yellow', '',
        1, '', FALSE, FALSE, 1,
        'a4', 1000, 1, 1000, 100,
        19);
INSERT INTO ORDER_ITEM(GENERATED_DATE_TIME, IS_DELETED, IS_IN_CART, ITEM_ID, ITEM_NAME,
                       BINDING_DIRECTION, BINDING_TYPE, BOOK_COUNT, CONTENT_PAGE_COUNT, CONTENT_PAPER_TYPE,
                       CONTENT_PRINTING_COLOR_TYPE, CONTENT_PRINTING_TYPE, COVER_COATING_TYPE, COVER_DESIGN_NAME, COVER_PAPER_TYPE,
                       COVER_PRINTING_COLOR_TYPE, COVER_PRINTING_TYPE, CUSTOMER_COMMENT, FLYLEAF_COLOR_TYPE, FLYLEAF_CONTENT_PRINTING_VALUE,
                       FLYLEAF_COUNT_PER_BOOK,FLYLEAF_INSERT_LOCATION, HAS_FLYLEAF, HAS_FLYLEAF_CONTENT_PRINTING, PAGE_COUNT_PER_BOOK,
                       PAPER_SIZE, ITEM_PRICE, ORDERER_ID, TOTAL_PRICE, VAT,
                       ORDERS_ID)
VALUES (now(), FALSE, FALSE, 1, '인쇄 및 제본',
        '좌철', '무선', 1, 10, '듀오매트-스노우지-백색-200g',
        '흑백', '단면', '무광', '디자인 선택안함', '듀오매트-스노우지-백색-200g',
        '흑백', '단면', '고객의 요청사항입니다.', 'yellow', '',
        1, '', FALSE, FALSE, 1,
        'a4', 1000, 1, 1000, 100,
        20);

-- INSERT INTO ORDER_ITEM (
--     generated_date_time, is_deleted, is_in_cart, item_id, item_name,
--     binding_direction, binding_type, book_count, content_page_count, content_paper_type,
--     content_printing_type, cover_coating_type, cover_design_name, cover_paper_type, cover_printing_color_type,
--     cover_printing_type, flyleaf_color_type, flyleaf_content_printing_value, flyleaf_count_per_book, flyleaf_insert_location, has_flyleaf, has_flyleaf_content_printing, page_count_per_book, paper_size, item_price, orderer_id, total_price, vat, orders_id)
-- VALUES (now(), FALSE, FALSE, 1, '인쇄 및 제본', '좌철', '무선', 1, 1, '듀오매트-백색-200g', '단면', '무광', '디자인 선택안함', '듀오매트-스노우지-백색-200g', '흑백', '단면', 'yellow', '', 1, '', FALSE, FALSE, 1, 'a4', 1000, 1, 1100, 100, 1);

/* dummy 공지사항 게시글 */
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (3, 1, '공지사항01 입니다', '<h1>공지사항 게시글 01 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (3, 1, '공지사항02 입니다', '<h1>공지사항 게시글 02 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (3, 1, '공지사항03 입니다', '<h1>공지사항 게시글 03 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (3, 1, '공지사항04 입니다', '<h1>공지사항 게시글 04 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (3, 1, '공지사항05 입니다', '<h1>공지사항 게시글 05 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (3, 1, '공지사항06 입니다', '<h1>공지사항 게시글 06 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (3, 1, '공지사항07 입니다', '<h1>공지사항 게시글 07 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (3, 1, '공지사항08 입니다', '<h1>공지사항 게시글 08 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (3, 1, '공지사항09 입니다', '<h1>공지사항 게시글 09 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (3, 1, '공지사항10 입니다', '<h1>공지사항 게시글 10 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (3, 1, '공지사항11 입니다', '<h1>공지사항 게시글 11 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (3, 1, '공지사항12 입니다', '<h1>공지사항 게시글 12 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (3, 1, '공지사항13 입니다', '<h1>공지사항 게시글 13 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (3, 1, '공지사항14 입니다', '<h1>공지사항 게시글 14 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (3, 1, '공지사항15 입니다', '<h1>공지사항 게시글 15 본문</h1>', now(), 'YET');

/* dummy 주문문의 게시글 */
insert into article (board_id, account_id, title, is_notice, content, created_date_time, reply_status) values (1, 1, '공지사항 01', true, '<h1>주문문의 게시글01 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, is_notice, content, created_date_time, reply_status) values (1, 1, '공지사항 02', true, '<h1>주문문의 게시글01 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, is_notice, content, created_date_time, reply_status) values (1, 1, '공지사항 03', true, '<h1>주문문의 게시글01 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 01', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 02', '<h1>주문문의 게시글01 본문</h1>', now(), 'DONE');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 03', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 04', '<h1>주문문의 게시글01 본문</h1>', now(), 'DONE');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 05', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 06', '<h1>주문문의 게시글01 본문</h1>', now(), 'DONE');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 07', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 08', '<h1>주문문의 게시글01 본문</h1>', now(), 'DONE');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 09', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 10', '<h1>주문문의 게시글01 본문</h1>', now(), 'DONE');

insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 11', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 12', '<h1>주문문의 게시글01 본문</h1>', now(), 'DONE');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 13', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 14', '<h1>주문문의 게시글01 본문</h1>', now(), 'DONE');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 15', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 16', '<h1>주문문의 게시글01 본문</h1>', now(), 'DONE');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 17', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 18', '<h1>주문문의 게시글01 본문</h1>', now(), 'DONE');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 19', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 20', '<h1>주문문의 게시글01 본문</h1>', now(), 'DONE');

insert into article (board_id, account_id, title, content, created_date_time, reply_status, deleted) values (1, 2, '주문문의 게시글 21', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET', true);
insert into article (board_id, account_id, title, content, created_date_time, reply_status, deleted) values (1, 2, '주문문의 게시글 22', '<h1>주문문의 게시글01 본문</h1>', now(), 'DONE', false);
insert into article (board_id, account_id, title, content, created_date_time, reply_status, deleted) values (1, 2, '주문문의 게시글 23', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET', true);
insert into article (board_id, account_id, title, content, created_date_time, reply_status, deleted) values (1, 2, '주문문의 게시글 24', '<h1>주문문의 게시글01 본문</h1>', now(), 'DONE', false);
insert into article (board_id, account_id, title, content, created_date_time, reply_status, deleted) values (1, 2, '주문문의 게시글 25', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET', true);

insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 01', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 02', '<h1>주문문의 게시글01 본문</h1>', now(), 'DONE');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 03', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 04', '<h1>주문문의 게시글01 본문</h1>', now(), 'DONE');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 05', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 06', '<h1>주문문의 게시글01 본문</h1>', now(), 'DONE');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 07', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 08', '<h1>주문문의 게시글01 본문</h1>', now(), 'DONE');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 09', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 10', '<h1>주문문의 게시글01 본문</h1>', now(), 'DONE');

insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 11', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 12', '<h1>주문문의 게시글01 본문</h1>', now(), 'DONE');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 13', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 14', '<h1>주문문의 게시글01 본문</h1>', now(), 'DONE');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 15', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 16', '<h1>주문문의 게시글01 본문</h1>', now(), 'DONE');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 17', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 18', '<h1>주문문의 게시글01 본문</h1>', now(), 'DONE');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 19', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 20', '<h1>주문문의 게시글01 본문</h1>', now(), 'DONE');

insert into article (board_id, account_id, title, content, created_date_time, reply_status, deleted) values (1, 2, '주문문의 게시글 21', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET', true);
insert into article (board_id, account_id, title, content, created_date_time, reply_status, deleted) values (1, 2, '주문문의 게시글 22', '<h1>주문문의 게시글01 본문</h1>', now(), 'DONE', false);
insert into article (board_id, account_id, title, content, created_date_time, reply_status, deleted) values (1, 2, '주문문의 게시글 23', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET', true);
insert into article (board_id, account_id, title, content, created_date_time, reply_status, deleted) values (1, 2, '주문문의 게시글 24', '<h1>주문문의 게시글01 본문</h1>', now(), 'DONE', false);
insert into article (board_id, account_id, title, content, created_date_time, reply_status, deleted) values (1, 2, '주문문의 게시글 25', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET', true);

insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 01', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 02', '<h1>주문문의 게시글01 본문</h1>', now(), 'DONE');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 03', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 04', '<h1>주문문의 게시글01 본문</h1>', now(), 'DONE');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 05', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 06', '<h1>주문문의 게시글01 본문</h1>', now(), 'DONE');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 07', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 08', '<h1>주문문의 게시글01 본문</h1>', now(), 'DONE');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 09', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 10', '<h1>주문문의 게시글01 본문</h1>', now(), 'DONE');

insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 11', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 12', '<h1>주문문의 게시글01 본문</h1>', now(), 'DONE');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 13', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 14', '<h1>주문문의 게시글01 본문</h1>', now(), 'DONE');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 15', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 16', '<h1>주문문의 게시글01 본문</h1>', now(), 'DONE');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 17', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 18', '<h1>주문문의 게시글01 본문</h1>', now(), 'DONE');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 19', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 20', '<h1>주문문의 게시글01 본문</h1>', now(), 'DONE');

insert into article (board_id, account_id, title, content, created_date_time, reply_status, deleted) values (1, 2, '주문문의 게시글 21', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET', true);
insert into article (board_id, account_id, title, content, created_date_time, reply_status, deleted) values (1, 2, '주문문의 게시글 22', '<h1>주문문의 게시글01 본문</h1>', now(), 'DONE', false);
insert into article (board_id, account_id, title, content, created_date_time, reply_status, deleted) values (1, 2, '주문문의 게시글 23', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET', true);
insert into article (board_id, account_id, title, content, created_date_time, reply_status, deleted) values (1, 2, '주문문의 게시글 24', '<h1>주문문의 게시글01 본문</h1>', now(), 'DONE', false);
insert into article (board_id, account_id, title, content, created_date_time, reply_status, deleted) values (1, 2, '주문문의 게시글 25', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET', true);

insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 01', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 02', '<h1>주문문의 게시글01 본문</h1>', now(), 'DONE');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 03', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 04', '<h1>주문문의 게시글01 본문</h1>', now(), 'DONE');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 05', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 06', '<h1>주문문의 게시글01 본문</h1>', now(), 'DONE');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 07', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 08', '<h1>주문문의 게시글01 본문</h1>', now(), 'DONE');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 09', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 10', '<h1>주문문의 게시글01 본문</h1>', now(), 'DONE');

insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 11', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 12', '<h1>주문문의 게시글01 본문</h1>', now(), 'DONE');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 13', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 14', '<h1>주문문의 게시글01 본문</h1>', now(), 'DONE');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 15', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 16', '<h1>주문문의 게시글01 본문</h1>', now(), 'DONE');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 17', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 18', '<h1>주문문의 게시글01 본문</h1>', now(), 'DONE');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 19', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 20', '<h1>주문문의 게시글01 본문</h1>', now(), 'DONE');

insert into article (board_id, account_id, title, content, created_date_time, reply_status, deleted) values (1, 2, '주문문의 게시글 21', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET', true);
insert into article (board_id, account_id, title, content, created_date_time, reply_status, deleted) values (1, 2, '주문문의 게시글 22', '<h1>주문문의 게시글01 본문</h1>', now(), 'DONE', false);
insert into article (board_id, account_id, title, content, created_date_time, reply_status, deleted) values (1, 2, '주문문의 게시글 23', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET', true);
insert into article (board_id, account_id, title, content, created_date_time, reply_status, deleted) values (1, 2, '주문문의 게시글 24', '<h1>주문문의 게시글01 본문</h1>', now(), 'DONE', false);
insert into article (board_id, account_id, title, content, created_date_time, reply_status, deleted) values (1, 2, '주문문의 게시글 25', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET', true);

insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 01', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 02', '<h1>주문문의 게시글01 본문</h1>', now(), 'DONE');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 03', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 04', '<h1>주문문의 게시글01 본문</h1>', now(), 'DONE');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 05', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 06', '<h1>주문문의 게시글01 본문</h1>', now(), 'DONE');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 07', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 08', '<h1>주문문의 게시글01 본문</h1>', now(), 'DONE');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 09', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 10', '<h1>주문문의 게시글01 본문</h1>', now(), 'DONE');

insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 11', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 12', '<h1>주문문의 게시글01 본문</h1>', now(), 'DONE');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 13', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 14', '<h1>주문문의 게시글01 본문</h1>', now(), 'DONE');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 15', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 16', '<h1>주문문의 게시글01 본문</h1>', now(), 'DONE');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 17', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 18', '<h1>주문문의 게시글01 본문</h1>', now(), 'DONE');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 19', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET');
insert into article (board_id, account_id, title, content, created_date_time, reply_status) values (1, 2, '주문문의 게시글 20', '<h1>주문문의 게시글01 본문</h1>', now(), 'DONE');

insert into article (board_id, account_id, title, content, created_date_time, reply_status, deleted) values (1, 2, '주문문의 게시글 21', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET', true);
insert into article (board_id, account_id, title, content, created_date_time, reply_status, deleted) values (1, 2, '주문문의 게시글 22', '<h1>주문문의 게시글01 본문</h1>', now(), 'DONE', false);
insert into article (board_id, account_id, title, content, created_date_time, reply_status, deleted) values (1, 2, '주문문의 게시글 23', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET', true);
insert into article (board_id, account_id, title, content, created_date_time, reply_status, deleted) values (1, 2, '주문문의 게시글 24', '<h1>주문문의 게시글01 본문</h1>', now(), 'DONE', false);
insert into article (board_id, account_id, title, content, created_date_time, reply_status, deleted) values (1, 2, '주문문의 게시글 25', '<h1>주문문의 게시글01 본문</h1>', now(), 'YET', true);

insert into comment (article_id, account_id, content) values (124, 1, '<h1>comment</h1>');

/* Cover Sample Articles */
insert into article (board_id, account_id, thumbnail, title, content, created_date_time, deleted) values (2, 1, '\images\common\no.image.jpg', '커버 샘플 01', '<h1>표지샘플 게시글01 본문</h1>', now(), false);
insert into article (board_id, account_id, thumbnail, title, content, created_date_time, deleted) values (2, 1, '\images\common\no.image.jpg', '커버 샘플 02', '<h1>표지샘플 게시글02 본문</h1>', now(), false);
insert into article (board_id, account_id, thumbnail, title, content, created_date_time, deleted) values (2, 1, '\images\common\no.image.jpg', '커버 샘플 03', '<h1>표지샘플 게시글03 본문</h1>', now(), false);
insert into article (board_id, account_id, thumbnail, title, content, created_date_time, deleted) values (2, 1, '\images\common\no.image.jpg', '커버 샘플 04', '<h1>표지샘플 게시글04 본문</h1>', now(), false);
insert into article (board_id, account_id, thumbnail, title, content, created_date_time, deleted) values (2, 1, '\images\common\no.image.jpg', '커버 샘플 05', '<h1>표지샘플 게시글05 본문</h1>', now(), false);
insert into article (board_id, account_id, thumbnail, title, content, created_date_time, deleted) values (2, 1, '\images\common\no.image.jpg', '커버 샘플 06', '<h1>표지샘플 게시글06 본문</h1>', now(), false);
insert into article (board_id, account_id, thumbnail, title, content, created_date_time, deleted) values (2, 1, '\images\common\no.image.jpg', '커버 샘플 07', '<h1>표지샘플 게시글07 본문</h1>', now(), false);
insert into article (board_id, account_id, thumbnail, title, content, created_date_time, deleted) values (2, 1, '\images\common\no.image.jpg', '커버 샘플 08', '<h1>표지샘플 게시글08 본문</h1>', now(), false);
insert into article (board_id, account_id, thumbnail, title, content, created_date_time, deleted) values (2, 1, '\images\common\no.image.jpg', '커버 샘플 09', '<h1>표지샘플 게시글09 본문</h1>', now(), false);
insert into article (board_id, account_id, thumbnail, title, content, created_date_time, deleted) values (2, 1, '\images\common\no.image.jpg', '커버 샘플 10', '<h1>표지샘플 게시글10 본문</h1>', now(), false);
insert into article (board_id, account_id, thumbnail, title, content, created_date_time, deleted) values (2, 1, '\images\common\no.image.jpg', '커버 샘플 11', '<h1>표지샘플 게시글11 본문</h1>', now(), false);
insert into article (board_id, account_id, thumbnail, title, content, created_date_time, deleted) values (2, 1, '\images\common\no.image.jpg', '커버 샘플 12', '<h1>표지샘플 게시글12 본문</h1>', now(), false);
insert into article (board_id, account_id, thumbnail, title, content, created_date_time, deleted) values (2, 1, '\images\common\no.image.jpg', '커버 샘플 13', '<h1>표지샘플 게시글13 본문</h1>', now(), false);
insert into article (board_id, account_id, thumbnail, title, content, created_date_time, deleted) values (2, 1, '\images\common\no.image.jpg', '커버 샘플 14', '<h1>표지샘플 게시글14 본문</h1>', now(), false);
insert into article (board_id, account_id, thumbnail, title, content, created_date_time, deleted) values (2, 1, '\files\20201220\7992595612793311861\cover-sample-thumbnail2.jpg', '커버 샘플 15', '<h1 style="text-align: center; ">표지샘플 게시글15 본문</h1><p style="text-align: center;"><br></p><p style="text-align: center;"><img src="\files\20201220\7992595612793311861\cover-sample-thumbnail2.jpg" style="width: 198px;" data-filename="image"><br></p>', now(), false);
insert into article (board_id, account_id, thumbnail, title, content, created_date_time, deleted) values (2, 1, '\files\20201220\7992595612793311861\cover-sample-thumbnail.jpg', '커버 샘플 16', '<h1 style="text-align: center; ">표지샘플 게시글16 본문</h1><p style="text-align: center;"><br></p><p style="text-align: center;"><img src="\files\20201220\7992595612793311861\cover-sample-thumbnail.jpg" style="width: 198px;" data-filename="image"><br></p>', now(), false);
insert into article (board_id, account_id, thumbnail, title, content, created_date_time, deleted) values (2, 1, '\files\20201220\7992595612793311861\cover-sample-thumbnail2.jpg', '커버 샘플 17', '<h1 style="text-align: center; ">표지샘플 게시글17 본문</h1><p style="text-align: center;"><br></p><p style="text-align: center;"><img src="\files\20201220\7992595612793311861\cover-sample-thumbnail2.jpg" style="width: 198px;" data-filename="image"><br></p>', now(), false);
insert into article (board_id, account_id, thumbnail, title, content, created_date_time, deleted) values (2, 1, '\files\20201220\7992595612793311861\cover-sample-thumbnail.jpg', '커버 샘플 18', '<h1 style="text-align: center; ">표지샘플 게시글18 본문</h1><p style="text-align: center;"><br></p><p style="text-align: center;"><img src="\files\20201220\7992595612793311861\cover-sample-thumbnail.jpg" style="width: 198px;" data-filename="image"><br></p>', now(), false);

/* 테스트를 위한 더미 데이터 끝 */