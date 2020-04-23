A project to automate testing the site http://prestashop-automation.qatestlab.com.ua/en/ in the Java language, 
using Selenium WebDriver as an automation framework and Maven as an automatic assembly tool.

Script for automation:

1. Open the main page of the site
2. Check that the price of products in the "Popular Products" section is indicated in accordance with the currency 
specified in the site header (USD, EUR, UAH).
3. Set the price display in USD using the drop-down list in the header of the site.
4. Search the catalog for “dress”
5. Check that the page "Search Results" contains the inscription "Products: x", where x is the number of actually 
found products.
6. Verify that the price of all displayed results is displayed in dollars.
7. Set the sorting to “high to low.”
8. Check that the goods are sorted by price from high to low.
9. For any product with a discount, it is necessary to verify that the price before and after the discount coincides 
with the indicated discount size.

Notes:

· The results of the checks and the action log displaing in the console.
