#! /bin/bash

total=0
diferencias=""

function corrigeResto(){
  nombre=$1
  nota=0
  diferencias=""
  if test -e $nombre.tmp; then
   diff -w $nombre.tmp $nombre.txt > d1.tmp
   numlin=$(cat d1.tmp|wc -l)
   if test $numlin -eq 0; then
      nota=1
   else
      diferencias="diferencias en fichero de salida txt; ejecuta diff -w $nombre.txt $nombre.tmp "  # 1
   fi
  fi
  rm -rf d1.tmp 
  return $nota
}


compilador=javac

interprete=java


fuentes=$(ls *.java 2>/dev/null)
numero=$(ls *.java |wc -l)

directorio=practica0-prueba

numfuentes=0
nota=0
rm -rf *.tmp  $directorio/*.tmp $directorio/*.terr *.class $directorio/*.class $directorio/*.tmp.err

if test $numero -eq 1; then
  for fichero in $fuentes; do
   if test $fichero == Metodos.java ; then 
    if test -f $fichero; then
     let numfuentes=numfuentes+1
    fi
   fi
  done
fi

if test $numfuentes -eq 1; then
  if test $numero -gt 1; then
    echo "Error, hay mas ficheros con código fuente de los necesarios; 0"
    continuar=false
  else
    continuar=true
  fi
else
  continuar=false
  echo "Error, el fichero fuente necesario es Metodos.java; 0"
fi

if $continuar; then
 $compilador *.java 2> errores.compilacion 
 numlin=$(cat errores.compilacion | wc -l)
 if test $numlin -ne 0; then
  echo "Error de compilacion; 0"
  cat errores.compilacion
  exit 1
 fi
 rm -rf errores.compilacion
 mv *.class $directorio
 cd  $directorio
 ficherosprueba=$(ls *.java)
 total=0
 for prueba in $ficherosprueba; do
  nombre=$(basename $prueba .java)
  $compilador $prueba 2> $nombre.terr 
  numlin=$(cat $nombre.terr | wc -l)
  if test $numlin -eq 0; then
   $interprete $nombre >$nombre.tmp 2>$nombre.tmp.err
   numlin=$(cat $nombre.tmp.err|wc -l)
   if test $numlin -eq 0; then
     corrigeResto $nombre
     nota=$?
     if test $nota -eq 1; then
         echo "Prueba $nombre: Ok" 
         total=$(echo "$total+0.5"|bc)
     else
       echo "Prueba $nombre: hay diferencias en $diferencias" 
     fi
   else
     echo "Prueba $nombre: hay errores de ejecucion"
     cat $nombre.tmp.err
   fi
  else
   echo "Prueba $nombre: hay errores de compilacion" 
   cat $nombre.terr
  fi
 rm -rf d1.tmp $nombre.terr 
 done
 echo "Nota: $total"
fi

