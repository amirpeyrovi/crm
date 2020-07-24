----------------------------CLIENT----------------------------------
INSERT INTO crm_permission (PERMISSION_ID , TITLE , SHOW_NAME , create_by,create_at)
select 1 , 'Client_Client' , 'Clients Information' , 1 , sysdate from dual
where not exists(select PERMISSION_ID from crm_permission where PERMISSION_ID = 1);

INSERT INTO crm_permission (PERMISSION_ID , TITLE , SHOW_NAME , create_by,create_at)
select 2 , 'Client_CONTACT' , 'Contact Of Clients' , 1 , sysdate from dual
where not exists(select PERMISSION_ID from crm_permission where PERMISSION_ID = 2);

INSERT INTO crm_permission (PERMISSION_ID , TITLE , SHOW_NAME , create_by,create_at)
select 3 , 'Client_Credit' , 'Credit Of Clients' , 1 , sysdate from dual
where not exists(select PERMISSION_ID from crm_permission where PERMISSION_ID = 3);

INSERT INTO crm_permission (PERMISSION_ID , TITLE , SHOW_NAME , create_by,create_at)
select 4 , 'Client_ExternalCode' , 'ExternalCode Of Clients' , 1 , sysdate from dual
where not exists(select PERMISSION_ID from crm_permission where PERMISSION_ID = 4);

----------------------------FINANCIAL----------------------------------
INSERT INTO crm_permission (PERMISSION_ID , TITLE , SHOW_NAME , create_by,create_at)
select 101 , 'Invoice' , 'Invoice Information' , 1 , sysdate from dual
where not exists(select PERMISSION_ID from crm_permission where PERMISSION_ID = 101);

INSERT INTO crm_permission (PERMISSION_ID , TITLE , SHOW_NAME , create_by,create_at)
select 102 , 'Transaction' , 'Transaction Information' , 1 , sysdate from dual
where not exists(select PERMISSION_ID from crm_permission where PERMISSION_ID = 102);

----------------------------ORDER----------------------------------
INSERT INTO crm_permission (PERMISSION_ID , TITLE , SHOW_NAME , create_by,create_at)
select 201 , 'Order' , 'Order Information' , 1 , sysdate from dual
where not exists(select PERMISSION_ID from crm_permission where PERMISSION_ID = 201);

INSERT INTO crm_permission (PERMISSION_ID , TITLE , SHOW_NAME , create_by,create_at)
select 202 , 'OrderItem' , 'OrderItem Information' , 1 , sysdate from dual
where not exists(select PERMISSION_ID from crm_permission where PERMISSION_ID = 202);

INSERT INTO crm_permission (PERMISSION_ID , TITLE , SHOW_NAME , create_by,create_at)
select 203 , 'OrderTransaction' , 'OrderTransaction Information' , 1 , sysdate from dual
where not exists(select PERMISSION_ID from crm_permission where PERMISSION_ID = 203);

----------------------------PRODUCT----------------------------------
INSERT INTO crm_permission (PERMISSION_ID , TITLE , SHOW_NAME , create_by,create_at)
select 301 , 'Product' , 'Product Information' , 1 , sysdate from dual
where not exists(select PERMISSION_ID from crm_permission where PERMISSION_ID = 301);

INSERT INTO crm_permission (PERMISSION_ID , TITLE , SHOW_NAME , create_by,create_at)
select 302 , 'ProductAddon' , 'ProductAddon Information' , 1 , sysdate from dual
where not exists(select PERMISSION_ID from crm_permission where PERMISSION_ID = 302);

INSERT INTO crm_permission (PERMISSION_ID , TITLE , SHOW_NAME , create_by,create_at)
select 303 , 'ProductAddonLinks' , 'Links Of ProductAddon Information' , 1 , sysdate from dual
where not exists(select PERMISSION_ID from crm_permission where PERMISSION_ID = 303);

INSERT INTO crm_permission (PERMISSION_ID , TITLE , SHOW_NAME , create_by,create_at)
select 304 , 'ProductCycle' , 'ProductCycle Information' , 1 , sysdate from dual
where not exists(select PERMISSION_ID from crm_permission where PERMISSION_ID = 304);

