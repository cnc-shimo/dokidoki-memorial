CREATE TABLE couples (
  id         SERIAL PRIMARY KEY,
  man_id     INTEGER UNIQUE,
  woman_id   INTEGER UNIQUE,
  created_at TIMESTAMP,
  updated_at TIMESTAMP
);
