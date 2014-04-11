package org.leo.ssl.sample;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.security.cert.X509Certificate;

import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERBoolean;
import org.bouncycastle.asn1.DERIA5String;
import org.bouncycastle.asn1.DERInteger;
import org.bouncycastle.asn1.DERObjectIdentifier;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x509.X509CertificateStructure;
import org.bouncycastle.asn1.x509.X509Extension;
import org.bouncycastle.asn1.x509.X509Extensions;
import org.bouncycastle.util.encoders.Hex;
import org.leo.util.CertificateUtils;

public class CertManager {
	String eoid[][] = {
			{ new String("Subject Key Identifier"), new String("2.5.29.14") },
			{ new String("Key Usage"), new String("2.5.29.15") },
			{ new String("Private Key Usage Period"), new String("2.5.29.16") },
			{ new String("Subject Alternative Name"), new String("2.5.29.17") },
			{ new String("Issuer Alternative Name"), new String("2.5.29.18") },
			{ new String("Basic Constraints"), new String("2.5.29.19") },
			{ new String("CRL Number"), new String("2.5.29.20") },
			{ new String("Reason code"), new String("2.5.29.21") },
			{ new String("Hold Instruction Code"), new String("2.5.29.23") },
			{ new String("Invalidity Date"), new String("2.5.29.24") },
			{ new String("Delta CRL indicator"), new String("2.5.29.27") },
			{ new String("Issuing Distribution Point"), new String("2.5.29.28") },
			{ new String("Certificate Issuer"), new String("2.5.29.29") },
			{ new String("Name Constraints"), new String("2.5.29.30") },
			{ new String("CRL Distribution Points"), new String("2.5.29.31") },
			{ new String("Certificate Policies"), new String("2.5.29.32") },
			{ new String("Policy Mappings"), new String("2.5.29.33") },
			{ new String("Authority Key Identifier"), new String("2.5.29.35") },
			{ new String("Policy Constraints"), new String("2.5.29.36") },
			{ new String("Extended Key Usage"), new String("2.5.29.37") } };
	byte buf[];

	public CertManager() {
		int fLength = 0;
		try {
			FileInputStream fis = new FileInputStream("D:/temp/certest/www.google.com.hk.00.base64.cer");
			fLength = fis.available();
			buf = new byte[fLength];
			fis.read(buf, 0, fLength);
		} catch (Exception ex) {
			System.out.println("读证书文件出错!");
			return;
		}
	}

	public byte[] getExtensionBytes(String oid, X509Extensions exts) {
		if (exts != null) {
			X509Extension ext = exts.getExtension(new DERObjectIdentifier(oid));
			if (ext != null) {
				return ext.getValue().getOctets();
			}
		}
		return null;
	}

