create database "dataDB";
use "dataDB";

create table tbData(
	id_data		int not null	primary key		identity(0,1),
	data_name	varchar(32) not null,
	data_date	date not null
)