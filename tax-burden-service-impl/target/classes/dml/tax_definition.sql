INSERT INTO public.tax_definition(
            id, tax_definition_key, description, tax_calc_strategy, tax_type, political_division_key, ordinal)
    VALUES (nextval('public.hibernate_sequence'), 'GA_STATE_INCOME', 'GA State Income', 'incomeTaxFlatRateCalculator', 'INCOME_STATE', 13, 1),
    (nextval('public.hibernate_sequence'), 'PA_STATE_INCOME', 'PA State Income', 'incomeTaxFlatRateCalculator', 'INCOME_STATE', 42, 1)
    ;