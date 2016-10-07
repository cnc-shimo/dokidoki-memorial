CREATE TABLE users (
  id            SERIAL PRIMARY KEY, /* 識別番号 */
  name          TEXT NOT NULL,      /* 名前　　 */
  sex           INTEGER NOT NULL,   /* 性別　　 */
  birthday      TIMESTAMP,          /* 誕生日　 */
  constellation TEXT,               /* 星座　　 */
  blood         INTEGER,            /* 血液型　 */
  birthplace    TEXT,               /* 出身地　 */
  residence     TEXT,               /* 居住地　 */
  occupation    TEXT,               /* 職業　　 */
  hobby         TEXT,               /* 趣味 　　*/
  created_at    TIMESTAMP NOT NULL, /* 作成日時 */
  updated_at    TIMESTAMP NOT NULL  /* 更新日時 */
);

CREATE TABLE couples (
  id              SERIAL PRIMARY KEY,                   /* 識別番号　 */
  man_id          INTEGER UNIQUE REFERENCES users (id), /* 利用者番号 */
  woman_id        INTEGER UNIQUE REFERENCES users (id), /* 利用者番号 */
  anniversary     TIMESTAMP,                            /* 記念日　　 */
  temporary_token TEXT UNIQUE NOT NULL,                 /* 一時認証　 */
  access_token    TEXT UNIQUE NOT NULL,                 /* 永続認証　 */
  created_at      TIMESTAMP NOT NULL,                   /* 作成日時　 */
  updated_at      TIMESTAMP NOT NULL                    /* 更新日時　 */
);

CREATE TABLE frustrations (
  id            SERIAL PRIMARY KEY,                       /* 識別番号　 */
  user_id       INTEGER NOT NULL REFERENCES users (id),   /* 利用者番号 */
  couple_id     INTEGER NOT NULL REFERENCES couples (id), /* 恋人番号　 */
  title         TEXT NOT NULL,                            /* 不満題名　 */
  message       TEXT NOT NULL,                            /* 不満内容　 */
  value         INTEGER NOT NULL,                         /* 不満度　　 */
  is_read       BOOLEAN NOT NULL,                         /* 既読判定　 */
  is_eliminated BOOLEAN NOT NULL,                         /* 解消判定　 */
  created_at    TIMESTAMP NOT NULL,                       /* 作成日時　 */
  updated_at    TIMESTAMP NOT NULL                        /* 更新日時　 */
);
