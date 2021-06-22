--------------------------------------------------------
--  DDL for Table FUNCIONARIO
--------------------------------------------------------

  CREATE TABLE "FUNCIONARIO" 
   (	"IDFUNC" NUMBER(*,0), 
	"NOME" VARCHAR2(50 BYTE), 
	"IDCARGO" NUMBER(*,0)
   );

Insert into FUNCIONARIO (IDFUNC,NOME,IDCARGO) values ('1','Carlos','1');
Insert into FUNCIONARIO (IDFUNC,NOME,IDCARGO) values ('2','Camila','2');
Insert into FUNCIONARIO (IDFUNC,NOME,IDCARGO) values ('3','Ricardo','5');
Insert into FUNCIONARIO (IDFUNC,NOME,IDCARGO) values ('4','Marcela','11');
Insert into FUNCIONARIO (IDFUNC,NOME,IDCARGO) values ('5','Lais','15');

--------------------------------------------------------
--  Constraints for Table FUNCIONARIO
--------------------------------------------------------

  ALTER TABLE "FUNCIONARIO" MODIFY ("IDCARGO" NOT NULL ENABLE);
  ALTER TABLE "FUNCIONARIO" MODIFY ("NOME" NOT NULL ENABLE);
  ALTER TABLE "FUNCIONARIO" MODIFY ("IDFUNC" NOT NULL ENABLE);

--------------------------------------------------------
--  DDL for Table CARGO
--------------------------------------------------------

  CREATE TABLE "CARGO" 
   ("IDCARGO" NUMBER(*,0), 
	"CARGO" VARCHAR2(50 BYTE)
   );

Insert into CARGO (IDCARGO,CARGO) values ('1','Analista');
Insert into CARGO (IDCARGO,CARGO) values ('2','Gerente');
Insert into CARGO (IDCARGO,CARGO) values ('3','Vendas');
Insert into CARGO (IDCARGO,CARGO) values ('8','Comercial');
Insert into CARGO (IDCARGO,CARGO) values ('10','Presidente');
Insert into CARGO (IDCARGO,CARGO) values ('22','Secretária');

  ALTER TABLE "CARGO" MODIFY ("CARGO" NOT NULL ENABLE);
  ALTER TABLE "CARGO" MODIFY ("IDCARGO" NOT NULL ENABLE);