INSERT INTO crm_permission (PERMISSION_ID , TITLE , SHOW_NAME , create_by,create_at)
select 305 , 'ProductCyclePrice' , 'Price Of ProductCycle Information' , 1 , sysdate from dual
where not exists(select PERMISSION_ID from crm_permission where PERMISSION_ID = 305);

INSERT INTO crm_permission (PERMISSION_ID , TITLE , SHOW_NAME , create_by,create_at)
select 306 , 'ProductGroup' , 'ProductGroup Information' , 1 , sysdate from dual
where not exists(select PERMISSION_ID from crm_permission where PERMISSION_ID = 306);

INSERT INTO crm_permission (PERMISSION_ID , TITLE , SHOW_NAME , create_by,create_at)
select 307 , 'ProductGroup' , 'ProductGroup Information' , 1 , sysdate from dual
where not exists(select PERMISSION_ID from crm_permission where PERMISSION_ID = 307);

INSERT INTO crm_permission (PERMISSION_ID , TITLE , SHOW_NAME , create_by,create_at)
select 308 , 'ProductParameter' , 'Parameter Of Product Information' , 1 , sysdate from dual
where not exists(select PERMISSION_ID from crm_permission where PERMISSION_ID = 308);

INSERT INTO crm_permission (PERMISSION_ID , TITLE , SHOW_NAME , create_by,create_at)
select 309 , 'ProductGroup_Parameter' , 'Parameter Of ProductGroup Information' , 1 , sysdate from dual
where not exists(select PERMISSION_ID from crm_permission where PERMISSION_ID = 309);

INSERT INTO crm_permission (PERMISSION_ID , TITLE , SHOW_NAME , create_by,create_at)
select 310 , 'ProductGroup_ParameterLinks' , 'Links Of Parameter Of ProductGroup Information' , 1 , sysdate from dual
where not exists(select PERMISSION_ID from crm_permission where PERMISSION_ID = 310);

INSERT INTO crm_permission (PERMISSION_ID , TITLE , SHOW_NAME , create_by,create_at)
select 311 , 'ProductServer_ParameterValue' , 'Value Of Parameter Of ProductServer Information' , 1 , sysdate from dual
where not exists(select PERMISSION_ID from crm_permission where PERMISSION_ID = 311);

----------------------------PROMOTION----------------------------------

INSERT INTO crm_permission (PERMISSION_ID , TITLE , SHOW_NAME , create_by,create_at)
select 401 , 'PromotionCode' , 'PromotionCode Information' , 1 , sysdate from dual
where not exists(select PERMISSION_ID from crm_permission where PERMISSION_ID = 401);

INSERT INTO crm_permission (PERMISSION_ID , TITLE , SHOW_NAME , create_by,create_at)
select 402 , 'PromotionCodeProduct' , 'PromotionCode Product' , 1 , sysdate from dual
where not exists(select PERMISSION_ID from crm_permission where PERMISSION_ID = 402);

----------------------------SERVER----------------------------------

INSERT INTO crm_permission (PERMISSION_ID , TITLE , SHOW_NAME , create_by,create_at)
select 501 , 'Server' , 'Server Information' , 1 , sysdate from dual
where not exists(select PERMISSION_ID from crm_permission where PERMISSION_ID = 501);

INSERT INTO crm_permission (PERMISSION_ID , TITLE , SHOW_NAME , create_by,create_at)
select 502 , 'ServerGroup' , 'ServerGroup Information' , 1 , sysdate from dual
where not exists(select PERMISSION_ID from crm_permission where PERMISSION_ID = 502);

INSERT INTO crm_permission (PERMISSION_ID , TITLE , SHOW_NAME , create_by,create_at)
select 503 , 'ServerParameter' , 'Server Parameter Information' , 1 , sysdate from dual
where not exists(select PERMISSION_ID from crm_permission where PERMISSION_ID = 503);
----------------------------SERVICE----------------------------------

INSERT INTO crm_permission (PERMISSION_ID , TITLE , SHOW_NAME , create_by,create_at)
select 601 , 'Service' , 'Service Information' , 1 , sysdate from dual
where not exists(select PERMISSION_ID from crm_permission where PERMISSION_ID = 601);

