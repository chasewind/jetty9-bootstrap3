#!/bin/sh
dirPath="/root/ssl/ca"
openssl genrsa -des3 -out $dirPath/users/client.key 1024
openssl req -new -key $dirPath/users/client.key -out $dirPath/users/client.csr
openssl ca -in $dirPath/users/client.csr -cert $dirPath/private/ca.crt -keyfile $dirPath/private/ca.key -out $dirPath/users/client.crt -config "$dirPath/conf/openssl.conf"
openssl pkcs12 -export -clcerts -in $dirPath/users/client.crt -inkey $dirPath/users/client.key -out $dirPath/users/client.p12

