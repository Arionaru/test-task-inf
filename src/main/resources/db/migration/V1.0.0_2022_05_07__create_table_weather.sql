create table if not exists weather (
    id bigserial primary key not null,
    country varchar not null,
    city varchar not null,
    temperature decimal not null,
    receiving_time timestamp not null
);

create index if not exists weather_country_city on weather(city);
create index if not exists weather_country_city_receiving_time on weather(city, receiving_time);