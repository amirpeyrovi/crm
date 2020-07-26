# crm

https://dbdiagram.io/d/5ea57ee339d18f5553fe3a4c

admins:

v1/admin/login (post) [username, password]

v1/admin/admin/{adminId} (get, post, put, delete) [admin object]

v1/admin/role/{roleId} (get, post, put, delete) [role object]

v1/admin/role/{roleId}/permission (get, post, put, delete) [permission object list]




product:

v1/product/product/{productId} (get, post, put, delete) [product object]

v1/product/product/{productId}/addonLink (get, post, put, delete) [product addon object list]

v1/product/product/{productId}/cyclePriceLink (get, post, put, delete) [product cycle price object list]

v1/product/product/{productId}/parameterGroupLink (get, post, put, delete) [product parameter Group object list]

v1/product/product/{productId}/ServerParameter (get, post, put, delete) [Product Server Parameter Value object list]

v1/product/productAddon/{productAddonId} (get, post, put, delete) [product addon object]

v1/product/productAddon/{productAddonId}/cyclePriceLink (get, post, put, delete) [product cycle price object list]

v1/product/productAddon/{productAddonId}/parameterGroupLink (get, post, put, delete) [product parameter Group object list]

v1/product/productGroup/{productGroupId} (get, post, put, delete) [product group object]

v1/product/productCycle/{productCycleId} (get, post, put, delete) [product cycle object]

v1/product/productparameterGroup/{productparameterGroupId} (get, post, put, delete) [product parameter Group object]

v1/product/productparameter/{productparameterId} (get, post, put, delete) [product parameter object]




order:

v1/order/buy

v1/order/renew

v1/order/upgrade

