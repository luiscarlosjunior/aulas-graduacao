DECLARE

  --Declare variables
  brokerage_id_limit  transactions.brokerage_id%TYPE := 10;
  stock_id_limit      transactions.stock_id%TYPE := 10;
  time_id             transactions.time_id%TYPE := 45;
  
BEGIN

  --Begin outer loop.  Create similar transactions for every broker
  FOR x IN 1..brokerage_id_limit LOOP
  
    --Begin nested loop.  Every brokerage will either buy or sell every stock.
    FOR i IN 1..stock_id_limit LOOP
    
      --Test for every even value of i
      IF mod(i, 2) = 0 THEN
      
        --Create a SELL record
        INSERT INTO transactions (STOCK_ID, TIME_ID, BROKERAGE_ID, 
                                  BUY_SELL_INDICATOR, NUMBER_SHARES, PRICE)
          VALUES (i, time_id, x, 'S', 100 + x + i, 10 + x + 1);
          
      ELSE
      
        --Create a BUY record
        INSERT INTO transactions (STOCK_ID, TIME_ID, BROKERAGE_ID, 
                                  BUY_SELL_INDICATOR, NUMBER_SHARES, PRICE)
          VALUES (i, time_id, x, 'B', 200 + x + i, 20 + x + 1);
      
      END IF;
    
    --End the nested loop
    END LOOP;
    
  --End the outer loop
  END LOOP;

END;