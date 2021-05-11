SET SERVEROUTPUT ON;
DECLARE

BEGIN
    GOTO segunda_mensagem;
    
    <<primeira_mensagem>>
    DBMS_OUTPUT.PUT_LINE('Olá, sou o primeiro');
    GOTO terceira_mensagem;
    
    <<segunda_mensagem>>
    DBMS_OUTPUT.PUT_LINE('Olá, sou o segundo');
    GOTO primeira_mensagem;
    
    <<terceira_mensagem>>
    DBMS_OUTPUT.PUT_LINE('Olá, sou o terceiro');
END;

BEGIN 
    FOR n_index IN 1..10 LOOP 
    
        FOR n_contador IN 1..10 LOOP
            DBMS_OUTPUT.PUT_LINE('Linha x Coluna: ' || n_index || 'x'|| n_contador);
            IF n_contador = 5 THEN
                goto primeiro_for;
            END IF;
        END LOOP;
        
        <<primeiro_for>>
        NULL;
    END LOOP;
    <<fim>>
    DBMS_OUTPUT.PUT_LINE('Final');
END;
/

BEGIN 
    FOR n_index IN 1..10 LOOP 
        FOR n_contador IN 1..10 LOOP
            DBMS_OUTPUT.PUT_LINE('Linha x Coluna: ' || n_index || 'x'|| n_contador);
            IF n_contador = 5 THEN
                goto primeiro_for;
            END IF;
        END LOOP;
        <<primeiro_for>>
        NULL;
        IF n_index = 8 THEN
            goto fim;
        END IF;        
    END LOOP;
    <<fim>>
    DBMS_OUTPUT.PUT_LINE('Final');
END;

