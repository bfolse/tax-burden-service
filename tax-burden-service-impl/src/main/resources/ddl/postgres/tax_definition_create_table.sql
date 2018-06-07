-- Table: public.tax_definition

-- DROP TABLE public.tax_definition;

CREATE TABLE public.tax_definition
(
  id bigint NOT NULL,
  description character varying(255),
  tax_definition_key character varying(255) NOT NULL,
  political_division_key character varying(255),
  tax_calc_strategy character varying(255),
  tax_type character varying(255),
  CONSTRAINT tax_definition_pkey PRIMARY KEY (id),
  CONSTRAINT uk_fokkcdj7egofkwedcrjgplrpy UNIQUE (political_division_key)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.tax_definition
  OWNER TO postgres;
