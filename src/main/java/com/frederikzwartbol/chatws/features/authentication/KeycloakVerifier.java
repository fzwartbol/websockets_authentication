package com.frederikzwartbol.chatws.features.authentication;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.AbstractMap;
import java.util.Base64;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * Custom offline JWT verifier for a Keycloak AccessToken. It's possible to verify the received access token against the Keycloak server for online verification
 * another way to verify the token is to parse the JWT using OAuth JWT verifier.
 */
@RequiredArgsConstructor
@Slf4j
public class KeycloakVerifier {

    @Value("${keycloak.client.issuer")
    private String ISSUER;

    private String AUDIENCE;
    private final Algorithm algorithm;
    private final JWTVerifier verifier;

    public KeycloakVerifier(String publicKey) {
        try {
            this.algorithm = Algorithm.RSA256((RSAPublicKey) createPublicKeyFromString(publicKey),null);
        } catch (Exception e) {
            throw new BadCredentialsException("Error parsing public key for Keycloak verifier");
        }

        this.verifier = JWT.require(this.algorithm)
                .withIssuer(ISSUER)
                .build();
    }

    public KeycloakUserRepresentation verifyToken(String token) {
        var decodedJWT = this.verifier.verify(token);
        return KeycloakUserRepresentation.fromDecodedJWT(decodedJWT);
    }

    private PublicKey createPublicKeyFromString (String publicKeyString) throws InvalidKeySpecException, NoSuchAlgorithmException {
        byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyString);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
       return keyFactory.generatePublic(spec);
    }

}
