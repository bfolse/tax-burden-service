INSERT INTO public.expenditure_category(
            id, expenditure_category_key, description)
    VALUES (nextval('public.hibernate_sequence'), 'FOOD_AT_HOME', 'Non-prepared food'),
    (nextval('public.hibernate_sequence'), 'FOOD_AWAY_FROM_HOME', 'Prepared food'),
    (nextval('public.hibernate_sequence'), 'ALCOHOL', 'Alcoholic Beverages'),
    (nextval('public.hibernate_sequence'), 'HOUSING_SHELTER', 'Mortgage or Rent'),
    (nextval('public.hibernate_sequence'), 'HOUSING_UTILITIES', 'Household Utilities'),
    (nextval('public.hibernate_sequence'), 'HOUSING_OPERATIONS', 'Homeowners Association Dues and Fees, Insurance'),
    (nextval('public.hibernate_sequence'), 'HOUSING_HOUSEKEEPING_SUPPLIES', 'Household cleaning and maintenance supplies'),
    (nextval('public.hibernate_sequence'), 'HOUSING_FURNISHINGS', 'Household furnishings and equipment'),
    (nextval('public.hibernate_sequence'), 'APPAREL', 'Clothing, Accessories, and Laundry'),
    (nextval('public.hibernate_sequence'), 'TRANSPORTATION_VEHICLE_PURCHASE', 'Vehicle loan or lease'),
    (nextval('public.hibernate_sequence'), 'TRANSPORTATION_FUEL', 'Gasoline and Motor Oil'),
    (nextval('public.hibernate_sequence'), 'TRANSPORTATION_VEHICLE_OTHER', 'Repairs and Maintenance'),
    (nextval('public.hibernate_sequence'), 'TRANSPORTATION_PUBLIC', 'Public transportation, other transportation'),
    (nextval('public.hibernate_sequence'), 'HEALTHCARE', ''),
    (nextval('public.hibernate_sequence'), 'ENTERTAINMENT', ''),
    (nextval('public.hibernate_sequence'), 'PERSONAL_CARE', ''),
    (nextval('public.hibernate_sequence'), 'READING', ''),
    (nextval('public.hibernate_sequence'), 'EDUCATION', ''),
    (nextval('public.hibernate_sequence'), 'TOBACCO', ''),
    (nextval('public.hibernate_sequence'), 'MISC', ''),
    (nextval('public.hibernate_sequence'), 'SAVINGS', 'Post-tax savings and investments'),
    (nextval('public.hibernate_sequence'), 'PERSONAL_INSURANCE', ''),
    (nextval('public.hibernate_sequence'), 'PENSIONS', '')
    ;
    
    INSERT INTO public.expenditure_category_group(
            id, description, expenditure_category_group_key)
    VALUES (1, 'Categories subject to sales tax, excluding food at home (unprepared)', 'SALES_TAX_GROUP'),
    (2, 'Categories subject to sales tax, including food at home (unprepared)', 'SALES_TAX_GROUP_WITH_UNPREPARED_FOOD')
    ;

    INSERT INTO public.expenditure_category_group_expenditure_category_key(
            expenditure_category_group_id, expenditure_category_keys)
    VALUES (1, 'FOOD_AWAY_FROM_HOME'),
    (1, 'ALCOHOL'),
    (1, 'HOUSING_SHELTER'),
    (1, 'HOUSING_UTILITIES'),
    (1, 'HOUSING_OPERATIONS'),
    (1, 'HOUSING_HOUSEKEEPING_SUPPLIES'),
    (1, 'HOUSING_FURNISHINGS'),
    (1, 'APPAREL'),
    (1, 'TRANSPORTATION_VEHICLE_PURCHASE'),
    (1, 'TRANSPORTATION_FUEL'),
    (1, 'TRANSPORTATION_VEHICLE_OTHER'),
    (1, 'TRANSPORTATION_PUBLIC'),
    (1, 'HEALTHCARE'),
    (1, 'ENTERTAINMENT'),
    (1, 'PERSONAL_CARE'),
    (1, 'READING'),
    (1, 'EDUCATION'),
    (1, 'TOBACCO'),
    (1, 'MISC'),
	(1, 'FOOD_AWAY_FROM_HOME'),    
    (2, 'FOOD_AT_HOME'),
    (2, 'ALCOHOL'),
    (2, 'HOUSING_SHELTER'),
    (2, 'HOUSING_UTILITIES'),
    (2, 'HOUSING_OPERATIONS'),
    (2, 'HOUSING_HOUSEKEEPING_SUPPLIES'),
    (2, 'HOUSING_FURNISHINGS'),
    (2, 'APPAREL'),
    (2, 'TRANSPORTATION_VEHICLE_PURCHASE'),
    (2, 'TRANSPORTATION_FUEL'),
    (2, 'TRANSPORTATION_VEHICLE_OTHER'),
    (2, 'TRANSPORTATION_PUBLIC'),
    (2, 'HEALTHCARE'),
    (2, 'ENTERTAINMENT'),
    (2, 'PERSONAL_CARE'),
    (2, 'READING'),
    (2, 'EDUCATION'),
    (2, 'TOBACCO'),
    (2, 'MISC')
    ;
    
