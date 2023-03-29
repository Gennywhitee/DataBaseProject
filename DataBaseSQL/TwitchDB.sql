drop database if exists TwitchDB;
create database TwitchDB;
use TwitchDB;

#Streamer(nickname,Nome,#followers)
create table Streamer(
Nickname char(30) primary key,
Nome char(30) not null,
N_Seguaci long not null
);

#Videogame(anno,nome,Genere)
create table Videogames(
Nome char(30),
Anno date,
Genere char(30) not null,
primary key (Nome,Anno) 
);

#Trasmissione (NickStr*, Ora_Inizio, Titolo, Ora_Fine,N_spec,tipo,Nome_Gioco,Nome_Canzone,Nome_Ricetta)
create table Trasmissioni(
Creatore char(30),
Ora_Inizio Datetime,
Titolo char(40),
Ora_fine Datetime,
N_Spect long not null,
Tipo char(20) not null,
Nome_Gioco char(30),
Nome_Canzone char(40),
Nome_Ricetta char(30),
primary key (Creatore,Ora_Inizio,Titolo),
foreign key (Nome_Gioco) references Videogames(Nome) on delete cascade,
foreign key (Creatore) references Streamer(Nickname) on delete cascade
);


#Spettatore (username,Data_Iscr,Nome,Password,E-mail)
create table Spettatori(
Username char(30) primary key,
Data_iscrizione date not null,
Nome char(30)not null,
E_mail char(30) not null
);


#Azienda(P.IVA,Nome,numero)
create table Aziende(
P_IVA char(35) primary key,
Nome char(30) not null
);

#Sposorizzazione(P.IVA,Creatore,Cod_Sconto)
create table Sponsor(
Azienda char(35),
Creatore char(30),
Codice_Sconto char(30) not null,
foreign key (Azienda) references Aziende(P_IVA) on delete cascade,
foreign key (Creatore) references Streamer(Nickname) on delete cascade,
primary key (Azienda, Creatore)
);


#Telefono(Azienda,numero)
create table Telefoni(
Azienda char(35),
Numero int,
foreign key (Azienda) references Aziende(P_IVA) on delete cascade,
primary key (Azienda,Numero)
);

#Eventi(Nome,Edizione)
create table Eventi(
Nome char(30),
Edizione int,
primary key (Nome,Edizione)
);

#Montepremi(Evento,Premio,Posto)
create table Premi(
Somma int,
Posto int,
Evento char(40),
Ed int,
foreign key (Evento,Ed) references Eventi(Nome,Edizione) on delete cascade,
primary key (Somma,Posto,Evento,Ed)
);


insert into Streamer values 
('Ninja', 'Richard Tyler Blevins', 1000000),
('Shroud', 'Michael Grzesiek', 800000),
('Tfue', 'Turner Tenney', 700000),
('DoktorFroid', 'Froid Mayweather', 232551),
('Tumblurr', 'Giammarco Tocco', 1000000),
('Enkk','Enrico Mensa',162985),
('LilLexi', 'Alexia Raye', 200000),
('Lirik', 'Saad Lamjarred', 400000),
('Summit1g', 'Jaryd Lazar', 800000),
('Sodapoppin', 'Chance Morris', 600000),
('TimTheTatman', 'Timothy John Bater', 500000);

insert into Videogames values 
('Fortnite', '2017-07-25','sandbox'),
('Valorant', '2020-04-07','FPS'),
('Minecraft', '2011-11-18','sandbox'),
('Apex Legends', '2019-02-04','FPS'),
('PUBG', '2017-03-23','FPS'),
('League of Legends', '2009-10-27', 'MOBA'),
('God of War: Ragnarok', '2022-11-09', 'MMORPG'),
('Cuphead', '2017-09-29', 'Run and Gun'),
('Overwatch', '2016-05-24','FPS'),
('Divinity: Original Sin 2', '2017-09-14','RPG'),
('The Witcher 3: Wild Hunt', '2015-05-19','RPG'),
('Final Fantasy VII Remake', '2020-04-10','RPG'),
('Call of Duty Black Ops 3', '2015-11-06', 'FPS');

