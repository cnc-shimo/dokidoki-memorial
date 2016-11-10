# RESTful API

## [GET] /api/v1/users
### Request
```
ToDo
```

### Response
```
{
    "users": [
        {
            "birthday": "1990-01-01T00:00:00+00:00",
            "birthplace": "広島県",
            "blood": 1,
            "constellation": "山羊座",
            "couple_anniversary": "2016-02-01 00:00:00",
            "couple_created_at": "2016-02-01 00:00:00",
            "couple_id": 1,
            "couple_man_id": 1,
            "couple_updated_at": "2016-02-01 00:00:00",
            "couple_woman_id": 3,
            "created_at": "2016-01-01T00:00:00+00:00",
            "hobby": "プログラミング",
            "id": 1,
            "name": "name1",
            "residence": "福岡県",
            "sex": 1,
            "updated_at": "2016-01-01T00:00:00+00:00"
        },
        {
            "birthday": "1994-07-01T00:00:00+00:00",
            "birthplace": "福岡県",
            "blood": 2,
            "constellation": "かに座",
            "couple_anniversary": null,
            "couple_created_at": null,
            "couple_id": null,
            "couple_man_id": null,
            "couple_updated_at": null,
            "couple_woman_id": null,
            "created_at": "2016-02-01T00:00:00+00:00",
            "hobby": "登山",
            "id": 2,
            "name": "name2",
            "residence": "福岡県",
            "sex": 1,
            "updated_at": "2016-02-01T00:00:00+00:00"
        },
        {
            "birthday": "1990-03-01T00:00:00+00:00",
            "birthplace": "福岡県",
            "blood": 1,
            "constellation": "うお座",
            "couple_anniversary": "2016-02-01 00:00:00",
            "couple_created_at": "2016-02-01 00:00:00",
            "couple_id": 1,
            "couple_man_id": 1,
            "couple_updated_at": "2016-02-01 00:00:00",
            "couple_woman_id": 3,
            "created_at": "2016-01-01T00:00:00+00:00",
            "hobby": "ショッピング",
            "id": 3,
            "name": "name3",
            "residence": "福岡県",
            "sex": 2,
            "updated_at": "2016-01-01T00:00:00+00:00"
        }
    ]
}
```

## [GET] /api/v1/users/:id
### Request
```
ToDo
```

### Response
```
{
    "birthday": "1990-01-01T00:00:00+00:00",
    "birthplace": "広島県",
    "blood": 1,
    "constellation": "山羊座",
    "couple_anniversary": "2016-02-01 00:00:00",
    "couple_created_at": "2016-02-01 00:00:00",
    "couple_id": 1,
    "couple_man_id": 1,
    "couple_updated_at": "2016-02-01 00:00:00",
    "couple_woman_id": 3,
    "created_at": "2016-01-01T00:00:00+00:00",
    "hobby": "プログラミング",
    "id": 1,
    "name": "name1",
    "residence": "福岡県",
    "sex": 1,
    "updated_at": "2016-01-01T00:00:00+00:00"
}
```

## [GET] /api/v1/users/:user_id/frustrations
### Request
```
ToDo
```

### Response
```
{
    "frustrations": [
        {
            "couple_id": 1,
            "created_at": "2016-03-01T00:00:00+00:00",
            "id": 1,
            "is_eliminated": false,
            "is_read": false,
            "message": "昨日の晩飯が死ぬほど不味かった。何とかして欲しい。",
            "title": "飯が不味い",
            "updated_at": "2016-03-01T00:00:00+00:00",
            "user_id": 1,
            "value": 70
        }
    ]
}
```

## [GET] /api/v1/users/:user_id/frustrations/:id
### Request
```
ToDo
```

### Response
```
{
    "couple_id": 1,
    "created_at": "2016-03-01T00:00:00+00:00",
    "id": 1,
    "is_eliminated": false,
    "is_read": false,
    "message": "昨日の晩飯が死ぬほど不味かった。何とかして欲しい。",
    "title": "飯が不味い",
    "updated_at": "2016-03-01T00:00:00+00:00",
    "user_id": 1,
    "value": 70
}
```

## [GET] /api/v1/couples
### Request
```
ToDo
```

### Response
```
{
    "couples": [
        {
            "anniversary": "2016-02-01T00:00:00+00:00",
            "created_at": "2016-02-01T00:00:00+00:00",
            "id": 1,
            "man_id": 1,
            "updated_at": "2016-02-01T00:00:00+00:00",
            "woman_id": 3
        }
    ]
}
```

## [GET] /api/v1/couples/:id
### Request
```
ToDo
```

### Response
```
{
    "anniversary": "2016-02-01T00:00:00+00:00",
    "created_at": "2016-02-01T00:00:00+00:00",
    "id": 1,
    "man_id": 1,
    "updated_at": "2016-02-01T00:00:00+00:00",
    "woman_id": 3
}
```

## [POST] /api/v1/users/:user_id/frustrations
### Request
```
{
    "frustrations": [
        {
            "title": "髪型がダサい",
            "message": "80年代のアイドルかと思った。",
            "value": 5
        }
    ]
}
```

### Response
```
{
    "frustrations": [
        {
            "couple_id": 1,
            "created_at": "2016-03-01T00:00:00+00:00",
            "id": 1,
            "is_eliminated": false,
            "is_read": false,
            "message": "昨日の晩飯が死ぬほど不味かった。何とかして欲しい。",
            "title": "飯が不味い",
            "updated_at": "2016-03-01T00:00:00+00:00",
            "user_id": 1,
            "value": 70
        },
        {
            "couple_id": 1,
            "created_at": "2000-01-01T00:00:00+00:00",
            "id": 3,
            "is_eliminated": false,
            "is_read": false,
            "message": "80年代のアイドルかと思った。",
            "title": "髪型がダサい",
            "updated_at": "2000-01-01T00:00:00+00:00",
            "user_id": 1,
            "value": 5
        }
    ]
}
```
