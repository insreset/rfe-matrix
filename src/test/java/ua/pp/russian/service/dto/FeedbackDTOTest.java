package ua.pp.russian.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import ua.pp.russian.web.rest.TestUtil;

class FeedbackDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FeedbackDTO.class);
        FeedbackDTO feedbackDTO1 = new FeedbackDTO();
        feedbackDTO1.setId(1L);
        FeedbackDTO feedbackDTO2 = new FeedbackDTO();
        assertThat(feedbackDTO1).isNotEqualTo(feedbackDTO2);
        feedbackDTO2.setId(feedbackDTO1.getId());
        assertThat(feedbackDTO1).isEqualTo(feedbackDTO2);
        feedbackDTO2.setId(2L);
        assertThat(feedbackDTO1).isNotEqualTo(feedbackDTO2);
        feedbackDTO1.setId(null);
        assertThat(feedbackDTO1).isNotEqualTo(feedbackDTO2);
    }
}