INSERT INTO crm_permission (PERMISSION_ID , TITLE , SHOW_NAME , create_by,create_at)
select 602 , 'ServiceAddon' , 'Service Addon Information' , 1 , sysdate from dual
where not exists(select PERMISSION_ID from crm_permission where PERMISSION_ID = 602);

INSERT INTO crm_permission (PERMISSION_ID , TITLE , SHOW_NAME , create_by,create_at)
select 603 , 'ServiceProductHistory' , 'History Of Service' , 1 , sysdate from dual
where not exists(select PERMISSION_ID from crm_permission where PERMISSION_ID = 603);

INSERT INTO crm_permission (PERMISSION_ID , TITLE , SHOW_NAME , create_by,create_at)
select 604 , 'ServiceProduct_ParameterValue' , 'Value Of Parameter OF Service' , 1 , sysdate from dual
where not exists(select PERMISSION_ID from crm_permission where PERMISSION_ID = 604);
----------------------------TICKET----------------------------------

INSERT INTO crm_permission (PERMISSION_ID , TITLE , SHOW_NAME , create_by,create_at)
select 701 , 'Ticket' , 'Ticket_Information' , 1 , sysdate from dual
where not exists(select PERMISSION_ID from crm_permission where PERMISSION_ID = 701);

----------------------------ADMIN----------------------------------

INSERT INTO crm_permission (PERMISSION_ID , TITLE , SHOW_NAME , create_by,create_at)
select 801 , 'Admin' , 'Admin_Information' , 1 , sysdate from dual
where not exists(select PERMISSION_ID from crm_permission where PERMISSION_ID = 801);

INSERT INTO crm_permission (PERMISSION_ID , TITLE , SHOW_NAME , create_by,create_at)
select 802 , 'AdminRole' , 'Admin_Role_Information' , 1 , sysdate from dual
where not exists(select PERMISSION_ID from crm_permission where PERMISSION_ID = 802);

INSERT INTO crm_permission (PERMISSION_ID , TITLE , SHOW_NAME , create_by,create_at)
select 803 , 'AdminLog' , 'Admin_Log_Information' , 1 , sysdate from dual
where not exists(select PERMISSION_ID from crm_permission where PERMISSION_ID = 803);

INSERT INTO crm_permission (PERMISSION_ID , TITLE , SHOW_NAME , create_by,create_at)
select 804 , 'AdminRolePermission' , 'Admin_Role_Permission_Information' , 1 , sysdate from dual
where not exists(select PERMISSION_ID from crm_permission where PERMISSION_ID = 804);

----------------------------ADMIN----------------------------------

INSERT INTO crm_permission (PERMISSION_ID , TITLE , SHOW_NAME , create_by,create_at)
select 900 , 'Ticket' , 'Ticket_Information' , 1 , sysdate from dual
where not exists(select PERMISSION_ID from crm_permission where PERMISSION_ID = 900);

INSERT INTO crm_permission (PERMISSION_ID , TITLE , SHOW_NAME , create_by,create_at)
select 901 , 'TicketLog' , 'Ticket_Log_Information' , 1 , sysdate from dual
where not exists(select PERMISSION_ID from crm_permission where PERMISSION_ID = 901);

INSERT INTO crm_permission (PERMISSION_ID , TITLE , SHOW_NAME , create_by,create_at)
select 902 , 'TicketNote' , 'Ticket_Note_Information' , 1 , sysdate from dual
where not exists(select PERMISSION_ID from crm_permission where PERMISSION_ID = 902);

INSERT INTO crm_permission (PERMISSION_ID , TITLE , SHOW_NAME , create_by,create_at)
select 903 , 'TicketStage' , 'Ticket_Stage_Information' , 1 , sysdate from dual
where not exists(select PERMISSION_ID from crm_permission where PERMISSION_ID = 903);

INSERT INTO crm_permission (PERMISSION_ID , TITLE , SHOW_NAME , create_by,create_at)
select 904 , 'TicketStageRole' , 'Ticket_Stage_Role_Information' , 1 , sysdate from dual
where not exists(select PERMISSION_ID from crm_permission where PERMISSION_ID = 904);

