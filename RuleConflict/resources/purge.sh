#!/bin/bash

# SQL script
purge_file="purge.sql"

# MySQL Database config file
db_config="./.database.cnf"

if [ ! -f "$db_config" ]
then
	echo "[ERROR] Database configuration '.database.cnf' file missing."
	exit
fi

if [ -f "$purge_file" ]
then 
	echo "[WARNING] Deletes the content of Rule Database & it's tables!..."
	read -s -r -p "Press any key to continue" -n 1 
	echo 
	mysql --defaults-extra-file=$db_config < $purge_file
else
	echo "[ERROR] 'purge.sql' file not found. Place 'purge.sql' in the same folder to continue."
fi