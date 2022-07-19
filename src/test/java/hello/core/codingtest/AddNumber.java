package hello.core.codingtest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AddNumber {

    @Test
    @DisplayName("없는 숫자 더하기")
    void solution() {
        int[] numbers = {1,2,3,4,6,7,8,0};
        int[] sum = {0,1,2,3,4,5,6,7,8,9};
        int answer = 0;

        for (int i = 0; i < sum.length; i++) {
            for (int j = 0; j < numbers.length; j++) {
                if (sum[i] == numbers[j]) {
                    sum[i] = sum[i] - numbers[j];
                }
            }
            answer = answer + sum[i];
        }

        assertThat(answer).isEqualTo(14);
    }
}
