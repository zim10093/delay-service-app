### delay service app
This app helps to solve problem of customer`s waiting. 
It can create report with average waiting time limited by date and section.
#### Input date
The company provides 10 different services, each with 3 variations. Questions are divided into
10 types, each can belong to 20 categories, a category can have 5 subcategories.
First line contains count S (<= 100.000) of all lines.
Every line starts with character C (waiting timeline) or D (query).
##### Waiting timeline:
`C service_id[.variation_id] question_type_id[.category_id.[sub-category_id]] P/N date time`
Values in square brackets are optional.

`service_id[.variation_id]` - service 9.1 represent service 9 of variation 1.

`question_type_id[.category_id.[sub-category_id]]` - question type 7.14.4 represent question type 7
category 14 and sub-category 4.

`P/N` - response type ‘P’ (first answer) or ‘N’ (next answer).

`date` - response date format is DD.MM.RRRR, for example, 27.11.2012 (27.november 2012).

`time` - time in minutes represent waiting time.
##### Query line:
`D service_id[.variation_id] question_type_id[.category_id.[sub-category_id]] P/N date_from[-date_to]`
Represent query, it prints out the average waiting time of all records matching specific criteria.

`service_id` and `question_type_id` can have special value `“*”`, it means query match all
services/question types. In the case of value `“*”`, neither service variation nor service
category/subcategory can be specified.
#### Output
Query line of type **‘D’** print out **average waiting time** rounded to minutes.
Only matching lines defined **before** the query line is counted.
It prints out **“-”** if the output is not defined.

#### Written with Java 17