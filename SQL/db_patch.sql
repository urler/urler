create role urler with password 'w:z$4jJ@^EQr7G#E';
alter role urler login;

create table links (
      link varchar NOT NULL DEFAULT nextLink()
    , url varchar not null
    , created_at timestamp not null default now()
    , constraint PK_links primary key(link)
);
grant select,update,delete,insert on links to urler;
grant usage on links_link_seq to urler;

CREATE OR REPLACE FUNCTION nextLink() RETURNS VARCHAR AS
$$
DECLARE
    cur_seq INTEGER;
    hash VARCHAR;
    result VARCHAR;
BEGIN
    SELECT INTO cur_seq nextval FROM  nextval('links_link_seq');
    
    hash := md5(random()::TEXT);
    hash := regexp_replace(hash, '\d', '', 'g');
    hash := substring(hash from 1 for 5);
    RETURN hash || cur_seq;
END;
$$ LANGUAGE 'plpgsql' ;

REVOKE ALL ON FUNCTION nextLink() FROM public;
GRANT EXECUTE ON FUNCTION nextLink() TO urler;


CREATE TABLE link_requests (
    request_id SERIAL
    , link VARCHAR NOT NULL
    , requested_at TIMESTAMP NOT NULL DEFAULT now()
    , ip varchar NOT NULL
    , constraint PK_link_requests primary key(request_id)
    , constraint FK_link_requests_request_id foreign key(link)
      references links(link)
      on delete restrict on update cascade
);
grant USAGE on link_requests_request_id_seq to urler;
grant select,delete,insert on link_requests to urler;


CREATE OR REPLACE VIEW link_request_statistics AS
	select
          link
        , request_date
        , count(*)
    FROM (
            SELECT
                  link
                , ip
                , requested_at::DATE as request_date
            FROM link_requests
            GROUP BY link, ip, requested_at::DATE
        ) t1
    GROUP BY link, request_date
    ORDER BY count(*) DESC
;
GRANT SELECT ON link_request_statistics to urler;
