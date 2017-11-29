#!/bin/bash

# SQL script
init_file="init.sql"
load_file="load.sql"

# MySQL Database config file
db_config="./.database.cnf"

if [ ! -f "$db_config" ]
then
	echo "[ERROR] Database configuration '.database.cnf' file missing."
	exit
fi

if [ ! -f "$init_file" ]
then
	echo "[ERROR] 'init.sql' file not found. Place 'init.sql' in the same folder to continue."
	exit
fi

if [ ! -f "$load_file" ]
then
	echo "[ERROR] 'load.sql' file not found. Place 'load.sql' in the same folder to continue."
	exit
fi

echo "[INFO] Creating Rule Database & it's tables!..."
mysql --defaults-extra-file=$db_config < $init_file

echo "[INFO] Loading table contents."
mysql --defaults-extra-file=$db_config < $load_file