package br.com.portifolify.application.dataprovider.impl.cryptography;

import br.com.portifolify.core.dataprovider.cryptography.Decrypt;
import lombok.RequiredArgsConstructor;
import org.hashids.Hashids;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HashIdDecrypt implements Decrypt<String, Long> {

    private final Hashids hashidsInstance;

    @Override
    public Long value(String value) {

        long[] values = hashidsInstance.decode(value);

        return values.length > 0 ? values[0] : null;
    }

}
