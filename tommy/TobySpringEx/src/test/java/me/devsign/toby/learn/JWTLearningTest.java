package me.devsign.toby.learn;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.codec.binary.Base64;
import org.assertj.core.util.Maps;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.crypto.KeyGenerator;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.LinkedHashMap;
import java.util.Map;

public class JWTLearningTest {
    private static ECPublicKey EC_PUBLIC_KEY;
    private static ECPrivateKey EC_PRIVATE_KEY;

    @BeforeAll
    public static void beforeAll() throws NoSuchAlgorithmException, InvalidKeySpecException {
        final KeyFactory keyPairGenerator = KeyFactory.getInstance("EC");

        EC_PUBLIC_KEY = (ECPublicKey) keyPairGenerator
                .generatePublic(
                        new X509EncodedKeySpec(
                                Base64.decodeBase64("MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAElHJq0UsTwIbLyEx1ziseNNqB3e+bRYokZXf5+DdHD0kTpaSpQEvZmiOpoY6d7IH32bt0iec+1T70JthAjrt77g==")
                        )
                );
        EC_PRIVATE_KEY = (ECPrivateKey) keyPairGenerator
                .generatePrivate(
                        new PKCS8EncodedKeySpec(
                                Base64.decodeBase64("MEECAQAwEwYHKoZIzj0CAQYIKoZIzj0DAQcEJzAlAgEBBCCHM1vAuEO0wFe8CGh9cE4EfywnN5A1da+pUpX30XSvYw==")
                        )
                );
    }

    public void testJavaJWT() throws NoSuchAlgorithmException, IOException, InvalidKeyException, SignatureException {
        //given
        final ObjectMapper objectMapper = new ObjectMapper();
        final Map<String, Object> header = new LinkedHashMap<>();

        header.put("kid", "TEST01");
        header.put("typ", "JWT");
        header.put("alg", "ES256");
        final String headerStr = Base64.encodeBase64URLSafeString(objectMapper.writeValueAsBytes(header));

        final Map<String, Object> payload = new LinkedHashMap<>();
        payload.put("iss", "JWT를 생성한 곳");
        payload.put("iat", 0); // JWT 생성 시간
        final String payloadStr = Base64.encodeBase64URLSafeString(objectMapper.writeValueAsBytes(payload));

        // when
        // java 9부터 가능 (java 8 오류 발생 'java.security.NoSuchAlgorithmException: SHA256withECDSAinP1363Format
        // Signature not available')
        // SHA256withECDSA와 서명 형식이 다름, 일부 라이브러리에서 검증이 실패하는 경우가 있었음
        final Signature signature = Signature.getInstance("SHA256withECDSAinP1363Format");
        signature.initSign(EC_PRIVATE_KEY);
        signature.update((headerStr + "." + payloadStr).getBytes(StandardCharsets.UTF_8));

        byte[] signatureBytes = signature.sign();

        final String signatureStr = Base64.encodeBase64URLSafeString(signatureBytes);

        final String jwt = headerStr + "." + payloadStr + "." + signatureStr;

//        logJWT

    }

    @Test
    public void testPureJavaGenerateKeyPair() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        //given
        final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
        keyPairGenerator.initialize(new ECGenParameterSpec("secp256r1")); // == P256

        // when
        final KeyPair keyPair = keyPairGenerator.generateKeyPair();

        //then
        // nothing happen
        System.out.printf("ecKey.publicKey: %s\n", Base64.encodeBase64String(keyPair.getPublic().getEncoded()));
        System.out.printf("ecKey.privateKey: %s\n", Base64.encodeBase64String(keyPair.getPrivate().getEncoded()));
    }
}
