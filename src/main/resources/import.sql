CREATE TABLE taste (
                       taste_id SERIAL PRIMARY KEY,
                       taste_name VARCHAR(255) NOT NULL
);

INSERT INTO taste (taste_name)
VALUES
    ('페미닌'),
    ('모던'),
    ('드뮤어'),
    ('올드머니'),
    ('힙'),
    ('캐주얼'),
    ('고프코어'),
    ('Y2K');