INSERT INTO crm_permission (PERMISSION_ID , TITLE , SHOW_NAME , create_by,create_at)
select 905 , 'TicketState' , 'Ticket_State_Information' , 1 , sysdate from dual
where not exists(select PERMISSION_ID from crm_permission where PERMISSION_ID = 905);

INSERT INTO crm_permission (PERMISSION_ID , TITLE , SHOW_NAME , create_by,create_at)
select 906 , 'TicketStateOfStage' , 'Ticket_State_of_Stage_Information' , 1 , sysdate from dual
where not exists(select PERMISSION_ID from crm_permission where PERMISSION_ID = 906);

INSERT INTO crm_permission (PERMISSION_ID , TITLE , SHOW_NAME , create_by,create_at)
select 907 , 'TicketWorkFlow' , 'Ticket_WorkFlow_Information' , 1 , sysdate from dual
where not exists(select PERMISSION_ID from crm_permission where PERMISSION_ID = 907);

INSERT INTO crm_permission (PERMISSION_ID , TITLE , SHOW_NAME , create_by,create_at)
select 908 , 'TicketStageParameter' , 'Ticket_Stage_Parameter' , 1 , sysdate from dual
where not exists(select PERMISSION_ID from crm_permission where PERMISSION_ID = 908);

INSERT INTO crm_permission (PERMISSION_ID , TITLE , SHOW_NAME , create_by,create_at)
select 909 , 'TicketStageAction' , 'Ticket_Stage_Action' , 1 , sysdate from dual
where not exists(select PERMISSION_ID from crm_permission where PERMISSION_ID = 909);

INSERT INTO crm_permission (PERMISSION_ID , TITLE , SHOW_NAME , create_by,create_at)
select 911 , 'TicketStateActionType' , 'Ticket_State_Action_Type' , 1 , sysdate from dual
where not exists(select PERMISSION_ID from crm_permission where PERMISSION_ID = 911);

----------------------------INVENTORY----------------------------------

INSERT INTO crm_permission (PERMISSION_ID , TITLE , SHOW_NAME , create_by,create_at)
select 2000 , 'InventoryType' , 'Inventory_Type_Action' , 1 , sysdate from dual
where not exists(select PERMISSION_ID from crm_permission where PERMISSION_ID = 2000);

INSERT INTO crm_permission (PERMISSION_ID , TITLE , SHOW_NAME , create_by,create_at)
select 2001 , 'InventoryGroup' , 'Inventory_Group_Action' , 1 , sysdate from dual
where not exists(select PERMISSION_ID from crm_permission where PERMISSION_ID = 2001);

INSERT INTO crm_permission (PERMISSION_ID , TITLE , SHOW_NAME , create_by,create_at)
select 2002 , 'InventoryItem' , 'Inventory_Item_Action' , 1 , sysdate from dual
where not exists(select PERMISSION_ID from crm_permission where PERMISSION_ID = 2002);

INSERT INTO crm_permission (PERMISSION_ID , TITLE , SHOW_NAME , create_by,create_at)
select 2003 , 'InventoryItemService' , 'Inventory_Item_Service_Action' , 1 , sysdate from dual
where not exists(select PERMISSION_ID from crm_permission where PERMISSION_ID = 2003);
----------------------------DATACENTER---------------------------------
INSERT INTO crm_permission (PERMISSION_ID , TITLE , SHOW_NAME , create_by,create_at)
select 1000 , 'DataCenterRack' , 'DataCenter_Rack' , 1 , sysdate from dual
where not exists(select PERMISSION_ID from crm_permission where PERMISSION_ID = 1000);

INSERT INTO crm_permission (PERMISSION_ID , TITLE , SHOW_NAME , create_by,create_at)
select 1001 , 'DataCenter' , 'DataCenter' , 1 , sysdate from dual
where not exists(select PERMISSION_ID from crm_permission where PERMISSION_ID = 1001);

