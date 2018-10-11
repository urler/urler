# Very Basic URL shortener made on Java Spring Boot with API for URL creation support.
# Uploaded as NetBeans project

Postgres 9.2 on localhost used. SQL patch can be found in SQL folder.

API usage:
URL for API: exapmle.com/app/api

Requests accepted in JSON format.
To create new short link please use :
{
"action":"create",
"url": "www.google.com",
"id", "anyTransactionId"
};

Returns JSON with link and same id client send request with. Link can be used as example.com/linkId to get redirected to www.google.com (in this case).
Example:
{
"status": "OK"
"id": "anyTransactionId"
"message": "yourShortLink"
};

To get stats on link requests:
{
"action":"stats",
"link": yourLinkId,
"id": "anyTransactionId",
"date": "2018-10-12"
};

Returns JSON with request count;
Example:
{
"status": "OK"
"id": "anyTransactionId"
"message": 10
};

Possible statuses:
OK
BAD (error will be displayed in message)

Possible actions:
create
stats








