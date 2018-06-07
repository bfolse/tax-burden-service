-- Sequence: public.hibernate_sequence
CREATE SEQUENCE public.hibernate_sequence
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE public.hibernate_sequence
  OWNER TO postgres;

-- Table: public.monetary_amount
CREATE TABLE public.monetary_amount
(
  id bigint NOT NULL,
  amount numeric(19,2),
  currency character varying(255),
  CONSTRAINT monetary_amount_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.monetary_amount
  OWNER TO postgres;
  
-- Table: public.political_division
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

-- Table: public.tax_payer_profile
CREATE TABLE public.tax_payer_profile
(
    id bigint NOT NULL,
    consumer_expenditure_profile_key character varying(255) COLLATE pg_catalog."default",
    postal_code character varying(255) COLLATE pg_catalog."default",
    tax_payer_profile_key character varying(255) COLLATE pg_catalog."default",
    "timestamp" timestamp without time zone,
    annual_income_id bigint,
    mortgage_interest_id bigint,
    real_property_market_value_id bigint,
    CONSTRAINT tax_payer_profile_pkey PRIMARY KEY (id),
    CONSTRAINT fkleh6a51j5j3l33agkrvxdqtqs FOREIGN KEY (mortgage_interest_id)
        REFERENCES public.monetary_amount (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkn0kwv8atsd7ovr2sa44stlyp6 FOREIGN KEY (real_property_market_value_id)
        REFERENCES public.monetary_amount (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkqwk3insvhwv42rsqdb787vcyk FOREIGN KEY (annual_income_id)
        REFERENCES public.monetary_amount (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
);
ALTER TABLE public.tax_payer_profile
  OWNER TO postgres;

-- Table: public.tax_definition
CREATE TABLE public.tax_definition
(
  id bigint NOT NULL,
  description character varying(255),
  ordinal integer NOT NULL,
  political_division_key character varying(255),
  tax_calc_strategy character varying(255),
  tax_definition_key character varying(255) NOT NULL,
  tax_type character varying(255),
  CONSTRAINT tax_definition_pkey PRIMARY KEY (id),
  CONSTRAINT uk_1fnffr1fpofe5im9vhhmty9rh UNIQUE (tax_definition_key)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.tax_definition
  OWNER TO postgres;
    
-- Table: public.expenditure_category
CREATE TABLE public.expenditure_category
(
  id bigint NOT NULL,
  description character varying(255),
  expenditure_category_key character varying(255) NOT NULL,
  CONSTRAINT expenditure_category_pkey PRIMARY KEY (id),
  CONSTRAINT uk_iauitsidsk7sg9gb67y8ssgg4 UNIQUE (expenditure_category_key)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.expenditure_category
  OWNER TO postgres;

-- Table: public.expenditure_category_group
CREATE TABLE public.expenditure_category_group
(
  id bigint NOT NULL,
  description character varying(255),
  expenditure_category_group_key character varying(255) NOT NULL,
  CONSTRAINT expenditure_category_group_pkey PRIMARY KEY (id),
  CONSTRAINT uk_f429byyp68od09kt4tc31dk6o UNIQUE (expenditure_category_group_key)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.expenditure_category_group
  OWNER TO postgres;
  
-- Table: public.expenditure_category_group_expenditure_category_key
CREATE TABLE public.expenditure_category_group_expenditure_category_key
(
  expenditure_category_group_id bigint NOT NULL,
  expenditure_category_keys character varying(255),
  CONSTRAINT fkmpcnskur94uaaay15v8sabs2c FOREIGN KEY (expenditure_category_group_id)
      REFERENCES public.expenditure_category_group (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.expenditure_category_group_expenditure_category_key
  OWNER TO postgres;
  
  -- Table: public.tax_payer_profile_political_division_keys
CREATE TABLE public.tax_payer_profile_political_division_keys
(
  tax_payer_profile_id bigint NOT NULL,
  political_division_keys character varying(255),
  CONSTRAINT fksqnc5c3b3unltrehccod6jf87 FOREIGN KEY (tax_payer_profile_id)
      REFERENCES public.tax_payer_profile (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.tax_payer_profile_political_division_keys
  OWNER TO postgres;
  
-- Table: public.tax_burden_report
CREATE TABLE public.tax_burden_report
(
  id bigint NOT NULL,
  report_key character varying(255),
  "timestamp" timestamp without time zone,
  tax_payer_profile_id bigint,
  CONSTRAINT tax_burden_report_pkey PRIMARY KEY (id),
  CONSTRAINT fksifsm86mgl79e2dwb41u07s9r FOREIGN KEY (tax_payer_profile_id)
      REFERENCES public.tax_payer_profile (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.tax_burden_report
  OWNER TO postgres;
  
-- Table: public.tax_entry
CREATE TABLE public.tax_entry
(
  id bigint NOT NULL,
  tax_type integer,
  amount_id bigint,
  political_division_id bigint,
  CONSTRAINT tax_entry_pkey PRIMARY KEY (id),
  CONSTRAINT fkf3adhtjr00xfr5y0ricg8q4s9 FOREIGN KEY (political_division_id)
      REFERENCES public.political_division (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fkn5480468l3jx7v3f8vydotfx6 FOREIGN KEY (amount_id)
      REFERENCES public.monetary_amount (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.tax_entry
  OWNER TO postgres;

-- Table: public.tax_burden_report_tax_entries
CREATE TABLE public.tax_burden_report_tax_entries
(
  tax_burden_report_id bigint NOT NULL,
  tax_entries_id bigint NOT NULL,
  CONSTRAINT fkckcgmiij0uxa43rn8eojd30bp FOREIGN KEY (tax_burden_report_id)
      REFERENCES public.tax_burden_report (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fkpr389g63so6obnfs1qyok6636 FOREIGN KEY (tax_entries_id)
      REFERENCES public.tax_entry (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT uk_54b60oydp8g0qvxv9j35dh3tg UNIQUE (tax_entries_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.tax_burden_report_tax_entries
  OWNER TO postgres;
  
-- Table: public.tax_rate
CREATE TABLE public.tax_rate
(
  id bigint NOT NULL,
  range_high integer,
  range_low integer,
  rate numeric(19,6),
  tax_definition_key character varying(255),
  CONSTRAINT tax_rate_pkey PRIMARY KEY (id),
  CONSTRAINT uk_fssqkiah11hqcy91qier4yeh3 UNIQUE (tax_definition_key)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.tax_rate
  OWNER TO postgres;
  
-- Table: public.assessed_rate
CREATE TABLE public.assessed_rate
(
  id bigint NOT NULL,
  rate numeric(19,6),
  tax_definition_key character varying(255),
  CONSTRAINT assessed_rate_pkey PRIMARY KEY (id),
  CONSTRAINT uk_rwicclcvtbpyihm0cfujjm7ge UNIQUE (tax_definition_key)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.assessed_rate
  OWNER to postgres;

-- Table: public.postal_code_political_division
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
  
-- Table: public.tax_definition_expenditure_category
CREATE TABLE public.tax_definition_expenditure_category
(
  id bigint NOT NULL,
  expenditure_category_group_key character varying(255) NOT NULL,
  tax_definition_key character varying(255) NOT NULL,
  CONSTRAINT tax_definition_expenditure_category_pkey PRIMARY KEY (id),
  CONSTRAINT uk_iyosrb6lgpr1a5d0hjfe8cj8g UNIQUE (tax_definition_key)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.tax_definition_expenditure_category
  OWNER TO postgres;
  
-- Table: public.consumer_expenditure_profile
CREATE TABLE public.consumer_expenditure_profile
(
  id bigint NOT NULL,
  consumer_expenditure_profile_key character varying(255),
  description character varying(255),
  CONSTRAINT consumer_expenditure_profile_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.consumer_expenditure_profile
  OWNER TO postgres;

  -- Table: public.consumer_expenditure_profile_entry
  CREATE TABLE public.consumer_expenditure_profile_entry
(
  consumer_expenditure_profile_id bigint NOT NULL,
  percentage numeric(19,6),
  expenditure_category_key character varying(255) NOT NULL,
  CONSTRAINT consumer_expenditure_profile_entry_pkey PRIMARY KEY (consumer_expenditure_profile_id, expenditure_category_key),
  CONSTRAINT fkc1a9ute5fvchw2xgrthsdmbwq FOREIGN KEY (consumer_expenditure_profile_id)
      REFERENCES public.consumer_expenditure_profile (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.consumer_expenditure_profile_entry
  OWNER TO postgres;
