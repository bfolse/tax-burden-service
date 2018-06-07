-- Table: public.tax_rate

-- DROP TABLE public.tax_rate;

CREATE TABLE public.tax_rate
(
  id bigint NOT NULL,
  range_high integer,
  range_low integer,
  rate numeric(19,2),
  tax_definition_key character varying(255),
  CONSTRAINT tax_rate_pkey PRIMARY KEY (id),
  CONSTRAINT uk_fssqkiah11hqcy91qier4yeh3 UNIQUE (tax_definition_key)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.tax_rate
  OWNER TO postgres;
