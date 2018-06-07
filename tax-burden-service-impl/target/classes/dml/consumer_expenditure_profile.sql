INSERT INTO public.consumer_expenditure_profile(
            id,  consumer_expenditure_profile_key,  description)
    VALUES (1, 'BASIC', 'Standard Consumer Expenditure Profile - 2015 US Bureau of Labor Statistics')
    ;
    
INSERT INTO public.consumer_expenditure_profile_entry(
            consumer_expenditure_profile_id, expenditure_category_key, percentage)
    VALUES (1, 'FOOD_AT_HOME', 0.072),
    (1, 'FOOD_AWAY_FROM_HOME', 0.054),
    (1, 'ALCOHOL', 0.009),
    (1, 'HOUSING_SHELTER', 0.0192),
    (1, 'HOUSING_UTILITIES', 0.069),
    (1, 'HOUSING_OPERATIONS', 0.023),
    (1, 'HOUSING_HOUSEKEEPING_SUPPLIES', 0.012),
    (1, 'HOUSING_FURNISHINGS', 0.032),
    (1, 'APPAREL', 0.033),
    (1, 'TRANSPORTATION_VEHICLE_PURCHASE', 0.071),
    (1, 'TRANSPORTATION_FUEL', 0.037),
    (1, 'TRANSPORTATION_VEHICLE_OTHER', 0.049),
    (1, 'TRANSPORTATION_PUBLIC', 0.012),
    (1, 'HEALTHCARE', 0.078),
    (1, 'ENTERTAINMENT', 0.051),
    (1, 'PERSONAL_CARE', 0.012),
    (1, 'READING', 0.002),
    (1, 'EDUCATION', 0.023),
    (1, 'TOBACCO', 0.006),
    (1, 'MISC', 0.016),
    (1, 'SAVINGS', 0.032),
    (1, 'PERSONAL_INSURANCE', 0.006),
    (1, 'PENSIONS', 0.0107)
    ;
