CREATE TABLE IF NOT NEISIS tasklist(
    id VARCHAR(8) PRIMARY KEY,
    task VARCHAR(256),
    deadline VARCHAR(10),
    done BOOLEAN
);