insert into Trasmissioni values #(NickStr*, Ora_Inizio, Titolo, Ora_Fine, N_spec, tipo, Nome_Gioco, Nome_Canzone, Nome_Ricetta)
('Ninja', '2020-01-01 10:00:00', 'SolovsSquad Fortnite', '2020-01-01 12:00:00', 10000, 'Gaming', 'Fortnite', null ,null),
('Ninja', '2020-01-01 10:00:00', 'Minecraft Bedrock', '2020-01-01 12:00:00', 10000, 'Gaming', 'Minecraft', null ,null),
('Shroud', '2020-01-02 10:00:00', 'Squad Valorant', '2020-01-02 12:00:00', 9000,'Gaming','Valorant', null ,null),
('Tfue', '2020-01-03 10:00:00', 'SpeedRun Minecraft', '2020-01-03 12:00:00', 8000,'Gaming','Minecraft', null ,null),
('DoktorFroid', '2020-01-04 10:00:00', 'Food Stream', '2020-01-04 12:00:00', 7000,'Food & Drink',null, null ,'McCheese'),
('TimTheTatman', '2020-01-05 10:00:00', 'Reaction Drake', '2020-01-05 12:00:00', 3000,'Music',null , 'Passionfruit' ,null),
('LilLexi','2022-12-01 12:00:00','KitchenStream','2022-12-01 14:00:00',10000,'Food & Drink',null ,null,"Pinha Colada"),
('Lirik','2022-12-01 18:00:00','TrickShot race','2022-12-01 21:00:00',5000,'Gaming','Call of Duty Black Ops 3',null,null),
('Summit1g','2022-12-01 15:00:00','Platinum The witcher3','2022-12-01 18:00:00',8000,'Gaming','The Witcher 3: Wild Hunt',null,null),
('Sodapoppin','2022-12-01 19:00:00','Kratos mode ON','2022-12-01 22:00:00',7000,'Gaming','God of War: Ragnarok',null,null),
('Tumblurr','2023-01-08 21:13:00','CupHead w/ Moccia', null, 12000,'Gaming','Cuphead',null,null);


insert into Spettatori values 
('SmithJ11', '2020-01-01', 'John Smith', 'JohnSmith@email.com'),
('Mjson2', '2020-01-02', 'Mary Johnson', 'MaryJohnson@email.com'),
('RobbyLi3', '2020-01-03', 'Robert Williams', 'RobertWilliams@email.com'),
('Mich4own', '2020-01-04', 'Michael Brown', 'MichaelBrown@email.com'),
('Davi55', '2020-01-05', 'James Davis', 'JamesDavis@email.com');

insert into Aziende values 
('IT1234567890','Microsoft'),
('IT0987654321','Sony'),
('IT123498765','NordVPN'),
('IT123487654','EA'),
('IT12345678901', 'Acme Inc.'),
('IT23456789012', 'Beta Corp.'),
('IT34567890123', 'Gamma Enterprises'),
('IT45678901234', 'Delta Industries'),
('IT56789012345', 'Epsilon Co.');


insert into Sponsor values
('IT123498765','Enkk','enkkVPN'),
('IT1234567890','Ninja','NinjSoft'),
('IT0987654321','Ninja','NinjSony'),
('IT12345678901','Ninja','NinjAcme'),
('IT23456789012','Ninja','NinjaBeta'),
('IT56789012345','Ninja','NinjaEpsilon'),
('IT123498765','Ninja','NinjaEA'),
('IT34567890123','Ninja','NinjaGamma'),
('IT123487654','Ninja','NinjaVPN'),
('IT45678901234','Ninja','NinjaDelta'),
('IT12345678901','Tfue','TfueAcme'),
('IT56789012345','Tfue','TfueEpsilon'),
('IT12345678901','Tumblurr','TumbluAcme'),
('IT23456789012','Tumblurr','TumbluBeta'),
('IT123487654','Tumblurr','TumbluuEA'),
('IT45678901234', 'DoktorFroid','DocDelta');

insert into Telefoni values 
('IT1234567890', 12345678),
('IT0987654321', 09876543),
('IT123498765', 1234987),
('IT123487654', 1234876),
('IT12345678901', 1234567),
('IT23456789012', 2345678),
('IT34567890123', 3456789),
('IT45678901234', 4567890),
('IT56789012345', 5678901);

insert into Eventi values 
('Fortnite SummerSkirmish', 1),
('ValorantInvitational', 1),
('MinecraftBuildingChallenge', 2),
('MinecraftBuildingChallenge', 3),
('ApexPredator', 4),
('TwitchCon', 5);

insert into Premi values 
(100000, 1, 'Fortnite SummerSkirmish',1),
(19000, 2, 'ValorantInvitational',1),
(3000, 3, 'MinecraftBuildingChallenge',3),
(5000, 2, 'MinecraftBuildingChallenge',3),
(8000, 1, 'MinecraftBuildingChallenge',3),
(4000, 3, 'MinecraftBuildingChallenge',2),
(6000, 2, 'MinecraftBuildingChallenge',2),
(9000, 1, 'MinecraftBuildingChallenge',2),
(10000, 3, 'ApexPredator',4),
(15000, 2, 'ApexPredator',4),
(25000, 1, 'ApexPredator',4);











