drop table if exists Pokemon;
create table if not exists Pokemon (
    PokemonId int primary key,
    PokemonName text,
    Damage int,
    Health int,
    Type int,
    OwnerName text
);