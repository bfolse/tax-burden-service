-- Sequence: public.hibernate_sequence
DROP SEQUENCE IF EXISTS public.hibernate_sequence;

-- Table: public.consumer_expenditure_profile_entry
DROP TABLE IF EXISTS public.consumer_expenditure_profile_entry;

-- Table: public.consumer_expenditure_profile
DROP TABLE IF EXISTS public.consumer_expenditure_profile CASCADE;

-- Table: public.tax_definition_expenditure_category
DROP TABLE public.tax_definition_expenditure_category;

-- Table: public.expenditure_category_group_expenditure_category_key
DROP TABLE public.expenditure_category_group_expenditure_category_key;

-- Table: public.expenditure_category_group
DROP TABLE public.expenditure_category_group;

 -- Table: public.tax_payer_profile_political_division_keys
DROP TABLE IF EXISTS public.tax_payer_profile_political_division_keys;
  
-- Table: public.tax_burden_report_tax_entries
DROP TABLE IF EXISTS public.tax_burden_report_tax_entries;

-- Table: public.tax_entry
DROP TABLE IF EXISTS public.tax_entry CASCADE;

-- Table: public.tax_burden_report
DROP TABLE IF EXISTS public.tax_burden_report CASCADE;
  
-- Table: public.tax_payer_profile
DROP TABLE IF EXISTS public.tax_payer_profile;

-- Table: public.expenditure_category
DROP TABLE IF EXISTS public.expenditure_category;

-- Table: public.tax_definition
DROP TABLE IF EXISTS public.tax_definition;

-- Table: public.assessed_rate
DROP TABLE IF EXISTS public.assessed_rate;

-- Table: public.tax_rate
DROP TABLE IF EXISTS public.tax_rate CASCADE;

-- Table: public.postal_code_political_division
DROP TABLE public.postal_code_political_division;

-- Table: public.political_division
DROP TABLE IF EXISTS public.political_division;

-- Table: public.monetary_amount
DROP TABLE IF EXISTS public.monetary_amount;

-- Table: public.tax_rate_set
DROP TABLE IF EXISTS public.tax_rate_set CASCADE;

-- Table: public.tax_rate_set_tax_rates
DROP TABLE IF EXISTS public.tax_rate_set_tax_rates;
