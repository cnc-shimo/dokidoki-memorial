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

INSERT INTO users (
  name,
  sex,
  birthday,
  constellation,
  blood,
  birthplace,
  residence,
  occupation,
  hobby,
  created_at,
  updated_at
) VALUES (
  'name1',
  1,
  '1990/01/01',
  '山羊座',
  1,
  '広島県',
  '福岡県',
  '大学院生',
  'プログラミング',
  '2016/01/01 00:00:00',
  '2016/01/01 00:00:00'
), (
  'name2',
  1,
  '1994/07/01',
  'かに座',
  2,
  '福岡県',
  '福岡県',
  '大学生',
  '登山',
  '2016/02/01 00:00:00',
  '2016/02/01 00:00:00'
), (
  'name3',
  2,
  '1990/03/01',
  'うお座',
  1,
  '福岡県',
  '福岡県',
  '大学院生',
  'ショッピング',
  '2016/01/01 00:00:00',
  '2016/01/01 00:00:00'
);

INSERT INTO couples (
  man_id,
  woman_id,
  anniversary,
  temporary_token,
  access_token,
  created_at,
  updated_at
) VALUES (
  1,
  3,
  '2016/02/01 00:00:00',
  '123456',
  'qwertyqwertyqwerty',
  '2016/02/01 00:00:00',
  '2016/02/01 00:00:00'
);

INSERT INTO frustrations (
  user_id,
  couple_id,
  title,
  message,
  value,
  is_read,
  is_eliminated,
  created_at,
  updated_at
) VALUES (
  1,
  1,
  '飯が不味い',
  '昨日の晩飯が死ぬほど不味かった。何とかして欲しい。',
  0,
  false,
  false,
  '2016/03/01 00:00:00',
  '2016/03/01 00:00:00'
), (
  2,
  1,
  '服装がダサい',
  'デートの時はもう少しオシャレをして欲しい。',
  0,
  false,
  false,
  '2016/03/10 00:00:00',
  '2016/03/10 00:00:00'
);
