package br.com.portifolify.core.dataprovider.cryptography;

public interface Encrypt<T, K> {

    K value(T value);

}
