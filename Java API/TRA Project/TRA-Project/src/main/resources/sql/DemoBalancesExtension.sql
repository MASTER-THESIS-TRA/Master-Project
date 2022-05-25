begin;
delete from ownerships where agent_id='e2ed2e7f-0b9d-41a5-85ce-408ac371e58a';
insert into ownerships (agent_id, resource) VALUES
    ('e2ed2e7f-0b9d-41a5-85ce-408ac371e58a','{Coffee Bean (g)->50000}');
commit;
end;