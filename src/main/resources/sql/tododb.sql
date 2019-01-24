CREATE DATABASE todo_db DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE TABLE todo (
	id BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT, 
	title VARCHAR(255) NOT NULL,
	name VARCHAR(100) NOT NULL,
	sequence INT(1) NOT NULL,
	type VARCHAR(20) DEFAULT 'TODO',
	regdate DATETIME DEFAULT NOW(),
	PRIMARY KEY (id) 
)DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

INSERT INTO todo(title, name, sequence) VALUES('자바스크립트공부하기', '홍길동', 1);
INSERT INTO todo(title, name, sequence) VALUES('리포트 제출하기', '홍길동', 2);
INSERT INTO todo(title, name, sequence) VALUES('리포트 제출하기2', '홍길동', 3);

UPDATE todo SET TYPE = 'DOING' WHERE id = 1;

SELECT id, title, name, sequence, type, regdate 
FROM todo
ORDER BY sequence DESC regdate DESC;

DELETE FROM todo
WHERE id= 2;

