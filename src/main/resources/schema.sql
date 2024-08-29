create table if not exists person( 
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    uname VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);