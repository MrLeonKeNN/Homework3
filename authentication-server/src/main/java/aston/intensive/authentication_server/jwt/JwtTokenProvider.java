package aston.intensive.authentication_server.jwt;

import aston.intensive.authentication_server.exeption.InvalidPemFormatException;
import aston.intensive.authentication_server.exeption.JwtParseException;
import aston.intensive.authentication_server.exeption.KeyGenerationException;
import aston.intensive.authentication_server.exeption.PemFileNotFoundException;
import aston.intensive.authentication_server.exeption.PemFileReadException;
import aston.intensive.authentication_server.exeption.PublicKeyNotFoundException;
import aston.intensive.authentication_server.model.enums.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class JwtTokenProvider {

    public String generateToken(String name, List<Role> roles) {
        PrivateKey privateKey = getSecretPem();

        String jwt;
        try {
            jwt = Jwts.builder()
                    .subject("user123")
                    .issuer("your-service-name")
                    .audience().add("anAudience").and()
                    .issuedAt(new Date())
                    .expiration(new Date(System.currentTimeMillis() + 3600_000))
                    .claim("role", "admin")
                    .signWith(privateKey, Jwts.SIG.ES384)
                    .compact();
        } catch (Exception e) {
            throw new JwtParseException("Failed to generate JWT", e);
        }
        return jwt;
    }

    public String getNameFromToken(String token) {
        Claims claims;
        try {
            PublicKey publicKey = loadPublicKeyFromResource("public_key.pem");
            claims = parseJwtWithPublicKey(token, publicKey);
        } catch (Exception e) {
            throw new JwtParseException("Failed to parse JWT", e);
        }
        return claims.getSubject();
    }

    private PrivateKey getSecretPem() {
        InputStream inputStream = JwtTokenProvider.class
                .getClassLoader()
                .getResourceAsStream("private_pkcs8.pem");

        if (inputStream == null) {
            throw new PemFileNotFoundException("File not found in resources: private_pkcs8.pem");
        }

        try (PemReader pemReader = new PemReader(new InputStreamReader(inputStream))) {
            PemObject pemObject = pemReader.readPemObject();
            if (pemObject == null) {
                throw new InvalidPemFormatException("Invalid PEM file format or empty content.");
            }

            byte[] privateKeyBytes = pemObject.getContent();
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(privateKeyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("EC");
            return keyFactory.generatePrivate(spec);
        } catch (IOException e) {
            throw new PemFileReadException("Error reading PEM file", e);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new KeyGenerationException("Failed to generate private key", e);
        }
    }

    private PublicKey loadPublicKeyFromResource(String resourcePath) {
        InputStream inputStream = JwtTokenProvider.class.getClassLoader().getResourceAsStream(resourcePath);

        if (inputStream == null) {
            throw new PublicKeyNotFoundException("Public key file not found in resources: " + resourcePath);
        }

        try (Reader reader = new InputStreamReader(inputStream); PemReader pemReader = new PemReader(reader)) {
            PemObject pemObject = pemReader.readPemObject();
            SubjectPublicKeyInfo publicKeyInfo = SubjectPublicKeyInfo.getInstance(pemObject.getContent());
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyInfo.getEncoded());
            KeyFactory keyFactory = KeyFactory.getInstance("EC");
            return keyFactory.generatePublic(keySpec);
        } catch (IOException e) {
            throw new PemFileReadException("Error reading public key PEM file", e);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new KeyGenerationException("Failed to generate public key", e);
        }
    }

    private Claims parseJwtWithPublicKey(String jwt, PublicKey publicKey) {
        try {
            return Jwts.parser()
                    .verifyWith(publicKey)
                    .build()
                    .parseSignedClaims(jwt)
                    .getPayload();
        } catch (Exception e) {
            throw new JwtParseException("Failed to parse or verify JWT", e);
        }
    }

}
