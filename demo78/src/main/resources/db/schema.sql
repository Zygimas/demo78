USE goods;

CREATE TABLE IF NOT EXISTS users (
                                     id                 INT(10)      NOT NULL AUTO_INCREMENT,
                                     first_name         VARCHAR(50)  NOT NULL,
                                     last_name          VARCHAR(50)  NOT NULL,
                                     username           VARCHAR(50)  NOT NULL,
                                     encrypted_password VARCHAR(255) NOT NULL,
                                     role               VARCHAR(10),
                                     active             BIT(1)       NOT NULL DEFAULT b'0',
                                     PRIMARY KEY (id),
                                     UNIQUE (username)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = 'utf8'
    COLLATE = 'utf8_general_ci';

CREATE TABLE IF NOT EXISTS notes (
                                     id       INT(8)      NOT NULL AUTO_INCREMENT,
                                     message  VARCHAR(100) NOT NULL,
                                     date     TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                     done     BIT(1)       NOT NULL DEFAULT b'0',
                                     user_id  INT(10)       NOT NULL,
                                     username VARCHAR(50)  NOT NULL,
                                     PRIMARY KEY (id),
                                     FOREIGN KEY (user_id) REFERENCES users(id)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = 'utf8'
    COLLATE = 'utf8_general_ci';

CREATE TABLE IF NOT EXISTS usersLogin (
                                          username           VARCHAR(50) NOT NULL,
                                          encrypted_password VARCHAR(255) NOT NULL,
                                          PRIMARY KEY (username)
);

