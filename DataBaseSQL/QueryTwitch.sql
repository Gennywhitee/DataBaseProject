#Una selezione ordinata su un attributo di una tabella con condizioni AND e OR; Query1 (Nome Streamer,N_Seguaci)
select *
from Streamer
where (Streamer.Nome like 'T%' or Streamer.Nome like 'E%')and N_Seguaci > 100000;


#Una selezione su due o più tabelle con condizioni; Query2 
select s.Nickname, count(t.titolo) as trasmissioni
from Streamer s, Trasmissioni t
where s.nickname = t.creatore
group by (s.nickName)
order by trasmissioni desc;

#Numero totale di seguaci sulla piattaforma
select sum(s.n_Seguaci) as "numero totale di followers"
from Streamer s;

#Somma di sponsorizzazioni per ogni Azienda
select a.Nome as Azienda ,count(sp.codice_sconto) as Sponsorizzazioni
from Aziende a, Sponsor sp
where a.P_Iva = sp.Azienda
group by (a.P_IVA)
order by Sponsorizzazioni desc;

#Elencare le aziende che sponsorizzano streamer con meno di 400.000 seguaci
select a.P_IVA, a.Nome as Azienda, s.Nickname as Creatore, sp.codice_sconto as "Codice Sconto"
from Aziende a, Streamer s, Sponsor sp
where a.P_IVA = sp.Azienda and s.nickname = sp.creatore and s.n_seguaci < 400000;

#Elencare i videogiochi dove gli spettatori, sono stati più alti
drop view if exists sumSpect;
create view sumSpect as
select v.Nome, sum(t.n_spect) as spettatori
from videogames v, Trasmissioni t
where t.Nome_gioco = v.Nome 
group by (v.nome);

select *
from sumSpect
where spettatori = (select max(spettatori)
								from sumSpect);	

#Elencare tutte gli streamer che non sono sponzorizzati da Acme Inc. 
select distinct s.nickname
from Streamer s, Aziende a,Sponsor sp
where sp.creatore = s.nickname and sp.Azienda = a.P_IVA and s.nickname not in
		(select a.p_iva 
        from Aziende a, Sponsor sp
        where a.P_IVA = sp.Azienda and a.Nome ="Acme Inc.");
        
#Elencare tutti gli streamer che sono sponsorizzate da tutte le aziende
select s.nickname
from Streamer s
where not exists(select *
				from Aziende a
                where not exists(select *
								from Sponsor sp
                                where s.nickname = sp.creatore and a.P_IVA = sp.Azienda));
															