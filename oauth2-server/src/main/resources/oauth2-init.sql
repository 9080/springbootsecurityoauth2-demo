-- oauth2 DDL
-- 客户端信息
create table IF NOT EXISTS oauth_client_details (
  client_id VARCHAR(256) PRIMARY KEY,
  resource_ids VARCHAR(256),
  client_secret VARCHAR(256),
  scope VARCHAR(256),
  authorized_grant_types VARCHAR(256),
  web_server_redirect_uri VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(256)
);

COMMENT ON COLUMN oauth_client_details.client_id IS '客户端ID';
COMMENT ON COLUMN oauth_client_details.resource_ids IS '资源ID集合,多个资源时用逗号(,)分隔';
COMMENT ON COLUMN oauth_client_details.client_secret IS '客户端密匙';
COMMENT ON COLUMN oauth_client_details.scope IS '客户端申请的权限范围';
COMMENT ON COLUMN oauth_client_details.authorized_grant_types IS '客户端支持的grant_type';
COMMENT ON COLUMN oauth_client_details.web_server_redirect_uri IS '重定向URI';
COMMENT ON COLUMN oauth_client_details.authorities IS '客户端所拥有的Spring Security的权限值，多个用逗号(,)分隔';
COMMENT ON COLUMN oauth_client_details.access_token_validity IS '访问令牌有效时间值(单位:秒)';
COMMENT ON COLUMN oauth_client_details.refresh_token_validity IS '更新令牌有效时间值(单位:秒)';
COMMENT ON COLUMN oauth_client_details.additional_information IS '附件信息（预留字段）';
COMMENT ON COLUMN oauth_client_details.autoapprove IS '用户是否自动授权操作';

--客户端使用的表
create table IF NOT EXISTS oauth_client_token (
  authentication_id VARCHAR(256) PRIMARY KEY,
  token_id VARCHAR(256),
  token LONG VARBINARY,
  user_name VARCHAR(256),
  client_id VARCHAR(256)
);
COMMENT ON COLUMN oauth_client_token.authentication_id IS 'MD5加密过的username,client_id,scope';
COMMENT ON COLUMN oauth_client_token.token_id IS 'MD5加密的access_token值';
COMMENT ON COLUMN oauth_client_token.token IS 'OAuth2AccessToken.java对象序列化后的二进制数据';
COMMENT ON COLUMN oauth_client_token.user_name IS '登录的用户名';
COMMENT ON COLUMN oauth_client_token.client_id IS '客户端ID';

--访问令牌存储
create table IF NOT EXISTS oauth_access_token (
  authentication_id VARCHAR(256) PRIMARY KEY,
  token_id VARCHAR(256),
  token LONG VARBINARY,
  user_name VARCHAR(256),
  client_id VARCHAR(256),
  authentication LONG VARBINARY,
  refresh_token VARCHAR(256)
);
COMMENT ON COLUMN oauth_access_token.authentication_id IS 'MD5加密过的username,client_id,scope';
COMMENT ON COLUMN oauth_access_token.token_id IS 'MD5加密的access_token的值';
COMMENT ON COLUMN oauth_access_token.token IS 'OAuth2AccessToken.java对象序列化后的二进制数据';
COMMENT ON COLUMN oauth_access_token.user_name IS '登录的用户名';
COMMENT ON COLUMN oauth_access_token.client_id IS '客户端ID';
COMMENT ON COLUMN oauth_access_token.authentication IS 'OAuth2Authentication.java对象序列化后的二进制数据';
COMMENT ON COLUMN oauth_access_token.refresh_token IS 'MD5加密后的refresh_token的值';

--更新令牌
create table IF NOT EXISTS oauth_refresh_token (
  token_id VARCHAR(256),
  token LONG VARBINARY,
  authentication LONG VARBINARY
);
COMMENT ON COLUMN oauth_refresh_token.token_id IS 'MD5加密过的refresh_token的值';
COMMENT ON COLUMN oauth_refresh_token.token IS 'OAuth2RefreshToken.java对象序列化后的二进制数据';
COMMENT ON COLUMN oauth_refresh_token.authentication IS 'OAuth2Authentication.java对象序列化后的二进制数据';

--授权码
create table IF NOT EXISTS oauth_code (
  code VARCHAR(256),
  authentication LONG VARBINARY
);
COMMENT ON COLUMN oauth_code.code IS '授权码(未加密)';
COMMENT ON COLUMN oauth_code.authentication IS 'AuthorizationRequestHolder.java对象序列化后的二进制数据';

--授权记录表
create table IF NOT EXISTS oauth_approvals (
	userId VARCHAR(256),
	clientId VARCHAR(256),
	scope VARCHAR(256),
	status VARCHAR(10),
	expiresAt TIMESTAMP,
	lastModifiedAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
COMMENT ON COLUMN oauth_approvals.userid IS '登录的用户名';
COMMENT ON COLUMN oauth_approvals.clientid IS '客户端ID';
COMMENT ON COLUMN oauth_approvals.scope IS '申请的权限';
COMMENT ON COLUMN oauth_approvals.status IS '状态（Approve或Deny）';
COMMENT ON COLUMN oauth_approvals.expiresat IS '过期时间';
COMMENT ON COLUMN oauth_approvals.lastmodifiedat IS '最终修改时间';


-- data schema
INSERT INTO `oauth_client_details`(
  `client_id`,
  `resource_ids`,
  `client_secret`,
  `scope`,
  `authorized_grant_types`,
  `web_server_redirect_uri`,
  `authorities`,
  `access_token_validity`,
  `refresh_token_validity`,
  `additional_information`,
  `autoapprove`
  )
  VALUES(
  'clientapp',
  null,
  '{bcrypt}$2a$04$LoD4ez17SMl3Md/c930MYuz8fIuFRWOtKk5ow1UcxdJXwDMomcx2K',
  'read_userinfo',
  'authorization_code,refresh_token',
  'https://www.baidu.com',
  null,
  3000,
  6000,
  null ,
  'false'
  );