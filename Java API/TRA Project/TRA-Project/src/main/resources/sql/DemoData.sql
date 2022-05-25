begin;
insert into agent (agent_id, name, email, password) VALUES
    ('11111111-1111-1111-1111-111111111111','Admin','admin@mail.com','TRA'),
    ('e2ed2e7f-0b9d-41a5-85ce-408ac371e58a','Farmer','farmer@mail.co','password1'),
    ('ee445839-76dd-4d24-bf90-b5370e04ef73','Purchase Point','purchasepoint@mail.co','password2'),
    ('e0ef4798-3360-46bc-8572-0d86adcdcb26','Dry Mill','drymill@mail.co','password3'),
    ('8dc52400-ee2b-4893-b6ba-14cef17e1108','Warehouse','warehouse@mail.se','password4'),
    ('03c4ceaf-fbcd-47b5-b499-872a302552e1','Roaster','roaster@mail.dk','password5'),
    ('cec52917-f8b1-40c7-9be1-265712532a83','Dagrosa','dagrosa@mail.dk','password6');
commit;
end;

begin;
insert into resource_type (resource_type_id, name, weight) VALUES
    ('9fd75104-178f-4023-b507-ad2ade8e4b1e','Quota(1kg Coffee Cherries)',1000),
    ('bb50fcb8-d567-44f4-82b2-a12a97fb08cf','Coffee Cherry (g)',1),
    ('6b92795e-6385-4764-b01c-a3e313093a27','Coffee Bean (g)',1),
    ('7cc47c33-db02-4100-be4d-8c2f9d2004b4','Parchment Coffee (g)',1),
    ('c749d02c-b5ce-4ff6-99fd-6afb45539633','Green Coffee (g)',1),
    ('ed7fb539-f60d-4d94-8752-5117dfb31e70','Roasted Coffee (g)',1),
    ('700741ba-0b13-4b2d-b915-1314fcf2d33f','Coffee Bag (400g)',401),
    ('9b529bce-a5f9-463a-ad9e-891d54a82023','Paper Bag (unit)',1),
    ('18bc2433-95d0-4cf0-8284-11e0f17248c5','Paper Waste (g)',1);
commit;
end;

begin;
insert into transform (transform_id, name, transform) VALUES
    ('a3d22d21-2ee1-4904-a82c-1136d3797f63','Harvest Coffee Cherries (1kg)','{Coffee Cherry (g)->1000,Quota(1kg Coffee Cherries)->-1}'),
    ('36df3c4b-9412-4ed1-922c-115cd6d0595e','Wet Mill (g)','{Coffee Bean (g)->1,Coffee Cherry (g)->-1}'),
    ('adf33d7a-a0bd-4661-97d5-5320c4c90bf0','Drying (g)','{Coffee Bean (g)->-1,Parchment Coffee (g)->1}'),
    ('ad457f4b-a7c3-4042-b1c1-8d33d9281c03','Dry Mill (g)','{Green Coffee (g)->1,Parchment Coffee (g)->-1}'),
    ('66f00a44-cfaf-4d7a-916f-a7f9093842b3','Roasting (g)','{Roasted Coffee (g)->1,Green Coffee (g)->-1}'),
    ('cf54fdec-9dc3-4137-a0a4-4a29c9051a7a','Bagging (400g)','{Roasted Coffee (g)->-400,Paper Bag (unit)->-1,Coffee Bag (400g)->1}'),
    ('4b265fbc-e4f9-4427-85b1-f1e523cbad67','Recycle Paper Bag','{Paper Bag (unit)->1,Paper Waste (g)->-1}'),
    ('74f4e1dc-906a-4eff-b40f-dd160425e50a','Unbag Coffee Bag','{Roasted Coffee (g)->400,Coffee Bag (400g)->-1,Paper Waste (g)->1}');
commit;
end;

begin;
insert into ownerships (agent_id, resource) VALUES
    ('e2ed2e7f-0b9d-41a5-85ce-408ac371e58a','{Quota(1kg Coffee Cherries)->1000}'),
    ('cec52917-f8b1-40c7-9be1-265712532a83','{Paper Bag (unit)->100}');
commit;
end;

begin;
insert into weights (type, weight) VALUES
    ('Quota(1kg Coffee Cherries)',1000),
    ('Coffee Cherry (g)',1),
    ('Coffee Bean (g)',1),
    ('Dry Bean (g)',1),
    ('Parchment Coffee (g)',1),
    ('Green Coffee (g)',1),
    ('Roasted Coffee (g)',1),
    ('Coffee Bag (400g)',401),
    ('Paper Bag (unit)',1),
    ('Paper Waste (g)',1);
commit;
end;