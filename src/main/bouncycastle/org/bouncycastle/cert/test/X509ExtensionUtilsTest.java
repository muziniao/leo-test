package org.bouncycastle.cert.test;

import java.io.IOException;
import java.security.cert.X509Certificate;

import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.x509.SubjectKeyIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.X509ExtensionUtils;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Hex;
import org.bouncycastle.util.test.SimpleTest;
import org.leo.util.CertificateUtils;

public class X509ExtensionUtilsTest
    extends SimpleTest
{
    private static byte[] pubKeyInfo = Base64.decode(
        "MFgwCwYJKoZIhvcNAQEBA0kAMEYCQQC6wMMmHYMZszT/7bNFMn+gaZoiWJLVP8ODRuu1C2jeAe" +
        "QpxM+5Oe7PaN2GNy3nBE4EOYkB5pMJWA0y9n04FX8NAgED");

    private static byte[] shaID = Hex.decode("d8128a06d6c2feb0865994a2936e7b75b836a021");
    private static byte[] shaTruncID = Hex.decode("436e7b75b836a021");
    private X509ExtensionUtils x509ExtensionUtils = new X509ExtensionUtils(new SHA1DigestCalculator());

    public String getName()
    {
        return "X509ExtensionUtilsTest";
    }

    public void performTest()
        throws Exception
    {
        //SubjectPublicKeyInfo pubInfo = SubjectPublicKeyInfo.getInstance(ASN1Primitive.fromByteArray(pubKeyInfo));
    	String certPath = "D:/temp/certest/www.google.com.hk.00.cer";
		X509Certificate cer = CertificateUtils.getCertificate(certPath);
    	SubjectPublicKeyInfo pubInfo = SubjectPublicKeyInfo.getInstance(ASN1Primitive.fromByteArray(cer.getTBSCertificate()));

        SubjectKeyIdentifier ski = x509ExtensionUtils.createSubjectKeyIdentifier(pubInfo);        

        if (!Arrays.areEqual(shaID, ski.getKeyIdentifier()))
        {
            fail("SHA-1 ID does not match");
        }

        ski = x509ExtensionUtils.createTruncatedSubjectKeyIdentifier(pubInfo);

        if (!Arrays.areEqual(shaTruncID, ski.getKeyIdentifier()))
        {
            fail("truncated SHA-1 ID does not match");
        }
    }

    public static void main(
        String[]    args)
    {
        runTest(new X509ExtensionUtilsTest());
    }
}
