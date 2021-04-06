select * from account;

/* 미리 생성 되어야 하는 테이블 및 데이터 */
/* 배송정보 - 배송비 */
CREATE TABLE DELIVERY_PROPERTY (
        fee decimal NOT NULL
);
INSERT INTO DELIVERY_PROPERTY (fee) VALUES (2500);

/* 미리 생성되어야 하는 데이터 */
/* 메인 상품 샘플 슬라이드 이미지 */
INSERT INTO PRODUCT_SAMPLE_IMAGE (path, sequence) values ('\images\dummy\product-sample-image01.jpg', 1);
INSERT INTO PRODUCT_SAMPLE_IMAGE (path, sequence) values ('\images\dummy\product-sample-image02.jpg', 2);
INSERT INTO PRODUCT_SAMPLE_IMAGE (path, sequence) values ('\images\dummy\product-sample-image03.jpg', 3);

/* dummy data */
INSERT INTO ACCOUNT (address, detailed_address, zipcode, created_date_time, email, name, password, personal_info_agreement, phone, site_usage_agreement, grade) values ('서울 동작구 동작대로 33길 13-13', '2층', '06997', now(), 'admin@junglim.com', '관리자', '11111111', TRUE, '01012345678', TRUE, 'ADMIN');
INSERT INTO ACCOUNT (address, detailed_address, zipcode, created_date_time, email, name, password, personal_info_agreement, phone, site_usage_agreement, grade) values ('서울 동작구 동작대로 33길 13-13', '2층', '06997', now(), 'normal@junglim.com', '김일반', '11111111', TRUE, '01012345678', TRUE, 'NORMAL');

insert into ONE_PAGE_MENU (title, content) values ('주문방법', '<h1>주문방법</h1>');
insert into ONE_PAGE_MENU (title, content) values ('가격표', '<h1>가격표</h1>');
insert into ONE_PAGE_MENU (title, content) values ('회사소개', '<h1>회사소개</h1>');

/* dummy 주문 아이템(order item) */
-- INSERT INTO ORDER_ITEM (
--     generated_date_time, is_deleted, is_in_cart, item_id, item_name,
--     binding_direction, binding_type, book_count, content_page_count, content_paper_type,
--     content_printing_type, cover_coating_type, cover_design_name, cover_paper_type, cover_printing_color_type,
--     cover_printing_type, flyleaf_color_type, flyleaf_content_printing_value, flyleaf_count_per_book, flyleaf_insert_location, has_flyleaf, has_flyleaf_content_printing, page_count_per_book, paper_size, item_price, orderer_id, total_price, vat, orders_id)
-- VALUES (now(), FALSE, FALSE, 1, '인쇄 및 제본', '좌철', '무선', 1, 1, '듀오매트-백색-200g', '단면', '무광', '디자인 선택안함', '듀오매트-스노우지-백색-200g', '흑백', '단면', 'yellow', '', 1, '', FALSE, FALSE, 1, 'a4', 1000, 1, 1100, 100, 1);

/* dummy 결제(payment) */
-- INSERT INTO PAYMENT (account_holder_name, amount, created_date_time, orderer_email, orderer_name, orderer_phone, payment_method, payment_status)
-- values ('김일반', 3600, now(), 'normal@junglim.com', '김일반', '01012345678', '무통장입금', 'WAITING');

/* dummy 주문(orders) */
-- INSERT INTO ORDERS (all_items_total_price, created_date_time, delivery_fee, delivery_type, message_to_deliver, address, detailed_address, zipcode, receiver_name, receiver_phone, is_ordered, total_price, account_id, payment_id)
-- values (1100, now(), 2500, 'PARCEL', '배송 요청사항', '서울 동작구 동작대로33길 13-13', '2층', '06997', '김보통', '01012345678', TRUE, 3600, 1, 1);


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
insert into article (board_id, account_id, thumbnail, title, content, created_date_time, deleted) values (2, 1, '\uploaded-files\20201220\7992595612793311861\cover-sample-thumbnail2.jpg', '커버 샘플 15', '<h1 style="text-align: center; ">표지샘플 게시글15 본문</h1><p style="text-align: center;"><br></p><p style="text-align: center;"><img src="\uploaded-files\20201220\7992595612793311861\cover-sample-thumbnail2.jpg" style="width: 198px;" data-filename="image"><br></p>', now(), false);
insert into article (board_id, account_id, thumbnail, title, content, created_date_time, deleted) values (2, 1, '\uploaded-files\20201220\7992595612793311861\cover-sample-thumbnail.jpg', '커버 샘플 16', '<h1 style="text-align: center; ">표지샘플 게시글16 본문</h1><p style="text-align: center;"><br></p><p style="text-align: center;"><img src="\uploaded-files\20201220\7992595612793311861\cover-sample-thumbnail.jpg" style="width: 198px;" data-filename="image"><br></p>', now(), false);
insert into article (board_id, account_id, thumbnail, title, content, created_date_time, deleted) values (2, 1, '\uploaded-files\20201220\7992595612793311861\cover-sample-thumbnail2.jpg', '커버 샘플 17', '<h1 style="text-align: center; ">표지샘플 게시글17 본문</h1><p style="text-align: center;"><br></p><p style="text-align: center;"><img src="\uploaded-files\20201220\7992595612793311861\cover-sample-thumbnail2.jpg" style="width: 198px;" data-filename="image"><br></p>', now(), false);
insert into article (board_id, account_id, thumbnail, title, content, created_date_time, deleted) values (2, 1, '\uploaded-files\20201220\7992595612793311861\cover-sample-thumbnail.jpg', '커버 샘플 18', '<h1 style="text-align: center; ">표지샘플 게시글18 본문</h1><p style="text-align: center;"><br></p><p style="text-align: center;"><img src="\uploaded-files\20201220\7992595612793311861\cover-sample-thumbnail.jpg" style="width: 198px;" data-filename="image"><br></p>', now(), false);