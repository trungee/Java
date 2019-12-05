package com.trungee;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import org.apache.commons.codec.binary.Base64;
import java.util.zip.InflaterInputStream;

public class TestDecompressJWT {

	public static void main(String[] args) throws Exception {
		String jwtToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eJyNU11r2zAU_StBz1EsS_6Q87RubFDWdqFZt8JShixfJ9psyUhK26z0v-_aKaXdQxkYg-859-ronOsHEvY1WZLr78Xq2zWZk1_R4CeXUrG0ZFQC5zQrGkGrpmqoyupCqCrVGS-RbFQky1RwlpUylSUXaVYJXs3JGvyt0fD1MABOOzdeIHsX47BMkqB30KuwuO-74NSwcH6b3IWEM5Yn-JgGbDTxkOhOmT4kVvXjjJoVUqesoLUuBc1Yw6msGaNMCdlWDWI8xTMuXTeybw3c0R5CUFsIWL4K4LH8sCEr71rTwZlTDTQbsox-D_MN-eK3ypo_KhpnA5Z_IHVsOh05G5JWbatVnVPGWEoFvqlkmtFWQ53nWSHaFjYE53wyPsQLlDy1fd05ZWZ8Qs7UC-Biuz-AfUJWLpjxXETsvuv-UTPxz08vxWy9Hwbn44zOTmz0zs7ee-d-h2nGy45JMxeszBE4GcxnOExDhMiU5kVBecUlzTLZUlVXnOqylZy36CkT07BTi3pUdP65-UnXmdOqO94A7M-r9cS-8t1H2wzO2DghY8wBc64hqkWP0S-0649MNPSDs1EZC35yeUPQyrIqm2J0Ft5yFpEqLQv1NvEGmWvQe48b9Oqopxusds7CBOij2jhGtLBTHu8we9fdwivB9pjZ2P9484i7dDQFt-l__ES-rVv8SfKCCSZ5yecE7ofnQl7yx7_UMRGA.Sg5yoyNYKxQ3l36lqtZk1gm186sdPBMNnKSdKXB0Q64";
		jwtToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eJyNU-9v0zAQ_V_8uW4cO86PfkMIpInCKko_kQk5zmU1JHZku9vK1P99l2SCgUCaFEXKu3f37t5dHkk4NWRDBteYHtYRQiQr8j0axBgXqc4roBUDTrNGaVpxwagEVahKZrzJOJKNimSTCp5mVZnmshBcCC6rFdmDvzMavpxHwGofjRfIPsY4bpIk6CMMKqwfhj44Na6dv03uQ8IZkwk-pgUbTTwnuldmCIlVw1QjRWGZpSVljKVU4JuWTDPaaWikzHLRdYAan10_se8M3NMBQlC3EBA-BPAIP9Zk512H426daqGtySb6E6xqcu1vlTU_VTTOBoS_InVKupo49avUa4J13hsf4idseU5brJ0DW_UCn7ye0Z0LZpJE1J76_q9G_o3OLeF-qhwDb0bzAc5zUV5kshQqp6ksGprJTNMG10dF24k2r4SQbTWLXlnUVNH5F8lt1RY6ZxWFjglM5hIHTAsqFH5noswL3ixzOK36ZQqw3w77GTz4_p1tR2dsnCPTpgOu2tj1gLtfazcsPHT0rbNRGQt-trkm6GWhO2gma-G_1t5g9h70yeNl_FHh2aDd0VmYA3pp4ZeeXUyfeJebC97CMjVew2sMQ34A226d--FOeOxkupdncH-9_w3YpsNfQRZMspLJYkXgYUQgZ5xlGQKXJxvTCoM.9VzLt9QBjmYZGd0ONEOxo3bS5OL1sOBDrtt9Db8JXqM";
		System.out.println("------------ Decode JWT ------------");
		String[] split_string = jwtToken.split("\\.");
		String base64EncodedHeader = split_string[0];
		String base64EncodedBody = split_string[1];
		String base64EncodedSignature = split_string[2];

		System.out.println("~~~~~~~ JWT Header ~~~~~");
		Base64 base64Url = new Base64(true);
		String header = new String(base64Url.decode(base64EncodedHeader));
		System.out.println("JWT Header : " + header);

		System.out.println("~~~~~~~ JWT Body ~~~~~");
		String body = decodeByteArrayToString(base64Url.decode(base64EncodedBody));
		System.out.println("JWT Body : " + body);

		System.out.println("~~~~~~~ Signature ~~~~~");
		System.out.println("JWT Signature : " + base64EncodedSignature);

	}

	/**
	 * Decode the byte[] in base64 to a string.
	 *
	 * @param bytes the data to encode
	 * @return the new string in {@link #UTF8_ENCODING}.
	 * @throws Exception
	 */
	public static String decodeByteArrayToString(final byte[] bytes) throws Exception {
		final ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		try (InflaterInputStream iis = new InflaterInputStream(bais)) {
			ByteArrayOutputStream bout = new ByteArrayOutputStream(512);
			int b;
			while ((b = iis.read()) != -1) {
				bout.write(b);
			}
			iis.close();
			bout.close();
			String s = new String(bout.toByteArray());
			return s;
		} catch (final Exception e) {
			throw e;
		}
	}

}