	public void getCert() {

		ByteArrayInputStream bIn;
		ASN1InputStream dIn;
		String dump = "";

		try {
			//bIn = new ByteArrayInputStream(buf);
			//dIn = new ASN1InputStream(bIn);

			//ASN1Sequence seq = (ASN1Sequence) dIn.readObject();
			// dump = DERDump.dumpAsString(seq);
			// 调试输出语句
			// System.out.println(dump);
			// 证书的基本信息
			System.out.println("<<=============证书的基本信息===============>>");
			//X509CertificateStructure cert = new X509CertificateStructure(seq);
			X509Certificate cer = CertificateUtils.getCertificate("D:/temp/certest/www.google.com.hk.00.base64.cer");
			X509CertificateStructure cert = X509CertificateStructure.getInstance(ASN1Primitive.fromByteArray(cer.getEncoded()));

			
			System.out.println("证书版本:\t" + cert.getVersion());
			System.out.println("序列号:\t\t"
					+ cert.getSerialNumber().getValue().toString(16));
			System.out.println("算法标识:\t"
					+ cert.getSignatureAlgorithm().getObjectId().getId());
			System.out.println("签发者:\t\t" + cert.getIssuer());
			System.out.println("开始时间:\t" + cert.getStartDate().getTime());
			System.out.println("结束时间:\t" + cert.getEndDate().getTime());
			System.out.println("主体名:\t\t" + cert.getSubject());
			System.out.print("签名值:\t");
			DERBitString signature = cert.getSignature();
			String strSign = new String(Hex.encode(signature.getBytes()));
			System.out.println(strSign);
			System.out.println("主体公钥:\t");
			SubjectPublicKeyInfo pukinfo = cert.getSubjectPublicKeyInfo();
			System.out.println("\t标识符:\t"
					+ pukinfo.getAlgorithmId().getObjectId().getId());
			byte[] byPuk = pukinfo.getPublicKeyData().getBytes();
			String strPuk = new String(Hex.encode(byPuk));
			System.out.println("\t公钥值:\t" + strPuk);
			// 证书的扩展信息
			System.out.println("<<===========证书的扩展信息==============>>");
			X509Extensions ext = cert.getTBSCertificate().getExtensions();
			// 15 --key usage 19 ---basic constrains
			// 31-- crl point 32 ---certificate policy
			getKeyUsage(ext);
			getBasicConstrains(ext);
			getCRLPoint(ext);
			getCertPolicy(ext);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}

	// 取密钥的使用

	public void getKeyUsage(X509Extensions ext) {
		DERObjectIdentifier derOid = new DERObjectIdentifier("2.5.29.15");
		X509Extension item = null;
		boolean isCritical;
		ASN1OctetString value;
		try {
			item = ext.getExtension(derOid);
			isCritical = item.isCritical();
			value = item.getValue();
		} catch (Exception ex) {
			return;
		}
		System.out.println(new String(Hex.encode(value.getOctets())));
	}

	// 取基本限制
	public void getBasicConstrains(X509Extensions ext) {
		byte[] bytes = getExtensionBytes("2.5.29.19", ext);
		
		if (bytes != null) {
			try {
				ASN1InputStream dIn = new ASN1InputStream(
						new ByteArrayInputStream(bytes));
				ASN1Sequence seq = (ASN1Sequence) dIn.readObject();

				if (seq.size() == 2) {
					if (((DERBoolean) seq.getObjectAt(0)).isTrue()) {
						int pathlen = ((DERInteger) seq.getObjectAt(1))
								.getValue().intValue();
						System.out.println("是CA证书\t" + "max path len="
								+ pathlen);
					} else {
						System.out.println("不是ca证书!");
					}
				} else if (seq.size() == 1) {
					if (seq.getObjectAt(0) instanceof DERBoolean) {
						if (((DERBoolean) seq.getObjectAt(0)).isTrue()) {
							System.out.println(Integer.MAX_VALUE);
						}
					}
				}
			} catch (Exception e) {
				throw new RuntimeException(
						"error processing key usage extension");
			}
		}
	}

	// 取crl分布点
	public void getCRLPoint(X509Extensions ext) {
		byte[] byContent = getExtensionBytes("2.5.29.31", ext);

		if (byContent != null) {
			try {
				ASN1InputStream dIn = new ASN1InputStream(
						new ByteArrayInputStream(byContent));
				ASN1Sequence seq = (ASN1Sequence) dIn.readObject();
				int dpCount = seq.size();
				for (int i = 0; i < dpCount; i++) {
					// 第一个分布点(DistributionPoint)
					ASN1Sequence point1 = (ASN1Sequence) seq.getObjectAt(i);
					ASN1Object tobj = (DERTaggedObject) point1.getObjectAt(0);
					System.out.println("CRL分布点" + (i + 1) + ":");
					while (tobj instanceof DERTaggedObject
							&& !((DERTaggedObject) tobj).isEmpty()) {
						System.out.println("\ttagNo:"
								+ ((DERTaggedObject) tobj).getTagNo());
						if (tobj instanceof DERTaggedObject)
							tobj = ((DERTaggedObject) tobj).getObject();
					}
					ASN1Primitive os = tobj.toASN1Object();
					String str = new String(os.getEncoded());
					System.out.println("\t" + str);
				}
			} catch (Exception e) {
				System.out.println("crl分布点处理出错了!");
			}
		}
	}

	// 取证书策略
	public void getCertPolicy(X509Extensions ext) throws UnsupportedEncodingException {
		byte[] byContent = getExtensionBytes("2.5.29.32", ext);
		System.out.println(new String(Hex.encode(byContent), "utf-8"));
		System.out.println(org.apache.commons.codec.binary.Hex.encodeHexString(byContent));
		if (byContent != null) {
			try {
				ASN1InputStream dIn = new ASN1InputStream(
						new ByteArrayInputStream(byContent));
				ASN1Sequence seq = (ASN1Sequence) dIn.readObject();
				// String dump = DERDump.dumpAsString(seq);
				// 调试输出语句
				// System.out.println("证书策略:"+dump);
				for (int i = 0; i < seq.size(); i++) {
					getPolicyInfo((ASN1Sequence) seq.getObjectAt(i));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void getPolicyInfo(ASN1Sequence seq) {
		System.out.println(seq.size());
		DERObjectIdentifier objID = (DERObjectIdentifier) seq.getObjectAt(0);
		System.out.println("证书策略标识:" + objID.getId());
		if (seq.size() == 2) {
			objID = (DERObjectIdentifier) seq.getObjectAt(0);
			System.out.println("证书策略标识:" + objID.getId());
			ASN1Sequence seqQualifier = (ASN1Sequence) seq.getObjectAt(1);
			for (int i = 0; i < seqQualifier.size(); i++)
				getPolicyQualifierInfo((ASN1Sequence) seqQualifier
						.getObjectAt(i));
		} else
			System.out.println("解析策略声明时出错!");
	}

	private void getPolicyQualifierInfo(ASN1Sequence seq) {
		if (seq.size() == 2) {
			DERObjectIdentifier objID = (DERObjectIdentifier) seq
					.getObjectAt(0);
			System.out.println("策略声明标识:" + objID.getId());
			DERIA5String ia5 = (DERIA5String) seq.getObjectAt(1);
			System.out.println("声明内容:" + ia5.getString());
		}
	}

	public static void main(String[] args) {
		CertManager cm = new CertManager();
		cm.getCert();
	}
}
