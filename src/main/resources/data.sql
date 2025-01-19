-- 초기 사용자 권한 넣는 방법
INSERT INTO user_roles (role) VALUES ('USER');
INSERT INTO user_roles (role) VALUES ('ADMIN');

-- 상품 테스트 데이터 20개
INSERT INTO product (name, is_popular, card_discount, phone_discount, home_page, cs_center, description, publisher, note, product_index, created_at, updated_at)
VALUES
    ('Product 1', true, 10, 5, 'http://homepage1.com', 'Center1', 'Description for product 1', 'Publisher1', 'Note1', 1, NOW(), NOW()),
    ('Product 2', false, 15, 7, 'http://homepage2.com', 'Center2', 'Description for product 2', 'Publisher2', 'Note2', 2, NOW(), NOW()),
    ('Product 3', true, 20, 10, 'http://homepage3.com', 'Center3', 'Description for product 3', 'Publisher3', 'Note3', 3, NOW(), NOW()),
    ('Product 4', false, 5, 3, 'http://homepage4.com', 'Center4', 'Description for product 4', 'Publisher4', 'Note4', 4, NOW(), NOW()),
    ('Product 5', true, 25, 12, 'http://homepage5.com', 'Center5', 'Description for product 5', 'Publisher5', 'Note5', 5, NOW(), NOW()),
    ('Product 6', false, 30, 15, 'http://homepage6.com', 'Center6', 'Description for product 6', 'Publisher6', 'Note6', 6, NOW(), NOW()),
    ('Product 7', true, 35, 20, 'http://homepage7.com', 'Center7', 'Description for product 7', 'Publisher7', 'Note7', 7, NOW(), NOW()),
    ('Product 8', false, 40, 25, 'http://homepage8.com', 'Center8', 'Description for product 8', 'Publisher8', 'Note8', 8, NOW(), NOW()),
    ('Product 9', true, 10, 5, 'http://homepage9.com', 'Center9', 'Description for product 9', 'Publisher9', 'Note9', 9, NOW(), NOW()),
    ('Product 10', false, 15, 7, 'http://homepage10.com', 'Center10', 'Description for product 10', 'Publisher10', 'Note10', 10, NOW(), NOW()),
    ('Product 11', true, 20, 10, 'http://homepage11.com', 'Center11', 'Description for product 11', 'Publisher11', 'Note11', 11, NOW(), NOW()),
    ('Product 12', false, 5, 3, 'http://homepage12.com', 'Center12', 'Description for product 12', 'Publisher12', 'Note12', 12, NOW(), NOW()),
    ('Product 13', true, 25, 12, 'http://homepage13.com', 'Center13', 'Description for product 13', 'Publisher13', 'Note13', 13, NOW(), NOW()),
    ('Product 14', false, 30, 15, 'http://homepage14.com', 'Center14', 'Description for product 14', 'Publisher14', 'Note14', 14, NOW(), NOW()),
    ('Product 15', true, 35, 20, 'http://homepage15.com', 'Center15', 'Description for product 15', 'Publisher15', 'Note15', 15, NOW(), NOW()),
    ('Product 16', false, 40, 25, 'http://homepage16.com', 'Center16', 'Description for product 16', 'Publisher16', 'Note16', 16, NOW(), NOW()),
    ('Product 17', true, 10, 5, 'http://homepage17.com', 'Center17', 'Description for product 17', 'Publisher17', 'Note17', 17, NOW(), NOW()),
    ('Product 18', false, 15, 7, 'http://homepage18.com', 'Center18', 'Description for product 18', 'Publisher18', 'Note18', 18, NOW(), NOW()),
    ('Product 19', true, 20, 10, 'http://homepage19.com', 'Center19', 'Description for product 19', 'Publisher19', 'Note19', 19, NOW(), NOW()),
    ('Product 20', false, 5, 3, 'http://homepage20.com', 'Center20', 'Description for product 20', 'Publisher20', 'Note20', 20, NOW(), NOW());
