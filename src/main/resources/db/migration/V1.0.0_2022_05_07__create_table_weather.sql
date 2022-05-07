create table if not exists weather (
    id bigserial,
    country varchar,
    city varchar,
    temperature decimal(2, 2),
    receiving_time timestamp
);

create index if not exists weather_country_city on weather(city);
create index if not exists weather_country_city_receiving_time on weather(city, receiving_time);