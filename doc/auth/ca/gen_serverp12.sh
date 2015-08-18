#!/bin/sh
dirPath="/root/ssl/ca/server"
openssl pkcs12 -export -clcerts -in $dirPath/server.crt -inkey $dirPath/server.key -out $dirPath/server.p12

