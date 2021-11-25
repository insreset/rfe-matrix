package ua.pp.russian.service.dto;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link ua.pp.russian.domain.Event} entity.
 */
@ApiModel(description = "The Event entity.")
public class EventDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 32)
    private String name;

    @NotNull
    @Size(max = 1024)
    private String detales;

    @NotNull
    @Size(max = 128)
    private String jitsi;

    private MemberDTO organizer;

    private LanguageDTO language;

    private SubjectDTO subject;

    private TypeDTO type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetales() {
        return detales;
    }

    public void setDetales(String detales) {
        this.detales = detales;
    }

    public String getJitsi() {
        return jitsi;
    }

    public void setJitsi(String jitsi) {
        this.jitsi = jitsi;
    }

    public MemberDTO getOrganizer() {
        return organizer;
    }

    public void setOrganizer(MemberDTO organizer) {
        this.organizer = organizer;
    }

    public LanguageDTO getLanguage() {
        return language;
    }

    public void setLanguage(LanguageDTO language) {
        this.language = language;
    }

    public SubjectDTO getSubject() {
        return subject;
    }

    public void setSubject(SubjectDTO subject) {
        this.subject = subject;
    }

    public TypeDTO getType() {
        return type;
    }

    public void setType(TypeDTO type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EventDTO)) {
            return false;
        }

        EventDTO eventDTO = (EventDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, eventDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EventDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", detales='" + getDetales() + "'" +
            ", jitsi='" + getJitsi() + "'" +
            ", organizer=" + getOrganizer() +
            ", language=" + getLanguage() +
            ", subject=" + getSubject() +
            ", type=" + getType() +
            "}";
    }
}
