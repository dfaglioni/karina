package br.com.elotech.karina.service.impl;

import java.time.format.DateTimeFormatter;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import br.com.elotech.karina.cripto.Idea;
import br.com.elotech.karina.domain.License;
import br.com.elotech.karina.service.GeradorSenha;
import lombok.SneakyThrows;

@Service
public class GeradorSenhaMD5 implements GeradorSenha {

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

        String keyMd5 = DigestUtils.md5Hex(KEY_ELOTECH);

        byte[] bytesConcat = concatLicense.getBytes();

        Idea idea = new Idea(keyMd5, false);

        idea.crypt(bytesConcat);

        return keyMd5;

    }

}
