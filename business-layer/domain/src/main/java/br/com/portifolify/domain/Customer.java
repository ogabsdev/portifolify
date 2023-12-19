package br.com.portifolify.domain;

import br.com.portifolify.domain.exception.CreditScoreTooLowException;
import br.com.portifolify.domain.vo.CreditScore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Customer {

    private static final Integer MIN_SCORE = 40;

    @NonNull
    private String uuid;

    @NonNull
    private CustomerStatus status;

    @NonNull
    private String document;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @NonNull
    private CreditScore creditScore;

    public static CustomerBuilder builder() {
        return new CustomBuilder();
    }

    public void inactivate() {
        status = CustomerStatus.INACTIVE;
    }

    private static class CustomBuilder extends CustomerBuilder {

        @Override
        public Customer build() {
            validate();
            return super.build();
        }

        private void validate() {
            if (super.creditScore.getScorePercentage() < MIN_SCORE) {
                throw new CreditScoreTooLowException(
                        String.format("Credit Score of %s%% is too low. Min.: %s%%",
                                super.creditScore.getScorePercentage(), MIN_SCORE));
            }
        }
    }

}
