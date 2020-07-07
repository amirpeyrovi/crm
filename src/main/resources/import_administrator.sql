-- @ccess@dm!n!23
INSERT INTO crm_admin (id , username , password , create_by,create_at)
select admin_seq.nextval , 'administrator' , '$2a$10$RD/Qfrq60kKwlchas4LAPO3UK8o7ebg.jbEUjZewQtoTGLQTkQw4.',  1,sysdate from dual
where not exists(select id from crm_admin where username = 'administrator');

insert into crm_admin_role (title , id) select 'administrator' , role_seq.nextval from dual where not exists (select id from crm_admin_role where title = 'administrator');
update crm_admin set admin_role_id = (select id from crm_admin_role where title ='administrator');

insert into crm_admin_role_permission (adminRolePermissionId, ADMIN_ROLE_ID, PERMISSION_ID
    , CLIENT_ADD_ITEM, CLIENT_UPDATE_ITEM, CLIENT_DELETE_ITEM, CLIENT_SHOW_ITEM,
                             ADMIN_ADD_ITEM, ADMIN_UPDATE_ITEM, ADMIN_DELETE_ITEM, ADMIN_SHOW_ITEM )
select admin_seq.nextval , (select id from crm_admin_role where title = 'administrator') ,
       crm_permission.PERMISSION_ID, 1,1,1,1,1,1 ,1,1 from crm_permission where not exists
    (select ADMIN_ROLE_ID, PERMISSION_ID from crm_admin_permission where ADMIN_ROLE_ID = (select id from crm_admin_role where title = 'administrator')  AND crm_permission.PERMISSION_ID = crm_admin_permission.PERMISSION_ID);