DROP DATABASE IF EXISTS TODOLIST;
CREATE DATABASE TODOLIST;
USE TODOLIST;

CREATE TABLE todo ( 
	id BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	title VARCHAR(255) NOT NULL,
    name VARCHAR(100) NOT NULL,
    sequence INT(1) NOT NULL,
    type VARCHAR(20) DEFAULT 'TODO',
    regdate DATETIME DEFAULT NOW()
);

INSERT INTO todo(title, name, sequence) VALUES('자바스크립트공부하기', '홍길동', 1);
INSERT INTO todo(title, name, sequence) VALUES('리포트 제출하기', '홍길동', 1);

UPDATE todo SET TYPE = 'DOING' WHERE id = 1;
UPDATE todo SET TYPE = 'DONE' where id = 1;

SELECT id, title, name, sequence, type, regdate 
FROM todo 
ORDER BY regdate DESC;

SELECT id, title, name, sequence, type, regdate 
FROM todo 
WHERE type = 'TODO'
ORDER BY regdate DESC;

SELECT * FROM todo;