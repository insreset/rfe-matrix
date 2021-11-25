package ua.pp.russian.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * The Event entity.
 */
@Entity
@Table(name = "event")
public class Event implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(max = 32)
    @Column(name = "name", length = 32, nullable = false)
    private String name;

    @NotNull
    @Size(max = 1024)
    @Column(name = "detales", length = 1024, nullable = false)
    private String detales;

    @NotNull
    @Size(max = 128)
    @Column(name = "jitsi", length = 128, nullable = false)
    private String jitsi;

    @OneToMany(mappedBy = "event")
    @JsonIgnoreProperties(value = { "event" }, allowSetters = true)
    private Set<Schedule> schedules = new HashSet<>();

    @OneToMany(mappedBy = "event")
    @JsonIgnoreProperties(value = { "event" }, allowSetters = true)
    private Set<Feedback> feedbacks = new HashSet<>();

    @OneToMany(mappedBy = "event")
    @JsonIgnoreProperties(value = { "event" }, allowSetters = true)
    private Set<Attachment> attachments = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    private Member organizer;

    @ManyToOne(optional = false)
    @NotNull
    private Language language;

    @ManyToOne(optional = false)
    @NotNull
    private Subject subject;

    @ManyToOne(optional = false)
    @NotNull
    private Type type;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Event id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Event name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetales() {
        return this.detales;
    }

    public Event detales(String detales) {
        this.setDetales(detales);
        return this;
    }

    public void setDetales(String detales) {
        this.detales = detales;
    }

    public String getJitsi() {
        return this.jitsi;
    }

    public Event jitsi(String jitsi) {
        this.setJitsi(jitsi);
        return this;
    }

    public void setJitsi(String jitsi) {
        this.jitsi = jitsi;
    }

    public Set<Schedule> getSchedules() {
        return this.schedules;
    }

    public void setSchedules(Set<Schedule> schedules) {
        if (this.schedules != null) {
            this.schedules.forEach(i -> i.setEvent(null));
        }
        if (schedules != null) {
            schedules.forEach(i -> i.setEvent(this));
        }
        this.schedules = schedules;
    }

    public Event schedules(Set<Schedule> schedules) {
        this.setSchedules(schedules);
        return this;
    }

    public Event addSchedules(Schedule schedule) {
        this.schedules.add(schedule);
        schedule.setEvent(this);
        return this;
    }

    public Event removeSchedules(Schedule schedule) {
        this.schedules.remove(schedule);
        schedule.setEvent(null);
        return this;
    }

    public Set<Feedback> getFeedbacks() {
        return this.feedbacks;
    }

    public void setFeedbacks(Set<Feedback> feedbacks) {
        if (this.feedbacks != null) {
            this.feedbacks.forEach(i -> i.setEvent(null));
        }
        if (feedbacks != null) {
            feedbacks.forEach(i -> i.setEvent(this));
        }
        this.feedbacks = feedbacks;
    }

    public Event feedbacks(Set<Feedback> feedbacks) {
        this.setFeedbacks(feedbacks);
        return this;
    }

    public Event addFeedbacks(Feedback feedback) {
        this.feedbacks.add(feedback);
        feedback.setEvent(this);
        return this;
    }

    public Event removeFeedbacks(Feedback feedback) {
        this.feedbacks.remove(feedback);
        feedback.setEvent(null);
        return this;
    }

    public Set<Attachment> getAttachments() {
        return this.attachments;
    }

    public void setAttachments(Set<Attachment> attachments) {
        if (this.attachments != null) {
            this.attachments.forEach(i -> i.setEvent(null));
        }
        if (attachments != null) {
            attachments.forEach(i -> i.setEvent(this));
        }
        this.attachments = attachments;
    }

    public Event attachments(Set<Attachment> attachments) {
        this.setAttachments(attachments);
        return this;
    }

    public Event addAttachments(Attachment attachment) {
        this.attachments.add(attachment);
        attachment.setEvent(this);
        return this;
    }

    public Event removeAttachments(Attachment attachment) {
        this.attachments.remove(attachment);
        attachment.setEvent(null);
        return this;
    }

    public Member getOrganizer() {
        return this.organizer;
    }

    public void setOrganizer(Member member) {
        this.organizer = member;
    }

    public Event organizer(Member member) {
        this.setOrganizer(member);
        return this;
    }

    public Language getLanguage() {
        return this.language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Event language(Language language) {
        this.setLanguage(language);
        return this;
    }

    public Subject getSubject() {
        return this.subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Event subject(Subject subject) {
        this.setSubject(subject);
        return this;
    }

    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Event type(Type type) {
        this.setType(type);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Event)) {
            return false;
        }
        return id != null && id.equals(((Event) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Event{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", detales='" + getDetales() + "'" +
            ", jitsi='" + getJitsi() + "'" +
            "}";
    }
}
