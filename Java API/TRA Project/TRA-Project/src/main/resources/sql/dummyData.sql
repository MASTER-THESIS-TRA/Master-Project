-- Agents
begin;
    insert into agent (AGENT_ID, NAME, EMAIL, PASSWORD)
    values
        ('11111111-1111-1111-1111-111111111111','Admin','admin@mail.com','TRA');
commit;
end;

begin;
    insert into event (EVENT_ID, EVENT_TYPE, AGENT_ID, TIME, BODY)
    values
    ('2222-1111','Transfer','1111-1111', localtime,'Test Transfer event'),
    ('2222-2222','Transform','1111-2222', localtime,'Test Transform event');
commit;
end;

begin;
    insert into ownerships (AGENT_ID, RESOURCE_TYPE, AMOUNT)
    values
        ('1111-1111', 'Honey', 1);
commit;
end;

begin;
insert into location (LOCATION_ID, NAME, LONGITUDE, LATITUDE)
values
    ('11-22', 'Work Place', 55.6932463728027, 12.582942373108413);
commit;
end;
