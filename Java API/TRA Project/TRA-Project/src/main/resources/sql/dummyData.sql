-- Agents
begin;
    insert into agent (AGENT_ID, NAME, EMAIL, PASSWORD)
    values
        ('1111-1111','Alexander','alexander@hogwarts.gb','TRA'),
        ('1111-2222','Daniel','daniel@durmstrang.ro','TRA');
commit;
end;

begin;
    insert into event (EVENT_ID, EVENT_TYPE, AGENT_ID, TIME, BODY)
    values
    ('2222-1111','Transfer','1111-1111', localtime,'Test Transfer event'),
    ('2222-2222','Transform','1111-2222', localtime,'Test Transform event');
commit;
end;