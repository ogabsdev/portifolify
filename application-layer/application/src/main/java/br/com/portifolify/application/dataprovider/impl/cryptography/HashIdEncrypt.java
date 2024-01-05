package br.com.portifolify.application.dataprovider.impl.cryptography;

import br.com.portifolify.core.dataprovider.cryptography.Encrypt;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hashids.Hashids;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HashIdEncrypt implements Encrypt<Long, String> {

    private final Hashids hashidsInstance;

    @Override
    public String value(@NonNull Long value) {
        return hashidsInstance.encode(value);
    }

}
