DROP TABLE IF EXISTS e_sample;

-- ======== ======== ======== ========
-- sample
-- ======== ======== ======== ========
CREATE TABLE e_sample
(
    id       BIGSERIAL             NOT NULL,
    name_    VARCHAR               NULL,
    value1_  BYTEA                 NULL,
    value2_  BYTEA                 NULL,
    deleted_ BOOLEAN DEFAULT FALSE NOT NULL,
    created_ TIMESTAMP             NULL,
    updated_ TIMESTAMP             NULL,
    CONSTRAINT sample_pk PRIMARY KEY (id)
);

-- ======== ======== ======== ========
-- sample data
-- ======== ======== ======== ========
INSERT INTO e_sample(id, name_, deleted_)
VALUES (101, 'sample1', false),
       (102, 'sample2', false),
       (103, 'sample3', true);
