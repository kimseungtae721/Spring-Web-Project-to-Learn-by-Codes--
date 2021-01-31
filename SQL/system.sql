
ALTER SESSION SET "_ORACLE_SCRIPT"=true; 
CREATE USER book_ex IDENTIFIED BY book_ex;
GRANT connect, resource, dba TO book_ex;

commit;