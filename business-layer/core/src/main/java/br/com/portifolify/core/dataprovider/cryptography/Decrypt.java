package br.com.portifolify.core.dataprovider.cryptography;

public interface Decrypt<T, K> {

    K value(T value);

}
