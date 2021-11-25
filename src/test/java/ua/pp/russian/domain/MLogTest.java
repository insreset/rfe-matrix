package ua.pp.russian.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import ua.pp.russian.web.rest.TestUtil;

class MLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MLog.class);
        MLog mLog1 = new MLog();
        mLog1.setId(1L);
        MLog mLog2 = new MLog();
        mLog2.setId(mLog1.getId());
        assertThat(mLog1).isEqualTo(mLog2);
        mLog2.setId(2L);
        assertThat(mLog1).isNotEqualTo(mLog2);
        mLog1.setId(null);
        assertThat(mLog1).isNotEqualTo(mLog2);
    }
}
