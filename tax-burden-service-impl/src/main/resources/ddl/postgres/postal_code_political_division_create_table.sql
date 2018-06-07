-- Table: public.postal_code_political_division

-- DROP TABLE public.postal_code_political_division;

CREATE TABLE public.postal_code_political_division
(
  id bigint NOT NULL,
  political_division_key character varying(255) NOT NULL,
  postal_code character varying(255) NOT NULL,
  CONSTRAINT postal_code_political_division_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.postal_code_political_division
  OWNER TO postgres;
