-- Agents
begin;
    insert into agent (AGENT_ID, NAME, EMAIL, PASSWORD)
    values
        ('1','Alexander','alexander@hogwarts.gb','TRA'),
        ('2','Daniel','daniel@durmstrang.ro','TRA');
commit;
end;