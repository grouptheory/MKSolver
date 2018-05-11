#!/bin/csh -f

set EXP=$1

foreach F (`ls $EXP-*.tex`)
   set BASE=`echo $F|sed -e 's/\..*//'`
   echo $BASE
   latex $F
   latex $F
   dvips -o $BASE.ps $BASE.dvi
   ps2pdf $BASE.ps
   rm $BASE.ps $BASE.dvi
end

ls -l $EXP*.pdf
