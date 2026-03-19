
    set client_min_messages = WARNING;

    alter table if exists animali 
       drop constraint if exists FKh4772d29qhfyksnsc9ib9dise;

    alter table if exists animali 
       drop constraint if exists FKsyr7mxgy0xkl1c4ufoidgcj8o;

    alter table if exists area 
       drop constraint if exists FK58cx5f7827h15xey6pta359vm;

    alter table if exists area 
       drop constraint if exists FKqyufmy0nn4gryp7ybjr1a9qp7;

    alter table if exists biglietti 
       drop constraint if exists FKa9qpqe8ph6vi4s58ld1v8xy49;

    alter table if exists biglietti 
       drop constraint if exists FK1bmp98ht8pfncacgxhy37umth;

    alter table if exists clienti 
       drop constraint if exists FKt9b2l6259oklgl24liwqr269o;

    alter table if exists delfini 
       drop constraint if exists FKaikh4o556oshv8bimioios4vw;

    alter table if exists dipendenti 
       drop constraint if exists FKf92w72v7612ypcdf2oon93ggy;

    alter table if exists dipendenti 
       drop constraint if exists FKiw1ncoi6fndn5apdn3ygnksq9;

    alter table if exists giornata_zoo 
       drop constraint if exists FK2hc1cgyewdkxx4ww4ll3aj176;

    alter table if exists giornata_zoo 
       drop constraint if exists FKjomjxpcfflmckds7alfucrx58;

    alter table if exists lemuri 
       drop constraint if exists FKql8qdh6ba9uka6ms1uq9uwgp6;

    alter table if exists leoni 
       drop constraint if exists FKc71c0urjkly4y272ptk8q9f82;

    alter table if exists mangime 
       drop constraint if exists FKi6eamu3q1o78675ksaa1pwrp3;

    alter table if exists movimenti_mangime 
       drop constraint if exists FKoyn1cvbkee7ly31owr4gkyoj7;

    alter table if exists movimenti_mangime 
       drop constraint if exists FK3cees6y41clh5wd01kuym8vkp;

    alter table if exists oggetti_ordine 
       drop constraint if exists FKr51g644e9d6no9q59hhrqkf26;

    alter table if exists oggetti_ordine 
       drop constraint if exists FKmfa4q2yt6eb8fl8bddfoj6cp5;

    alter table if exists oggetti_ordine 
       drop constraint if exists FK36gjgowybkg1iwr87vwt6co96;

    alter table if exists oggetti_ordine 
       drop constraint if exists FKl463u7rwv54ynoft8nh0hf872;

    alter table if exists ordine 
       drop constraint if exists FK9aqtlofxcuoakkgdmonvalmje;

    alter table if exists pesci 
       drop constraint if exists FKdw5iqq1aj9wq7qp2ajn143psc;

    alter table if exists pinguini 
       drop constraint if exists FKdxadp8w25mdifsepy1bjvbiwl;

    alter table if exists prodotti 
       drop constraint if exists FKn52bwip863qpm6uejbrg7w470;

    alter table if exists scimmie 
       drop constraint if exists FKa4sm58jo81j4doq8nn9ae9d8b;

    alter table if exists serpenti 
       drop constraint if exists FKs0xkhgmahthc0apv90pnd1xk3;

    alter table if exists tartarughe 
       drop constraint if exists FK6wciko8y5ghilsaopulb9v5k;

    alter table if exists utenti 
       drop constraint if exists FKph5t4nnxcvxmjtmko0plgkjid;

    alter table if exists utenti 
       drop constraint if exists FKdo1vk3w751hiuoqxdl4r2mmy9;

    drop table if exists animali cascade;

    drop table if exists area cascade;

    drop table if exists biglietti cascade;

    drop table if exists clienti cascade;

    drop table if exists delfini cascade;

    drop table if exists dipendenti cascade;

    drop table if exists eventi cascade;

    drop table if exists giornata_zoo cascade;

    drop table if exists lemuri cascade;

    drop table if exists leoni cascade;

    drop table if exists mangime cascade;

    drop table if exists messaggi_systema cascade;

    drop table if exists movimenti_mangime cascade;

    drop table if exists oggetti_ordine cascade;

    drop table if exists ordine cascade;

    drop table if exists pesci cascade;

    drop table if exists pinguini cascade;

    drop table if exists prodotti cascade;

    drop table if exists scimmie cascade;

    drop table if exists serpenti cascade;

    drop table if exists tartarughe cascade;

    drop table if exists turni cascade;

    drop table if exists utenti cascade;
