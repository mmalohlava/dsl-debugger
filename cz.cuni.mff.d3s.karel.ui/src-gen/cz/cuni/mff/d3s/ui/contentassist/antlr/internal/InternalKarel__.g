lexer grammar InternalKarel;
@header {
package cz.cuni.mff.d3s.ui.contentassist.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.Lexer;
}

T11 : 'NORTH' ;
T12 : 'SOUTH' ;
T13 : 'WEST' ;
T14 : 'EAST' ;
T15 : 'move' ;
T16 : 'turn' ;
T17 : 'turnOn' ;
T18 : 'turnOff' ;
T19 : 'put' ;
T20 : 'get' ;
T21 : 'WALL_AHEAD' ;
T22 : 'MARKED_PLACE' ;
T23 : 'BEGIN-PROGRAM' ;
T24 : 'END-PROGRAM' ;
T25 : 'BEGIN-INIT' ;
T26 : 'END-INIT' ;
T27 : 'position' ;
T28 : ',' ;
T29 : ';' ;
T30 : 'heading' ;
T31 : 'boardSize' ;
T32 : 'DEFINE COMMAND' ;
T33 : 'AS' ;
T34 : 'BEGIN' ;
T35 : 'END' ;
T36 : 'BEGIN-MAIN' ;
T37 : 'END-MAIN' ;
T38 : 'IF' ;
T39 : '(' ;
T40 : ')' ;
T41 : '{' ;
T42 : '}' ;
T43 : 'ELSE' ;
T44 : 'ITERATE' ;
T45 : 'TIMES' ;
T46 : 'WHILE' ;
T47 : '@(' ;
T48 : '!' ;

// $ANTLR src "../cz.cuni.mff.d3s.karel.ui/src-gen/cz/cuni/mff/d3s/ui/contentassist/antlr/internal/InternalKarel.g" 3224
RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

// $ANTLR src "../cz.cuni.mff.d3s.karel.ui/src-gen/cz/cuni/mff/d3s/ui/contentassist/antlr/internal/InternalKarel.g" 3226
RULE_INT : ('0'..'9')+;

// $ANTLR src "../cz.cuni.mff.d3s.karel.ui/src-gen/cz/cuni/mff/d3s/ui/contentassist/antlr/internal/InternalKarel.g" 3228
RULE_STRING : ('"' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'"')))* '"'|'\'' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'\'')))* '\'');

// $ANTLR src "../cz.cuni.mff.d3s.karel.ui/src-gen/cz/cuni/mff/d3s/ui/contentassist/antlr/internal/InternalKarel.g" 3230
RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../cz.cuni.mff.d3s.karel.ui/src-gen/cz/cuni/mff/d3s/ui/contentassist/antlr/internal/InternalKarel.g" 3232
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../cz.cuni.mff.d3s.karel.ui/src-gen/cz/cuni/mff/d3s/ui/contentassist/antlr/internal/InternalKarel.g" 3234
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../cz.cuni.mff.d3s.karel.ui/src-gen/cz/cuni/mff/d3s/ui/contentassist/antlr/internal/InternalKarel.g" 3236
RULE_ANY_OTHER : .;


