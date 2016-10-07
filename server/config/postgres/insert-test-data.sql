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
  temporary_token,
  access_token,
  created_at,
  updated_at
) VALUES (
  1,
  3,
  '123456',
  'qwertyqwertyqwerty',
  '2016/01/01 00:00:00',
  '2016/01/01 00:00:00'
);
