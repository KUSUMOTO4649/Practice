CREATE TABLE IF NOT EXISTS practice(
    id VARCHAR(8) PRIMARY KEY,
    task VARCHAR(256),
    deadline VARCHAR(10),
    done BOOLEAN
 );
--INSERT INTO practice
    --VALUES('00001','Javaの本の原稿を書く','2021-09-30',FALSE)
