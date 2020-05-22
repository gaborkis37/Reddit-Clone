insert into user(id,alias,email,enabled,first_name, last_name,password) values (1,'testAlias','testemail',1,'testFirstName', 'testLastName','testPass');
insert into user(id,alias,email,enabled,first_name, last_name,password) values (2,'testAlias2','testemail2',1,'testFirstName2', 'testLastName2','testPass');


insert into link (id,created_by, last_modified_by,title, url, vote_count, user_id) values(1000,'test@test.com','test@test.com','testTitle','testUrl1',1,1);
insert into link (id,created_by, last_modified_by,title, url, vote_count, user_id) values(1001,'test@test.com','test@test.com','testTitle1','testUrl2',2,1);
insert into link (id,created_by, last_modified_by,title, url, vote_count, user_id) values(1002,'test1@test.com','test1@test.com','testTitle2','testUrl3',3,2);
insert into link (id,created_by, last_modified_by,title, url, vote_count, user_id) values(1003,'test1@test.com','test1@test.com','testTitle3','testUrl4',4,2);
insert into link (id,created_by, last_modified_by,title, url, vote_count, user_id) values(1004,'test1@test.com','test1@test.com','testTitle4','testUrl5',5,2);

insert into comment(id,created_by,last_modified_by,body,creators_alias,link_id) values(100,'testCreator','testCreator','testBody','alias',1000);
insert into comment(id,created_by,last_modified_by,body,creators_alias,link_id) values(101,'testCreator','testCreator','testBody','alias',1000);
insert into comment(id,created_by,last_modified_by,body,creators_alias,link_id) values(102,'testCreator','testCreator','testBody','alias',1000);
insert into comment(id,created_by,last_modified_by,body,creators_alias,link_id) values(103,'testCreator2','testCreator2','testBody','alias2',1000);