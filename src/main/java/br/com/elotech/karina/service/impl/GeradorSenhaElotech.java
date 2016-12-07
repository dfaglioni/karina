package br.com.elotech.karina.service.impl;

import java.time.format.DateTimeFormatter;

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

        byte[] key = DigestUtils.getSha1Digest().digest(DigestUtils.md5Hex(KEY_ELOTECH).getBytes());

        KeyParameter keyParameter = new KeyParameter(key);
        BufferedBlockCipher cipher = new BufferedBlockCipher(new IDEAEngine());
        cipher.init(true, keyParameter);

        byte[] input = concatLicense.getBytes();

        byte[] out = new byte[input.length];

        cipher.processBytes(input, 0, input.length, out, 0);

        String ideaSecret = new String(Hex.encode(out)).toUpperCase();
        StringBuilder licenseKey = new StringBuilder();

        for (int i = 0; i < ideaSecret.length(); i++) {

            if (i % 9 == 0) {
                licenseKey.append(ideaSecret.charAt(i));
            }
        }

        return licenseKey.toString();

    }

}
