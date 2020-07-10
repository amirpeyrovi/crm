-- @ccess@dm!n!23
INSERT INTO crm_admin (id , username , password , create_by,create_at)
select admin_seq.nextval , 'administrator' , '$2a$10$RD/Qfrq60kKwlchas4LAPO3UK8o7ebg.jbEUjZewQtoTGLQTkQw4.',  1,sysdate from dual
where not exists(select id from crm_admin where username = 'administrator');

insert into crm_admin_role (title , id) select 'administrator' , admin_seq.nextval from dual where not exists (select id from crm_admin_role where title = 'administrator');
update crm_admin set admin_role_id = (select id from crm_admin_role where title ='administrator');

insert into crm_admin_role_permission (id, admin_role_id, admin_permission_id
    , client_permission_add, client_permission_update, client_permission_delete, client_permission_view,
                                       admin_permission_add, admin_permission_update, admin_permission_delete, admin_permission_view )
select admin_seq.nextval , (select id from crm_admin_role where title = 'administrator') ,
       crm_permission.PERMISSION_ID, 1,1,1,1,1,1 ,1,1 from crm_permission where not exists
    (select admin_role_id, admin_permission_id from crm_admin_role_permission where admin_role_id = (select id from crm_admin_role where title = 'administrator')  AND crm_permission.PERMISSION_ID = crm_admin_role_permission.admin_permission_id);