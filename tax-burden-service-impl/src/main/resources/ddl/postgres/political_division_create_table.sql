-- Table: public.political_division

-- DROP TABLE public.political_division;

CREATE TABLE public.political_division
(
  id bigint NOT NULL,
  description character varying(255),
  fips character varying(255) NOT NULL,
  name character varying(255),
  type character varying(255),
  CONSTRAINT political_division_pkey PRIMARY KEY (id),
  CONSTRAINT uk_9aqb5i2hpaw2ep01av4ilbjsm UNIQUE (fips)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.political_division
  OWNER TO postgres;
