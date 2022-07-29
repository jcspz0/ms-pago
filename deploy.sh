#!/bin/sh
PATH_REPO="/diplo/ms-pago"
RAMA_REPO="develop"

#Ubicarnos en la carpeta del repositorio
cd $PATH_REPO
#Actualizar el codigo
git checkout -b $RAMA_REPO
git pull origin $RAMA_REPO 
#contruir las imagenes y levantar los contenedores
docker-compose up --build
