#! /bin/bash

total=0
diferencias=""

function corrigeResto(){
  nombre=$1
  nota=0
  diferencias=""
  if test -e $nombre.tmp; then
   diff --brief -w -i $nombre.tmp $nombre.txt > d1.tmp
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

function claseabstracta(){
 touch erno.java
 echo "public class erno{" >>erno.java
 echo "  public static void main(String[] args){" >>erno.java
 echo "    Habitante imposible=new Habitante(\"Eraser McCaan\",'H');}}" >>erno.java
 $compilador erno.java 2> error.terr 
 numlin=$(cat error.terr | wc -l)
 if test $numlin -eq 0; then
   echo "La clase Habitante debe ser abstracta; 0"
   rm -rf erno.java error.terr erno.class
  exit 1
 fi
 rm -rf erno.java error.terr erno.class
}

compilador=javac

interprete=java


fuentes=$(ls *.java 2>/dev/null)
numero=$(ls *.java |wc -l)

directorio=practica2-prueba

numfuentes=0
nota=0
rm -rf *.tmp  $directorio/*.tmp $directorio/*.terr *.class $directorio/*.class $directorio/*.tmp.err

if test $numero -eq 11; then
  for fichero in $fuentes; do
   if test $fichero == Producto.java || test $fichero == Terreno.java || \
    test $fichero == Bestia.java || test $fichero == Demoledor.java || \
    test $fichero == Plebeyo.java || test $fichero == Guerrero.java || \
    test $fichero == Oscuro.java || test $fichero == Blanco.java || \
    test $fichero == Clan.java || test $fichero == Habitante.java || test $fichero == Mistico.java ; then 
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
  echo "Error, los ficheros fuentes necesarios son Producto.java, Terreno.java, Habitante.java, Plebeyo.java, Guerrero.java, Oscuro.java, Blanco.java, Bestia.java, Demoledor.java, Clan.java y Mistico.java; 0"
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
 claseabstracta
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