INSERT INTO crm_permission (PERMISSION_ID , TITLE , SHOW_NAME , create_by,create_at)
select 1002 , 'DataCenterVendor' , 'DataCenter_Vendor' , 1 , sysdate from dual
where not exists(select PERMISSION_ID from crm_permission where PERMISSION_ID = 1002);

INSERT INTO crm_permission (PERMISSION_ID , TITLE , SHOW_NAME , create_by,create_at)
select 1003 , 'DataCenterServer' , 'DataCenter_Server' , 1 , sysdate from dual
where not exists(select PERMISSION_ID from crm_permission where PERMISSION_ID = 1003);

INSERT INTO crm_permission (PERMISSION_ID , TITLE , SHOW_NAME , create_by,create_at)
select 1004 , 'DataCenterSwitch' , 'DataCenter_Switch' , 1 , sysdate from dual
where not exists(select PERMISSION_ID from crm_permission where PERMISSION_ID = 1004);

INSERT INTO crm_permission (PERMISSION_ID , TITLE , SHOW_NAME , create_by,create_at)
select 1005 , 'DataCenterSwitchPort' , 'DataCenter_Switch_Port' , 1 , sysdate from dual
where not exists(select PERMISSION_ID from crm_permission where PERMISSION_ID = 1005);

INSERT INTO crm_permission (PERMISSION_ID , TITLE , SHOW_NAME , create_by,create_at)
select 1006 , 'DataCenterSwitchServiceLink' , 'DataCenter_Switch_Service_Link' , 1 , sysdate from dual
where not exists(select PERMISSION_ID from crm_permission where PERMISSION_ID = 1006);

INSERT INTO crm_permission (PERMISSION_ID , TITLE , SHOW_NAME , create_by,create_at)
select 1007 , 'DataCenterServerConfig' , 'DataCenter_Server_Config' , 1 , sysdate from dual
where not exists(select PERMISSION_ID from crm_permission where PERMISSION_ID = 1007);

INSERT INTO crm_permission (PERMISSION_ID , TITLE , SHOW_NAME , create_by,create_at)
select 1008 , 'DataCenterServerConfigGroup' , 'DataCenter_Server_Config_Group' , 1 , sysdate from dual
where not exists(select PERMISSION_ID from crm_permission where PERMISSION_ID = 1008);

INSERT INTO crm_permission (PERMISSION_ID , TITLE , SHOW_NAME , create_by,create_at)
select 1009 , 'DataCenterServerConfigValue' , 'DataCenter_Server_Config_Value' , 1 , sysdate from dual
where not exists(select PERMISSION_ID from crm_permission where PERMISSION_ID = 1009);

INSERT INTO crm_permission (PERMISSION_ID , TITLE , SHOW_NAME , create_by,create_at)
select 1010 , 'DataCenterServerServiceLink' , 'DataCenter_Server_Service_Link' , 1 , sysdate from dual
where not exists(select PERMISSION_ID from crm_permission where PERMISSION_ID = 1010);

----------------------------CONTRACT----------------------------------

INSERT INTO crm_permission (PERMISSION_ID , TITLE , SHOW_NAME , create_by,create_at)
select 10100 , 'ContractGroup' , 'Contract_Group' , 1 , sysdate from dual
where not exists(select PERMISSION_ID from crm_permission where PERMISSION_ID = 10100);

INSERT INTO crm_permission (PERMISSION_ID , TITLE , SHOW_NAME , create_by,create_at)
select 10101 , 'ContractProductGroupLink' , 'Contract_ProductGroup_Link' , 1 , sysdate from dual
where not exists(select PERMISSION_ID from crm_permission where PERMISSION_ID = 10101);

INSERT INTO crm_permission (PERMISSION_ID , TITLE , SHOW_NAME , create_by,create_at)
select 10102 , 'ContractServiceLink' , 'Contract_Service_Link' , 1 , sysdate from dual
where not exists(select PERMISSION_ID from crm_permission where PERMISSION_ID = 10102);

INSERT INTO crm_permission (PERMISSION_ID , TITLE , SHOW_NAME , create_by,create_at)
select 10103 , 'ContractTemplate' , 'Contract_Template' , 1 , sysdate from dual
where not exists(select PERMISSION_ID from crm_permission where PERMISSION_ID = 10103);