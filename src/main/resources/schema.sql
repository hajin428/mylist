-- To Do 테이블
create table if not exists mylist.todo
(
    id          bigint auto_increment
    primary key,
    completed   bit          not null,
    description varchar(255) not null,
    due_date    date         not null,
    title       varchar(255) not null
    );

-- answer 테이블
create table if not exists mylist.answer
(
    id         bigint auto_increment
    primary key,
    content    text        not null,
    created_at datetime(6) not null,
    task_key   int         not null,
    todo_id    bigint      not null,
    constraint FKd52tm7dpat5kjt50sme3xf1uj
    foreign key (todo_id) references mylist.todo (id)
    );

-- answer 테이블 인덱싱
create index idx_todo_id
    on mylist.answer (todo_id);

create index idx_todo_id_task_key
    on mylist.answer (todo_id, task_key);

-- To Do 테이블 인덱싱
create index idx_due_date
    on mylist.todo (due_date);

create index idx_title
    on mylist.todo (title);
