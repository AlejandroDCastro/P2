#! /bin/bash

total=0
diferencias=""

function corrigeResto(){
  nombre=$1
  nota=0
  diferencias=""
  if test -e $nombre.tmp; then
   diff $nombre.tmp $nombre.txt > d1.tmp
   numlin=$(cat d1.tmp|wc -l)
   if test $numlin -eq 0; then
      if test -e $nombre.sal; then
        numlin=$(cat $nombre.sal|wc -l)
        if test $numlin -eq 0; then
          nota=1
        else
          nota=2
        fi
      else
        nota=1
      fi
   else
      diferencias="diferencias en fichero de salida txt; ejecuta diff $nombre.txt $nombre.tmp "  # 1
   fi
  fi
  rm -rf d1.tmp 
  return $nota
}

compilador=javac

interprete=java


fuentes=$(ls *.java 2>/dev/null)
numero=$(ls *.java |wc -l)

directorio=practica3-prueba

numfuentes=0
nota=0
rm -rf *.tmp  $directorio/*.tmp $directorio/*.terr *.class $directorio/*.class $directorio/*.tmp.err

if test $numero -eq 11; then
  for fichero in $fuentes; do
   if test $fichero == Ficha.java || test $fichero == Tablero.java || \
    test $fichero == Jugador.java || test $fichero == Saco.java ||test $fichero == Juego.java || \
    test $fichero == ObjetoNoValidoException.java || test $fichero == FichaRepetidaException.java || \
    test $fichero == JugadaIncorrectaException.java || test $fichero == CierreException.java || \
    test $fichero == SacoIncompletoException.java || test $fichero == PartidaGanadaException.java ; then 
    if test -f $fichero; then
     let numfuentes=numfuentes+1
    fi
   fi
  done
fi

if test $numfuentes -eq 11; then
  if test $numero -gt 11; then
    echo "Error, hay mas ficheros con código fuente de los necesarios; 0"
    continuar=false
  else
    continuar=true
  fi
else
  continuar=false
  echo "Error, los ficheros fuentes necesarios son Ficha.java, Tablero.java, Jugador.java, Saco.java, Juego.java, JugadaIncorrectaException.java, ObjetoNoValidoException.java, FichaRepetidaException.java, SacoIncompletoException.java, CierreException.java y PartidaGanadaException.java; 0"
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
   if test -e $nombre.ent; then
     $interprete $nombre $nombre.ent $nombre.tmp >$nombre.sal 2>$nombre.tmp.err
   else
     $interprete $nombre >$nombre.tmp 2>$nombre.tmp.err
   fi
   numlin=$(cat $nombre.tmp.err|wc -l)
   if test $numlin -eq 0; then
     corrigeResto $nombre
     nota=$?
     if test $nota -eq 1; then
         rm -rf $nombre.sal
         echo "Prueba $nombre: Ok" 
         total=$(echo "$total+0.5"|bc)
     else
       if test $nota -eq 2; then
         echo "Prueba $nombre: ok" 
         total=$(echo "$total+0.25"|bc)
       else
         rm -rf $nombre.sal
         echo "Prueba $nombre: hay diferencias en $diferencias" 
       fi
     fi
   else
     rm -rf $nombre.sal
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

