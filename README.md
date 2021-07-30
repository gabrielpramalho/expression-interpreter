## Expression Interpreter

A simple expression interpreter built for Compilers course.

### Grammar

    prog ::= stmt EOL lines <br/>
    stmt ::= atr | imp <br/>
    lines ::= prog | ε <br/>
    atr ::= VAR EQ expr <br/>
    imp ::= PRINT OPEN VAR CLOSE <br/>
    expr ::= fact SUM expr | fact SUB expr | fact <br/>
    fact ::= term MULT fact | term DIV fact | term <br/>
    term ::= OPEN expr CLOSE | NUM | VAR <br/>
    VAR ::= all alphabet letters starting with $
    NUM ::= all numbers
    OPEN ::= (
    CLOSE ::= )
    SUM ::= +
    SUB ::= -
    DIV ::= /
    MULT ::= *
    PRINT ::= print
    EQ ::= =
    EOL ::= ;
    
### Intput
    $x = 1;
    $y = 2;
    $z = $x + $y;
    print ($z);

### Output
    3.0
