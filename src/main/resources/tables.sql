

CREATE TABLE IF NOT EXISTS geo_cache(
    id INTEGER NOT NULL PRIMARY KEY,
    username TEXT NOT NULL,
    note TEXT NOT NULL,
    secret TEXT,
    date TIMESTAMP NOT NULL);


