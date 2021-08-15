insert into e_role(id, name_, state_)
values (1, '皇帝', 1),
       (2, '文官', 1),
       (3, '武官', 1);

insert into e_hero(id, username_, password_, role_id, state_)
values (1, '刘备', '123456', 1, 1),
       (2, '简雍', '123456', 2, 1),
       (3, '关羽', '123456', 3, 1);
