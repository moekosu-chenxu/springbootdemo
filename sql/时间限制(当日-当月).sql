EXPLAIN
SELECT * FROM dccp_act_csl_thumb a
WHERE 

#current day
a.create_time >= STR_TO_DATE(DATE_FORMAT('2017-09-26','%Y-%m-%d'),'%Y-%m-%d %H:%i:%s')
AND a.create_time <= DATE_ADD(DATE_ADD(STR_TO_DATE(DATE_FORMAT('2017-09-26','%Y-%m-%d'),'%Y-%m-%d %H:%i:%s'),INTERVAL 1 DAY),INTERVAL -1 SECOND)

#current month
AND a.create_time <
    DATE_ADD(
      CURDATE() - DAY(CURDATE()) + 1,
      INTERVAL 1 MONTH
    )
  AND a.create_time >=
    DATE_ADD(
      CURDATE(),
      INTERVAL - DAY(CURDATE()) + 1 DAY
    );
