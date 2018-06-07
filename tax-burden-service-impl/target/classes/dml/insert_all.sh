echo "political_division_state.sql"
psql -d postgresql://postgres:G\!antSt3ps@localhost:5432/postgres -f political_division_state.sql -e
echo "political_division_county_GA.sql"
psql -d postgresql://postgres:G\!antSt3ps@localhost:5432/postgres -f political_division_GA.sql -e
echo "postal_code_political_division_state_GA.sql"
psql -d postgresql://postgres:G\!antSt3ps@localhost:5432/postgres -f postal_code_political_division_state_GA.sql -e
echo "tax_definition.sql"
psql -d postgresql://postgres:G\!antSt3ps@localhost:5432/postgres -f tax_definition.sql -e
echo "tax_definition_sales_GA.sql"
psql -d postgresql://postgres:G\!antSt3ps@localhost:5432/postgres -f tax_definition_sales_GA.sql -e
echo "tax_definition_property_GA.sql"
psql -d postgresql://postgres:G\!antSt3ps@localhost:5432/postgres -f tax_definition_property_GA.sql -e
echo "tax_rate.sql"
psql -d postgresql://postgres:G\!antSt3ps@localhost:5432/postgres -f tax_rate.sql -e
echo "tax_rate_sales_GA.sql"
psql -d postgresql://postgres:G\!antSt3ps@localhost:5432/postgres -f tax_rate_sales_GA.sql -e
echo "tax_rate_property_GA.sql"
psql -d postgresql://postgres:G\!antSt3ps@localhost:5432/postgres -f tax_rate_property_GA.sql -e
echo "expenditure_category.sql"
psql -d postgresql://postgres:G\!antSt3ps@localhost:5432/postgres -f expenditure_category.sql -e
echo "tax_definition_expenditure_category.sql"
psql -d postgresql://postgres:G\!antSt3ps@localhost:5432/postgres -f tax_definition_expenditure_category_GA.sql -e
echo "consumer_expenditure_profile.sql"
psql -d postgresql://postgres:G\!antSt3ps@localhost:5432/postgres -f consumer_expenditure_profile.sql -e
