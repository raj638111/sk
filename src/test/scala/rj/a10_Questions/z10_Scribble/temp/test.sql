


































































Q2 Table Name: Inventory
 warehouse product units
 ABC1 XYZ 3
 ABC1 DEF 4
 ABC2 XYZ 10
 ABC2 PQR 1

 Table Name: Dimensions
 product W L H
 XYZ 12 10 8
 PQR 4 3 3
 LMN 5 2
 1) cubic feet of items per warehouse (w * l * h) + (no_of_unit * (w * l * h)) + ....
 2) number of units with no dimensions record OR missing dimensions
 3) % of volume each building contributes to the entire network
 4) 3 most common products per warehouse



staff
-------
name
hire_date



select
  name,
  hire_date,
  dense_rank over (partition by name order by hire_date) as rank
from
  staff
where rank = 3;



PS : Raj: I have copied the question to my editor as it is difficult to view back and forth

Q1 Status table
demand_id timestamp              status source warehouse
123        2019-01-01 00:01:02    s1     a      abc
123        2019-01-01 00:01:03    s1     a      abc
123        2019-01-01 00:01:04    s2     a      abc
123        2019-01-01 00:01:05    s1     a      abc
124        2019-01-01 00:01:06    s1     a      abc
124        2019-01-01 00:01:02    s1     a      abc
124        2019-01-01 00:01:03    s1     a      abc
124        2019-01-01 00:01:04    s2     a      abc
124        2019-01-01 00:01:05    s1     a      abc
124        2019-01-01 00:01:06    s1     a      abc

So, output should contain only the rows where status got changed.
output:
123        2019-01-01 00:01:02    s1     a      abc
123        2019-01-01 00:01:04    s2     a      abc
123        2019-01-01 00:01:05    s1     a      abc
124        2019-01-01 00:01:06    s1     a      abc
124        2019-01-01 00:01:04    s2     a      abc
124        2019-01-01 00:01:05    s1     a      abc

select
  a.demand_id, a.timestamp, a.status, a.source, a.warehouse
from
    Status a
left join
    Status b
on
 a.demand_id = b.demand_id and
 a.timestamp = b.timestamp - 1sec and
 (a.status != b.status or b.status is null)


Q2 Table Name: Inventory
 warehouse product units
 ABC1 XYZ 3
 ABC1 DEF 4
 ABC2 XYZ 10
 ABC2 PQR 1

 Table Name: Dimensions
 product W L H
 XYZ 12 10 8
 PQR 4 3 3
 LMN 5 2
 1) cubic feet of items per warehouse (w * l * h) + (no_of_unit * (w * l * h)) + ....
 2) number of units with no dimensions record OR missing dimensions
 3) % of volume each building contributes to the entire network
 4) 3 most common products per warehouse

1)---
select
    a.warehouse,
    sum(a.productsCf) as cf
from
    (select
        i.warehouse, i.product, i.units * (d.W * d.L * d.H) as productsCf
    Inventory i
    join
    Dimenstions d
    on
     i.product = d.product
    where
     d.W is not null and d.L is not null and d.H is not null)a
group by
 a.warehouse

2)--

select
 sum(i.units) as number_of_units
from
    Inventory i
left join
    Dimenstion d
on
    i.product = d.product
where
    d.W is null or d.L is null or d.H is null


3)-- Cartesian (wh, b)

select
 wh.warehouse,
 (100 / b.total) * wh.cf as percentage
from
    (select
        a.warehouse,
        sum(a.productsCf) as cf
    from
        (select
            i.warehouse, i.product, i.units * (d.W * d.L * d.H) as productsCf
        Inventory i
        join
        Dimenstions d
        on
         i.product = d.product
        where
         d.W is not null and d.L is not null and d.H is not null)a
    group by
     a.warehouse) wh,
    (
        select
            sum(i.units * i.units * (d.W * d.L * d.H)) as total
        from
        Inventory i
        join
        Dimenstions d
        on
         i.product = d.product
        where
         d.W is not null and d.L is not null and d.H is not null
    )b


4)---

select
    warehouse,
    dense_rank() over (partition by warehouse order by units desc) as rank
from
    Inventory i
where
    i.rank in (1, 2, 3)



Q3 Buying and selling stock to maximize the profit.
Stock price of a stock is given for each day for a month.
This is closing price of the stock for a given day.
{10,13,14,15,12,12.5,11.5 ......}

You can buy and sell stock any number of times but you should have only 1 position at a time.
Write a fuction to tell the user when to buy and when to sell to maximize the profit.


Q4 Develop a function to return the indexes of the 2 numbers which sum up to the target integer.
input . = [1,2,4,5,7,8]
target = [3,0,14,13,12,20]

example: target[0] = 3

input[0] + input[1] = 3

So, the output should be target 3 is equal to sum of input numbers at indices 0 and 1.





