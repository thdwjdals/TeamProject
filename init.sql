CREATE TABLE user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    userid VARCHAR(255) NOT NULL,
password VARCHAR(255) NOT NULL
);

CREATE TABLE token (
    user_id BIGINT PRIMARY KEY,
    token VARCHAR(1024) NULL,
    FOREIGN KEY (user_id) REFERENCES user (id)
);
-- "authority" 테이블 생성
CREATE TABLE authority (
    authority_name VARCHAR(50) PRIMARY KEY
);

-- "user_authority" 테이블 생성
CREATE TABLE user_authority (
user_id BIGINT,
authority_name VARCHAR(50),
PRIMARY KEY (user_id, authority_name),
FOREIGN KEY (user_id) REFERENCES user (id),
FOREIGN KEY (authority_name) REFERENCES authority (authority_name)
);

CREATE TABLE book_mark (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    start_location VARCHAR(5) NOT NULL,
    layover_location VARCHAR(5),
    end_location VARCHAR(5) NOT NULL,
    type VARCHAR(100) NOT NULL,
    alias VARCHAR(255) NOT NULL,
FOREIGN KEY (user_id) REFERENCES user(id)
);

# -- 데이터 삽입
# -- "user" 테이블
# INSERT INTO user (email, userid, password)VALUES
#     ('user1@naver.com','user1','1234'),
#     ('user2@naver.com','user2','12345'),
#     ('user3@naver.com','user3','123456');
#
#
# -- "authority" 테이블
# INSERT INTO authority (authority_name) VALUES
#     ('USER');
#
# -- "user_authority" 테이블
# INSERT INTO user_authority (user_id, authority_name) VALUES
#     (1, 'ROLE_USER'),
#     (2, 'ROLE_USER'),
#     (3, 'ROLE_USER');
#
#
# INSERT INTO bookmark (user_id, departure_station, arrival_station, time, cost) VALUES
#     (1, '101', '104', '10:00', 2000),
#     (1, '301', '102', '14:30', 1500),
#     (2, '402', '201', '09:45', 1800),
#     (3, '112', '701', '12:15', 2230);

