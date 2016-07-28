DROP DATABASE IF EXISTS politicmap;

CREATE DATABASE politicmap DEFAULT CHARACTER SET 'utf8';

USE politicmap;

create table materuk
(
	materukId integer AUTO_INCREMENT not null,
	nazva char(50) not null,
	ploshcha decimal(8, 2) null,
	k_naselennja integer  null,
	k_krain integer  null,
	opus char(100)  null,
 	primary key (materukId)
);

create table krainu
(
	krainuId integer AUTO_INCREMENT not null,
	nazva char(100) not null,
	ystriu integer null,
	k_nasel integer null,
	ploshcha decimal(6, 2) null,
	opus char(200) not null,
	rozvutok integer null,
	vvp decimal(6, 2) null,
	materukkrainuId integer null,
	primary key (krainuId)
);

alter table krainu
	add constraint FK_MATERUK_KRAINU
	foreign key (materukkrainuId)
	references materuk(materukId);

