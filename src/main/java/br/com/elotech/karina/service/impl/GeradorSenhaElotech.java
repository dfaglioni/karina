package br.com.elotech.karina.service.impl;

import java.time.format.DateTimeFormatter;
import java.util.Base64;

import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.engines.IDEAEngine;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.stereotype.Service;

import br.com.elotech.karina.domain.License;
import br.com.elotech.karina.service.GeradorSenha;
import lombok.SneakyThrows;

@Service
public class GeradorSenhaElotech implements GeradorSenha {

    private final static String KEY_ELOTECH = "elotech";

    @Override
    public String generate(License license) {

        StringBuilder builder = new StringBuilder()
                .append(license.getDate().format(DateTimeFormatter.ofPattern("dd/MM/YYYY")))
                .append(license.getName())
                .append(license.getCode());

        return internalGenerate(builder.toString());
    }

    @SneakyThrows
    private String internalGenerate(String concatLicense) {

        System.out.println(concatLicense);

        String keyForSha1 = DigestUtils.md5Hex(KEY_ELOTECH).toUpperCase();
        System.out.println(keyForSha1);

        String keyForIdea = new String(Hex.encode(DigestUtils.getSha1Digest().digest(keyForSha1.getBytes())))
                .toUpperCase();

        System.out.println(keyForIdea);

        byte[] input = concatLicense.getBytes();
        byte[] out = new byte[input.length];

        KeyParameter keyParameter = new KeyParameter(keyForIdea.getBytes());

        BufferedBlockCipher cipher = new BufferedBlockCipher(new IDEAEngine());

        cipher.init(true, keyParameter);

        cipher.processBytes(input, 0, input.length, out, 0);

        String ideaSecret = Base64.getEncoder().encodeToString(out);

        System.out.println(ideaSecret);

        return null;

    }

}
