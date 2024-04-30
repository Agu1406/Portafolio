#define Numeric    N
#define Character  C
#define Boolean    L
#define Date       D
#define Memo       M

#command Set RDD to <rdd>                                               ;
=>                                                                      ;
REQUEST <rdd>                                                           ;
;RDDSETDEFAULT(<(rdd)>)                                                 ;
;__rdd:=<(rdd)>                                                         ;


#command Set RDD to                                                     ;
=>                                                                      ;
__rdd:="DBFNTX"                                                        

*=============================================================================
* CREATE TABLE

#command CREATE TABLE <(tablename)>                                     ;
([ <(name1)> <(type1)>( <length1>,<decimal1> )                          ;
 [,<(namen)> <(typen)>( <lengthn>,<decimaln> ) ]] )                     ;
=>                                                                      ;
__aDBF := {}                                                            ;
[; AADD(__aDBF,{<(name1)>,<(type1)>,<length1>,<decimal1>})]             ;
[; AADD(__aDBF,{<(namen)>,<(typen)>,<lengthn>,<decimaln>})]             ;
; DBCREATE(<(tablename)>,__aDBF) 

*=============================================================================
* DROP TABLE

#command DROP TABLE <(tablename)>                                       ;
=>                                                                      ;
FERASE(<(tablename)>) 

*=============================================================================
* CREATE INDEX

#command CREATE   [<unique:  UNIQUE>]                                   ;
                  [<descend: DESCENDING>]                               ;
         INDEX    <cOrderName>                                          ;
         ON TABLE <cOrderBagName> (<key>)                               ;
         [ FOR  <for> ]                                                 ;
=>                                                                      ;
dbUseArea( .T., __rdd, <(cOrderBagName)>,,.F. )                         ;
;ordCondSet( <"for">, <{for}>,,,,,, RECNO(),,,, [<.descend.>] )         ;
;ordCreate( <(cOrderBagName)>, <(cOrderName)>,                          ;
            <"key">, <{key}>, [<.unique.>]    )                         ;        
;dbCloseArea()

*=============================================================================
* DROP INDEX

#command DROP INDEX <cOrderName> ON TABLE <cOrderBagName>               ;
=>                                                                      ;
FERASE(<(cOrderBagName)>+ORDBAGEXT())
