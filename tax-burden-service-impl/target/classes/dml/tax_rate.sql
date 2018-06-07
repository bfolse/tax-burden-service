INSERT INTO public.tax_rate(
            id, tax_definition_key, rate, range_low, range_high)
    VALUES 
    (nextval('public.hibernate_sequence'), 'PA_STATE_INCOME', 0.0307, null, null)
    ;