#!/bin/sh
dirPath="/root/ssl/ca"
openssl genrsa -out private/ca.key
openssl req -new -key private/ca.key -out private/ca.csr
openssl x509 -req -days 3650 -in private/ca.csr -signkey private/ca.key -out private/ca.crt
echo FACE>serial
touch index.txt
openssl ca -gencrl -out $dirPath/private/ca.crl -crldays 7 -config "$/conf/openssl.conf"
