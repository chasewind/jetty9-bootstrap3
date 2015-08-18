
cmd /k keytool -importkeystore  -v  -srckeystore client.p12 -srcstoretype PKCS12  -destkeystore client.bks -deststoretype BKS -provider org.bouncycastle.jce.provider.BouncyCastleProvider
