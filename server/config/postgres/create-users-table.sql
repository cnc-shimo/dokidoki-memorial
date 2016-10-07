CREATE TABLE users (
  id            SERIAL PRIMARY KEY, /* 識別番号 */
  name          TEXT NOT NULL,      /* 名前　　 */
  sex           INTEGER NOT NULL,   /* 性別　　 */
  birthday      DATE,               /* 誕生日　 */
  constellation TEXT,               /* 星座　　 */
  blood         INTEGER,            /* 血液型　 */
  birthplace    TEXT,               /* 出身地　 */
  residence     TEXT,               /* 居住地　 */
  occupation    TEXT,               /* 職業　　 */
  hobby         TEXT,               /* 趣味 　　*/
  created_at    TIMESTAMP,          /* 作成日時 */
  updated_at    TIMESTAMP           /* 更新日時 */
);
