insert into genre( id, name) values( 1, 'Проза');
insert into genre( id, name) values( 2, 'Фантастика');

insert into authors( id, name, birthdate, email, phone, address) values(1, 'king', '1980-06-12', 'king@mail.ru', '11111111', 'no info');
insert into authors( id, name, birthdate, email, phone, address) values(2, 'kong', '1990-12-12', 'kong@mail.ru', '33333333', 'address');

insert into books( id, name, ISBN, PUB_PLACE, PUB_HOUSE, PUB_YEAR, genre_id, author_id) values( 1, 'book 1', '1111111', 'Moscow', 'Астор',  2018, 1, 1);
insert into books( id, name, ISBN, PUB_PLACE, PUB_HOUSE, PUB_YEAR, genre_id, author_id) values( 2, 'book 2', '2222222', 'SPB',    'Кастор', 2017,2, 1);
insert into books( id,  name,    ISBN,      PUB_PLACE, PUB_HOUSE, PUB_YEAR, genre_id, author_id)
            values( 3, 'book 3', '3333333', 'USA',    'Мастор',   2016,     2,        2);

insert into comments( id, name, datetime, comment, book_id) values( 1, 'test_user', '2019-01-01 23:59:59', 'test comment', 1);
