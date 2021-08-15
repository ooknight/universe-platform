--
-- H2, POSTGRESQL
--
DROP TABLE IF EXISTS e_hero;
DROP TABLE IF EXISTS e_role;

-- ======== ======== ======== ========
-- role
-- ======== ======== ======== ========
CREATE TABLE e_role
(
    id       BIGSERIAL NOT NULL,
    name_    VARCHAR   NOT NULL,
    state_   INT       NOT NULL,
    created_ TIMESTAMP NULL,
    updated_ TIMESTAMP NULL,
    CONSTRAINT role_pk PRIMARY KEY (id),
    CONSTRAINT role_uk_name UNIQUE (name_)
);

-- ======== ======== ======== ========
-- hero
-- ======== ======== ======== ========
CREATE TABLE e_hero
(
    id        BIGSERIAL NOT NULL,
    username_ VARCHAR   NOT NULL,
    password_ VARCHAR   NOT NULL,
    role_id   BIGINT    NULL,
    state_    INT       NOT NULL,
    created_  TIMESTAMP NULL,
    updated_  TIMESTAMP NULL,
    CONSTRAINT hero_pk PRIMARY KEY (id),
    CONSTRAINT hero_uk_username UNIQUE (username_),
    CONSTRAINT hero_fk_role FOREIGN KEY (role_id) REFERENCES e_role (id)
);
