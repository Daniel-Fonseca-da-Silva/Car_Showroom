create table client_roles (
	client_id int8 not null,
	role_id int8 not null
);
    
alter table client_roles 
	add constraint FKdt5dk0ejk90kei1x80i5h8jx7 
	foreign key (role_id) 
	references role
    
alter table client_roles 
	add constraint FKhcuwm327ncan0ue5b65v578cl 
	foreign key (client_id) 
	references client
